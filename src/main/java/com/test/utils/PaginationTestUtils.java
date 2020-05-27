package com.test.utils;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ReflectionUtils;

import com.test.entity.AuditableEntity_;
import com.test.sort.EntitySort;

/**
 * Utility class for paged responses.
 */
public final class PaginationTestUtils {

	private PaginationTestUtils() throws IllegalAccessException {
		throw new IllegalAccessException("PaginationUtils is non-instantiable.");
	}

	public static Pageable buildPageable(Integer maxResults, Integer pageNumber) {
		return Optional.ofNullable(maxResults).map(max -> PageRequest.of(pageNumber, max)).orElse(null);
	}

	public static Pageable buildPageable(Integer maxResults, Integer pageNumber, Sort sort) {
		return Optional.ofNullable(maxResults).map(max -> PageRequest.of(pageNumber, max, sort)).orElse(null);
	}

	public static Pageable setSort(Pageable pageable, Sort.Order... sortOrders) {
		final Sort sort = Sort.by(sortOrders);
		return setSort(pageable, sort);
	}

	public static Pageable setSort(Pageable pageable, Sort sort) {
		if (pageable == null) {
			return PageRequest.of(0, Integer.MAX_VALUE, sort);
		}
		return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
	}

	public static Sort historySort() {
		return Sort.by(Sort.Order.desc(AuditableEntity_.LAST_MODIFIED_DATE), Sort.Order.desc(AuditableEntity_.ID));
	}

	public static Pageable sortByDefault(Pageable pageable, Sort defaultSort) {
		if (!hasSort(pageable)) {
			return setSort(pageable, defaultSort);
		}
		return pageable;
	}

	public static boolean hasSort(Pageable pageable) {
		return pageable.getSort().isSorted();
	}

	public static Pageable setHistorySort(Pageable pageable) {
		return setSort(pageable, historySort());
	}

	public static <T> Page<T> buildEmptyPage() {
		return new PageImpl<>(Collections.emptyList());
	}

	public static <T> Pageable applyEntitySort(Pageable pageable, Class<T> dtoClass) {
		if (!hasSort(pageable)) {
			return pageable;
		}
		var mappedSort = pageable.getSort().get().flatMap(order -> mapToEntitySortOrder(order, dtoClass))
				.collect(Collectors.toList());

		return setSort(pageable, Sort.by(mappedSort));

	}

	private static <T> Stream<Sort.Order> mapToEntitySortOrder(Sort.Order order, Class<T> dtoClass) {
		var property = order.getProperty();

		if (dtoClass.isAnnotationPresent(EntitySort.List.class)) {
			return Stream.of(dtoClass.getAnnotation(EntitySort.List.class).value())
					.filter(entitySort -> entitySort.uiField().equals(order.getProperty()))
					.map(PaginationTestUtils::toEntityProperty)
					.map(s -> new Sort.Order(order.getDirection(), s, order.getNullHandling()));

		}
		if (dtoClass.isAnnotationPresent(EntitySort.class)) {
			return Stream.of(dtoClass.getAnnotation(EntitySort.class))
					.filter(entitySort -> entitySort.uiField().equals(order.getProperty()))
					.map(PaginationTestUtils::toEntityProperty)
					.map(s -> new Sort.Order(order.getDirection(), s, order.getNullHandling()));
		}

		var field = ReflectionUtils.findField(dtoClass, property);
		if (field != null) {
			if (field.isAnnotationPresent(EntitySort.List.class)) {
				return Stream.of(field.getAnnotation(EntitySort.List.class).value())
						.map(PaginationTestUtils::toEntityProperty)
						.map(s -> new Sort.Order(order.getDirection(), s, order.getNullHandling()));
			}
			if (field.isAnnotationPresent(EntitySort.class)) {
				return Stream.of(toEntityProperty(field.getAnnotation(EntitySort.class)))
						.map(s -> new Sort.Order(order.getDirection(), s, order.getNullHandling()));
			}
		}
		return Stream.of(order);
	}

	private static String toEntityProperty(EntitySort entitySort) {
		if (StringUtils.isNotEmpty(entitySort.value())) {
			return entitySort.value();
		}
		return StringUtils.join(entitySort.joined(), ".");
	}

}
