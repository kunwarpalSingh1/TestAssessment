package com.test.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Component;

import com.test.entity.AuditableEntity;
import com.test.entity.AuditableEntity_;

@Component
public class AuditableEntityPredicateGenerator {

    public <T extends AuditableEntity> Predicate unarchived(From<?, T> from, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(from.get(AuditableEntity_.archived), false);
    }

}
