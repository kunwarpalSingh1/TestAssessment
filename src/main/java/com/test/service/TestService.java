package com.test.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.dto.TestFilter;
import com.test.entity.Test;

public interface TestService extends AuditableEntityService<Test> {
	
	Test findById(Long testId);
	
	Page<Test> find(TestFilter filter, Pageable pageable);

}
