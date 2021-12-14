package com.qa.restAssured.jsonAssert;

import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class ComparePortionOfJsonObjects {

    @Test
    public void comparePortionOfJsonObjects(){
        JSONObject jsonObject1 = new JSONObject("""
                {\r
                  "lastName": "Animesh",\r
                  "firstName": "Prashant",\r
                  "address": {\r
                    "city": "Kolkata",\r
                    "state": "WB"\r
                  }\r
                }""");
        JSONObject jsonObject2 = new JSONObject("""
                {\r
                  "lastName": "Animesh",\r
                  "firstName": "Prashant",\r
                  "communicationAddress": {\r
                    "city": "Kolkata",\r
                    "state": "WB"\r
                  },\r
                  "skills": [\r
                    "CA",\r
                    "BCOM"\r
                  ]\r
                }""");


        // Want to assert address objects are same in both
        // Since "address" and "communicationAddress" are JSON objects, so we need to use proper method i.e. getJSONObjects
        JSONAssert.assertEquals(jsonObject1.getJSONObject("address"),jsonObject2.getJSONObject("communicationAddress"), JSONCompareMode.LENIENT);
    }

    @Test
    public void comparePortionOfJsonArrays(){
        JSONArray jSONArray1 = new JSONArray("""
                [
                  {
                    "id": 1,
                    "first_name": "Giavani",
                    "last_name": "Skellorne"
                  },
                  {
                    "id": 2,
                    "first_name": "Patton",
                    "last_name": "Buchett"
                  }
                ]""");

        JSONArray jSONArray2 = new JSONArray("""
                [
                  {
                    "id": 1,
                    "first_name": "Giavani",
                    "last_name": "Skellorne"
                  },
                  {
                    "id": 2,
                    "first_name": "Harland",
                    "last_name": "Brookwood"
                  },
                  {
                    "id": 3,
                    "first_name": "Leigh",
                    "last_name": "Gatteridge"
                  }
                ]""");

        // Want to assert address objects are same in both
        // Since index elements in both arrays are JSON objects so using proper method i.e. getJSONObject
        JSONAssert.assertEquals(jSONArray1.getJSONObject(0),jSONArray2.getJSONObject(0),JSONCompareMode.STRICT);
    }
}
