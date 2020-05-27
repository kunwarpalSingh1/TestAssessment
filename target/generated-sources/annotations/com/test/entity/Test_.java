package com.test.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Test.class)
public abstract class Test_ extends com.test.entity.AuditableEntity_ {

	public static volatile SingularAttribute<Test, String> title;
	public static volatile SingularAttribute<Test, String> category;
	public static volatile SingularAttribute<Test, Float> starRating;

	public static final String TITLE = "title";
	public static final String CATEGORY = "category";
	public static final String STAR_RATING = "starRating";

}

