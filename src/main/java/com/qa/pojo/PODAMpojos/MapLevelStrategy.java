package com.qa.pojo.PODAMpojos;

import uk.co.jemos.podam.common.AttributeStrategy;

import java.util.HashMap;
import java.util.List;

public class MapLevelStrategy implements AttributeStrategy<String> {
    @Override
    public String getValue(Class attrType, List attrAnnotations) {
        HashMap map = new HashMap();
        map.put(1, "Shyam");
        map.put(2, "Rohit");
        map.put(3, "Pankaj");
        map.put(4, "Himanshu");

        return (String) map.values().stream().findAny().get();
    }
}
