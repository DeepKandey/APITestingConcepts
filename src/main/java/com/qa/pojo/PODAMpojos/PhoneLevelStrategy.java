package com.qa.pojo.PODAMpojos;

import uk.co.jemos.podam.common.AttributeStrategy;

import java.util.List;

public class PhoneLevelStrategy implements AttributeStrategy<Integer> {
    @Override
    public Integer getValue(Class attrType, List attrAnnotations) {
        return (int) ((Math.random()*Math.pow(10,10)));
    }
}
