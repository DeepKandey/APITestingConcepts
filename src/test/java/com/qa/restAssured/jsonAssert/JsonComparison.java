package com.qa.restAssured.jsonAssert;

import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class JsonComparison {

    @Test
    public void exactSameJson() {
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
        JSONAssert.assertEquals(jsonObject1, jsonObject2, JSONCompareMode.LENIENT);

    }

    @Test
    public void exactSameJsonWithDifferentOrder() {
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
        JSONAssert.assertEquals(jsonObject1, jsonObject2, JSONCompareMode.LENIENT);
    }

    @Test
    public void sameFieldWithDifferentValues() {
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
        JSONAssert.assertEquals(jsonObject1, jsonObject2, JSONCompareMode.LENIENT);
    }

    @Test
    public void unmatchedDataType() {
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
        JSONAssert.assertEquals(jsonObject1, jsonObject2, JSONCompareMode.LENIENT);

    }

    @Test
    public void strictMatchExamples1() {
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

        JSONAssert.assertEquals(jsonObject1, jsonObject2, JSONCompareMode.STRICT);
    }

    @Test
    public void lenientMatchWithJsonObjects() {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("firstName", "Deepak");
        jsonObject1.put("lastName", "Rai");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("firstName", "Deepak");
        jsonObject2.put("lastName", "Rai");

        JSONAssert.assertEquals(jsonObject1, jsonObject2, JSONCompareMode.LENIENT);
    }

    @Test
    public void matchJsonObjectWithBooleanCompareMode() {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("firstName", "Deepak");
        jsonObject1.put("lastName", "Rai");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("firstName", "Deepak");
        jsonObject2.put("lastName", "Rai");

        JSONAssert.assertEquals("Json objects are not equal", jsonObject1, jsonObject2, false);
    }

    @Test
    public void errorHandlingJsonAssert() {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("firstName", "Deepak");
        jsonObject1.put("lastName", "Rai");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("firstName", "Rahul");
        jsonObject2.put("lastName", "Rai");

        try {
            JSONAssert.assertEquals(jsonObject1, jsonObject2, false);
        } catch (Error e) {
            System.out.println("Error occurred as JSON are not same.");
        }
    }

    @Test
    public void lenientMatchWithJsonArrays() {
        String jsonObject1 = """
                [
                  "Amod","Mukesh","Ravi"
                ]""";

        String jsonObject2 = """
                [
                "Amod","Mukesh" ,"Ravi","Animesh"
                ]""";

        JSONAssert.assertEquals(jsonObject1, jsonObject2, JSONCompareMode.LENIENT);
    }


    @Test
    public void strictMatchWithJsonArrays() {
        String jsonObject1 = """
                [
                  "Amod","Mukesh","Ravi"
                ]""";

        String jsonObject2 = """
                [
                "Mukesh" ,"Ravi","Amod"
                ]""";

        JSONAssert.assertEquals(jsonObject1, jsonObject2, JSONCompareMode.STRICT);
    }

    @Test
    public void lenientMatchWithJsonArrays_1() {
        String jsonObject1 =
                """
                        {
                          "attribute_value_map": [
                            {
                              "key": "0e640c7e-647e-4c47-8953-92fc088fc4b4",
                              "value": "5/8\\""
                            },
                            {
                              "key": "6dd2a6b8-dcf6-40e5-9ed6-35cc4dfb0fa3",
                              "value": "7/8\\""
                            },
                            {
                              "key": "d7f1392e-a642-4bdd-9c85-a7fcf7fc8fa1",
                              "value": "Other/Unknown"
                            },
                            {
                              "key": "0c971d6c-a358-41af-8da4-e5f8deab5ff9",
                              "value": "Other/Unknown"
                            },
                            {
                              "key": "71db56a7-1e0b-4aa0-af41-a32d1f78b97a",
                              "value": "<1 Year"
                            },
                            {
                              "key": "771f01f4-225b-40eb-888f-60a37e534165",
                              "value": "1 5/8\\""
                            },
                            {
                              "key": "a6249f72-c0c7-4755-9f3a-43b4d05afb86",
                              "value": "1/2\\""
                            },
                            {
                              "key": "6adc6ed3-e75a-4087-b6cc-8ecea7cd7528",
                              "value": "3/4\\""
                            },
                            {
                              "key": "531d77de-0fd2-41e7-831e-815788b9351e",
                              "value": "1 - 5 Years"
                            },
                            {
                              "key": "60402fa3-d0e6-4a9e-a06a-ad3b06f2f5d5",
                              "value": "1 1/8\\""
                            },
                            {
                              "key": "c34f7b55-b06a-451c-84fe-ea5f4eabdb38",
                              "value": "1 3/8\\""
                            }
                          ]
                        }
                         
                        """;

        String jsonObject2 = """
                {
                  "attribute_value_map": [
                    {
                      "key": "a6249f72-c0c7-4755-9f3a-43b4d05afb86",
                      "value": "1/2\\""
                    },
                    {
                      "key": "d7f1392e-a642-4bdd-9c85-a7fcf7fc8fa1",
                      "value": "Other/Unknown"
                    },
                    {
                      "key": "0c971d6c-a358-41af-8da4-e5f8deab5ff9",
                      "value": "Other/Unknown"
                    },
                    {
                      "key": "71db56a7-1e0b-4aa0-af41-a32d1f78b97a",
                      "value": "<1 Year"
                    },
                    {
                      "key": "60402fa3-d0e6-4a9e-a06a-ad3b06f2f5d5",
                      "value": "1 1/8\\""
                    },
                    {
                      "key": "771f01f4-225b-40eb-888f-60a37e534165",
                      "value": "1 5/8\\""
                    },
                    {
                      "key": "0e640c7e-647e-4c47-8953-92fc088fc4b4",
                      "value": "5/8\\""
                    },
                    {
                      "key": "6dd2a6b8-dcf6-40e5-9ed6-35cc4dfb0fa3",
                      "value": "7/8\\""
                    },
                    {
                      "key": "531d77de-0fd2-41e7-831e-815788b9351e",
                      "value": "1 - 5 Years"
                    },
                    {
                      "key": "c34f7b55-b06a-451c-84fe-ea5f4eabdb38",
                      "value": "1 3/8\\""
                    },
                    {
                      "key": "6adc6ed3-e75a-4087-b6cc-8ecea7cd7528",
                      "value": "3/4\\""
                    }
                  ]
                }
                                
                """;

        JSONAssert.assertEquals(jsonObject1, jsonObject2, JSONCompareMode.NON_EXTENSIBLE);
    }
}
