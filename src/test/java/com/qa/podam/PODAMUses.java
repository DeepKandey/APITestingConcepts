package com.qa.podam;

import com.qa.pojo.PODAMpojos.GenericPojo;
import com.qa.pojo.PODAMpojos.UserDetails;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class PODAMUses {

    public static void main(String[] args) {

        PodamFactory factory = new PodamFactoryImpl();

        /*
           1. Discussed below uses
            manufacturePojo
            manufacturePojoWithFullData

            Annotations for Attrbute Level Strategy
             @PodamStrategyValue
             @PodamIntValue
             @PodamCollection for List
             @PodamDoubleValue
             @PodamCollection for map
         */

        UserDetails userDetails2 = factory.manufacturePojoWithFullData(UserDetails.class);
        System.out.println(userDetails2.getName() + " " + userDetails2.getAge() + " "
                + userDetails2.getAddress().getStreetNumber() + " " + userDetails2.getAddress().getStreetLine() + " " + userDetails2.getAddress().getCity() + " " + userDetails2.getAddress().getZipCode() + " " + userDetails2.getPhoneNumbers()
                + " " + userDetails2.getDoubleFieldWithMinAndMaxValue() + " " + userDetails2.getHashMap());

        UserDetails userDetails = factory.manufacturePojo(UserDetails.class);
        System.out.println(userDetails.getName() + " " + userDetails.getAge());

        // 2. Generic Types
        GenericPojo pojo = factory.manufacturePojo(GenericPojo.class, Double.class, String.class);
        System.out.println(pojo.getFirstValue() + " " + pojo.getSecondValue());

        //3. PODAM exclude
        UserDetails userDetails3 = new UserDetails();
        userDetails3.setName("Anil");
        factory.populatePojo(userDetails3);
        System.out.println(userDetails3.getName() + " " + userDetails3.getAge() + " "
                + userDetails3.getAddress().getStreetNumber() + " " + userDetails3.getAddress().getStreetLine() + " "
                + userDetails3.getAddress().getCity() + " " + userDetails3.getAddress().getZipCode() + " "
                + userDetails3.getPhoneNumbers()
                + " " + userDetails3.getDoubleFieldWithMinAndMaxValue() + " " + userDetails3.getHashMap());
    }
}
