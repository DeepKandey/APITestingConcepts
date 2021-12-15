package com.qa.restAssured;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

public class ValidateJsonSchemaWithoutRestAssured {

    @Test
    public void verifyJsonSchemaWithoutRestAssured(){
        String json = """
                {
                    "firstname" : "Jim",
                    "lastname" : "Brown",
                    "totalprice" : 111,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2018-01-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : "Breakfast"
                }""";

        MatcherAssert.assertThat(json, JsonSchemaValidator.matchesJsonSchema(this.getClass().getResourceAsStream("/SampleJsonSchemaCreateBooking.json")));
    }
}
