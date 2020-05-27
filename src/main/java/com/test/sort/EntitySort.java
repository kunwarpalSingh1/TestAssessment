package com.test.sort;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({FIELD, TYPE})
public @interface EntitySort {

    String value() default "";

    String[] joined() default {};

    /**
     * Should be used if annotated on class only.
     * @return
     */
    String uiField() default "";

    @Documented
    @Retention(RUNTIME)
    @Target({FIELD, TYPE})
    @interface List {
        EntitySort[] value();
    }
}
