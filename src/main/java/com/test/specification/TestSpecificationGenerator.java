package com.test.specification;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.test.dto.TestFilter;
import com.test.entity.Test;
import com.test.entity.Test_;
import com.test.utils.SpecificationTestUtils;

@Component
public class TestSpecificationGenerator extends AuditableEntitySpecificationGenerator<Test> {
	public Specification<Test> byFilter(TestFilter filter) {
		return (root, criteriaQuery, criteriaBuilder) -> {
			var predicates = new ArrayList<Predicate>();
			if (StringUtils.isNotEmpty(filter.getSearchText())) {

				predicates.add(criteriaBuilder.like(root.get(Test_.title),
						SpecificationTestUtils.wrapWithWildcards(filter.getSearchText())));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

		};

	}

	@Override
	protected Class<Test> getEntityClass() {
		return Test.class;
	}

}
