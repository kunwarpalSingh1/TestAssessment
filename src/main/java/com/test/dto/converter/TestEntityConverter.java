package com.test.dto.converter;

import org.springframework.stereotype.Service;

import com.test.dto.TestDto;
import com.test.entity.Test;

@Service
public abstract class TestEntityConverter<T extends Test>{

	public Test convert(TestDto source) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
