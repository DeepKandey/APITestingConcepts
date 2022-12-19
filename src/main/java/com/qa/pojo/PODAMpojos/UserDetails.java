package com.qa.pojo.PODAMpojos;

import lombok.Builder;
import lombok.Data;
import uk.co.jemos.podam.common.*;

import java.util.HashMap;
import java.util.List;

@Builder
@Data
public class UserDetails {
//    @PodamExclude
    @PodamStrategyValue(value = NameLevelStrategy.class)
    private String name;
    @PodamIntValue(minValue = 5, maxValue = 50)
    private int age;
    private Address address;
    @PodamCollection(nbrElements = 2, collectionElementStrategy = PhoneLevelStrategy.class)
    private List<Integer> phoneNumbers;

    @PodamDoubleValue(minValue = 5.67, maxValue = 14.67)
    private double doubleFieldWithMinAndMaxValue;

    @PodamCollection(nbrElements = 2, mapElementStrategy = MapLevelStrategy.class)
    private HashMap<Integer, String> hashMap;


    UserDetails(String name, int age) {
        this.name = name;
        this.age = age;
    }

    UserDetails(String name, int age, Address address, List<Integer> phoneNumbers, double doubleFieldWithMinAndMaxValue, HashMap<Integer, String> hashMap) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
        this.doubleFieldWithMinAndMaxValue = doubleFieldWithMinAndMaxValue;
        this.hashMap = hashMap;
    }

    public UserDetails() {

    }
}
