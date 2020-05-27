package com.test.converter.base;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;

public interface ListAndItemConverter<S,T> extends Converter<S,T> {

    default<E extends S> List<T> convertList(List<E> sourceList) {
        var nullSafeList = CollectionUtils.emptyIfNull(sourceList);
        return nullSafeList.stream().map(this::convert).collect(Collectors.toList());
    }
}
