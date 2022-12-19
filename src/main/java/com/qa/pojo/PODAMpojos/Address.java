package com.qa.pojo.PODAMpojos;

import lombok.Getter;
import uk.co.jemos.podam.common.PodamIntValue;

@Getter
//@Setter
public class Address {
    private int streetNumber;
    private String streetLine;
    private String city;
    @PodamIntValue(minValue = 5, maxValue = 5)
    private int zipCode;


    Address(int streetNumber, String streetLine) {
        this.streetNumber = streetNumber;
        this.streetLine = streetLine;
    }


    Address(int streetNumber, String streetLine, String city, int zipCode) {
        this.streetNumber = streetNumber;
        this.streetLine = streetLine;
        this.city = city;
        this.zipCode = zipCode;
    }
}
