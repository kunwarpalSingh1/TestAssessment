package com.test.entity2Dto;

import org.springframework.stereotype.Component;

import com.test.converter.base.ListAndItemConverter;
import com.test.dto.TestDto;
import com.test.entity.Test;

@Component
public class TestDtoListItemConverter implements ListAndItemConverter<Test, TestDto> {

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
