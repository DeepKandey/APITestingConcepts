package com.qa.restAssured.jsonAssert;

import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class JsonComparison {

@Test
public void exactSameJson(){
    String jsonObject1 = """
            {
              "firstName" : "Deepak",
              "lastName": "Rai"
            }""";

    String jsonObject2 = """
            {
              "firstName" : "Deepak",
              "lastName": "Rai"
            }""";

    // Lenient mode - extensible and no strict ordering
    JSONAssert.assertEquals(jsonObject1,jsonObject2, JSONCompareMode.LENIENT);

}

@Test
    public void exactSameJsonWithDifferentOrder(){
    String jsonObject1 = """
            {
              "lastName": "Rai",
              "firstName" : "Deepak",

            }""";

    String jsonObject2 = """
            {
              "firstName" : "Deepak",
              "lastName": "Rai"
            }""";

    // Lenient mode - extensible and no strict ordering
    JSONAssert.assertEquals(jsonObject1,jsonObject2, JSONCompareMode.LENIENT);
}

@Test
    public void sameFieldWithDifferentValues(){
    String jsonObject1 = """
            {
              "lastName": "Rai",
              "firstName" : "Deepak",

            }""";

    String jsonObject2 = """
            {
              "firstName" : "Deepak",
              "lastName": "kumar"
            }""";

    // Lenient mode - extensible and no strict ordering
    JSONAssert.assertEquals(jsonObject1,jsonObject2, JSONCompareMode.LENIENT);
}

@Test
    public void unmatchedDataType(){
    String jsonObject1 = """
            {
              "lastName": "Mahajan",
              "firstName": "Amod",
              "age": "18"
            }""";

    String jsonObject2 = """
            {
              "lastName": "Mahajan",
              "firstName": "Amod",
              "age": 18
            }""";

    // First json has 18 as string while second json has 18 as int
    JSONAssert.assertEquals(jsonObject1,jsonObject2,JSONCompareMode.LENIENT);

}

@Test
    public void strictMatchExamples1(){
    String jsonObject1 = """
            {
              "firstName" : "Amod",
              "lastName": "Mahajan"
            }""";

    String jsonObject2 = """
            {
              "firstName" : "Amod",
              "lastName": "Mahajan",
              "age": 28
            }""";

    JSONAssert.assertEquals(jsonObject1,jsonObject2,JSONCompareMode.STRICT);
}

@Test
    public void lenientMatchWithJsonObjects(){
    JSONObject jsonObject1= new JSONObject();
    jsonObject1.put("firstName","Deepak");
    jsonObject1.put("lastName","Rai");

    JSONObject jsonObject2 = new JSONObject();
    jsonObject2.put("firstName","Deepak");
    jsonObject2.put("lastName","Rai");

    JSONAssert.assertEquals(jsonObject1,jsonObject2,JSONCompareMode.LENIENT);
}

@Test
    public void matchJsonObjectWithBooleanCompareMode(){
    JSONObject jsonObject1= new JSONObject();
    jsonObject1.put("firstName","Deepak");
    jsonObject1.put("lastName","Rai");

    JSONObject jsonObject2= new JSONObject();
    jsonObject2.put("firstName","Deepak");
    jsonObject2.put("lastName","Rai");

    JSONAssert.assertEquals("Json objects are not equal",jsonObject1,jsonObject2,false);
}

@Test
    public void errorHandlingJsonAssert(){
    JSONObject jsonObject1= new JSONObject();
    jsonObject1.put("firstName","Deepak");
    jsonObject1.put("lastName","Rai");

    JSONObject jsonObject2= new JSONObject();
    jsonObject2.put("firstName","Rahul");
    jsonObject2.put("lastName","Rai");

    try{
        JSONAssert.assertEquals(jsonObject1,jsonObject2,false);
    }catch (Error e){
        System.out.println("Error occurred as JSON are not same.");
    }
}

@Test
    public void lenientMatchWithJsonArrays(){
    String jsonObject1 = """
            [
              "Amod","Mukesh","Ravi"
            ]""";

    String jsonObject2 = """
            [
            "Amod","Mukesh" ,"Ravi","Animesh"
            ]""";

    JSONAssert.assertEquals(jsonObject1,jsonObject2,JSONCompareMode.LENIENT);
}


@Test
public void strictMatchWithJsonArrays(){
    String jsonObject1 = """
        [
          "Amod","Mukesh","Ravi"
        ]""";

    String jsonObject2 = """
        [
        "Mukesh" ,"Ravi","Amod"
        ]""";

    JSONAssert.assertEquals(jsonObject1,jsonObject2,JSONCompareMode.STRICT);
}
}
