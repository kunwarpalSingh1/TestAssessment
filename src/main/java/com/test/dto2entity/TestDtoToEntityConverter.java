package com.test.dto2entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.test.dao.TestDao;
import com.test.dto.TestDto;
import com.test.entity.Test;

@Component
public class TestDtoToEntityConverter implements Converter<TestDto, Test> {
	@Autowired
	private TestDao moviesDao;

	public Test convert(TestDto source) {
		Test target = new Test();
		if (source.getId() != null) {
			Test target1 = moviesDao.findById(source.getId()).orElseThrow(null);
			target.setTitle(target1.getTitle());
			target.setId(target1.getId());
			target.setChainId(target1.getChainId());
			target.setArchived(target1.getArchived());
			target.setAuditableStatus(target1.getAuditableStatus());
			target.setLastModifiedDate(target1.getLastModifiedDate());
			target.setCategory(target1.getCategory());
			target.setStarRating(target1.getStarRating());
		} else {
			target = new Test();
		}
		target.setTitle(source.getTitle());
		target.setCategory(source.getCategory());
		target.setStarRating(source.getStarRating());
		return target;
	}
}
