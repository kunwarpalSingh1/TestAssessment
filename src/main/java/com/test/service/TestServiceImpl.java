package com.test.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.dao.TestDao;
import com.test.dto.TestFilter;
import com.test.entity.BaseAuditableService;
import com.test.entity.Test;
import com.test.specification.TestSpecificationGenerator;

@Service
@Transactional
public class TestServiceImpl extends BaseAuditableService<Test> implements TestService{

	@Autowired
	private TestDao testDao;
	
	@Autowired
	private TestSpecificationGenerator testSpecificationGenerator;
	
	@Override
	public Test save(Test test) {
		return testDao.save(test);
	}

	@Override
	public Test findById(Long testId) {
		return testDao.findById(testId).orElse(new Test());
	}
	

	@Override
	public Page<Test> find(TestFilter filter, Pageable pageable) {
		var filterSpecification = testSpecificationGenerator.byFilter(filter)
				.and(testSpecificationGenerator.isUnarchived());
		return testDao.findAll(filterSpecification, pageable);
	}
	
}
