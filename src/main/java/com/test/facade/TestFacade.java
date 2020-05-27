package com.test.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.dto.TestDto;
import com.test.dto.TestFilter;

public interface TestFacade {

	Long save(TestDto testDto);

	TestDto findById(Long testId);
	
	Page<TestDto> find(TestFilter filter, Pageable pageable);

	void deleteById(Long testId);


}
