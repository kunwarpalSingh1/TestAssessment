package com.test.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dto.TestDto;
import com.test.dto.TestFilter;
import com.test.dto.converter.ListAndItemConverter;
import com.test.entity.Test;
import com.test.entity.Test_;
import com.test.service.TestService;
import com.test.utils.PaginationTestUtils;

@Service
@Transactional
public class TestFacadeImpl implements TestFacade {

	@Autowired
	private TestService testService;
	
	@Autowired
	private Converter<TestDto, Test> testDtoToEntityConverter;
	
	@Autowired
	private Converter<Test, TestDto> testToDtoConverter;
	
	@Autowired
	private ListAndItemConverter<Test, TestDto> testDtoListItemConverter;

	@Override
	@Transactional
	public Long save(TestDto testDto) {
		if (testDto.getId() != null) {
			return testService.updateAuditableEntity(testDtoToEntityConverter.convert(testDto));
		} else
			return testService.createAuditableEntity(testDtoToEntityConverter.convert(testDto));
	}
	
	@Override
	public TestDto findById(Long testId) {
		return testToDtoConverter.convert(testService.findById(testId));
	}
	
	@Override
	public Page<TestDto> find(TestFilter filter, Pageable pageable) {
		return testService.find(filter, PaginationTestUtils.sortByDefault(pageable, Sort.by(Test_.TITLE)))
				.map(testDtoListItemConverter::convert);
	}
	
	@Override
	@Transactional
	public void deleteById(Long testId) {
		testService.deleteAuditableEntity(testId);
	}
}
