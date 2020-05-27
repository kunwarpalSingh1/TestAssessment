package com.test.specification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.test.entity.AuditableEntity;

public abstract class AuditableEntitySpecificationGenerator<T extends AuditableEntity> {

	@Autowired
	private AuditableEntityPredicateGenerator auditableEntityPredicateGenerator;

	public Specification<T> isUnarchived() {
		return (root, criteriaQuery, criteriaBuilder) -> auditableEntityPredicateGenerator.unarchived(root,
				criteriaBuilder);
	}

	protected abstract Class<T> getEntityClass();
}
