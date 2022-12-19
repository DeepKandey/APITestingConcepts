package com.qa.pojo.PODAMpojos;

import uk.co.jemos.podam.common.AttributeStrategy;

import java.util.List;

public class NameLevelStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue(Class attrType, List attrAnnotations) {
        String[] names = new String[]{"Harsh", "Sam", "Josh", "Jack"};
        return names[(int) (Math.random() * (names.length) + 0)];
    }
}
