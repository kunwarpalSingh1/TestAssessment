package com.test.utils;


import org.apache.commons.lang3.StringUtils;

public class SpecificationTestUtils {

    private SpecificationTestUtils() {
    }


    public static String wrapWithWildcards(String name) {
        return "%" + name + "%";
    }

    public static String fixForEnum(String name) {
        return StringUtils.defaultString(name).replaceAll(" ", "_");
    }


}
