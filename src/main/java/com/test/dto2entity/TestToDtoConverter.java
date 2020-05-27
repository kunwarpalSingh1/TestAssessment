package com.test.dto2entity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.test.dto.TestDto;
import com.test.entity.Test;

@Component
public class TestToDtoConverter implements Converter<Test, TestDto> {

    @Override
    public TestDto convert(Test source) {
    	TestDto target = new TestDto();
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setCategory(source.getCategory());
        target.setStarRating(source.getStarRating());
        return target;
    }

}
