package com.test.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuditableEntity.class)
public abstract class AuditableEntity_ {

	public static volatile SingularAttribute<AuditableEntity, Boolean> archived;
	public static volatile SingularAttribute<AuditableEntity, Long> chainId;
	public static volatile SingularAttribute<AuditableEntity, Date> lastModifiedDate;
	public static volatile SingularAttribute<AuditableEntity, Long> id;
	public static volatile SingularAttribute<AuditableEntity, AuditableEntityStatus> auditableStatus;

	public static final String ARCHIVED = "archived";
	public static final String CHAIN_ID = "chainId";
	public static final String LAST_MODIFIED_DATE = "lastModifiedDate";
	public static final String ID = "id";
	public static final String AUDITABLE_STATUS = "auditableStatus";

}

