package com.qa.restAssured;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

public class CompareJsonObjects {

  String jsonObject1;
  String jsonObject2;
  ObjectMapper objectMapper;
  JsonNode jsonNode1;
  JsonNode jsonNode2;

  @Test
  public void compareTwoJsonObjects() throws JsonProcessingException {
    jsonObject1 =
            """
                    {
                      "firstName" : "Amod",
                      "lastName" : "Mahajan"
                    }""";

    jsonObject2 =
            """
                    {
                      "firstName" : "Amod",
                      "lastName" : "Mahajan"
                    }""";

    objectMapper = new ObjectMapper();
    jsonNode1 = objectMapper.readTree(jsonObject1);
    jsonNode2 = objectMapper.readTree(jsonObject2);

    // checking if both json objects are same
    System.out.println(jsonNode1.equals(jsonNode2));

  }

  @Test
  public void compareTwoJsonObjectsWithDifferentOrderOfRootElements() throws JsonProcessingException {

    // Nested Json objects can also be compared with equals method
    // Change in order of elements does not impact
    jsonObject1 = """
            {
              "firstName" : "Amod",
              "lastName" : "Mahajan"
            }""";

    jsonObject2 = """
            {
              "lastName" : "Mahajan",
              "firstName" : "Amod"
            }""";

    objectMapper= new ObjectMapper();
    jsonNode1= objectMapper.readTree(jsonObject1);
    jsonNode2= objectMapper.readTree(jsonObject2);

    // checking if both json objects are same
    System.out.println(jsonNode1.equals(jsonNode2));

  }

  @Test
  public void compareTwoNestedJsonObjects() throws JsonProcessingException {

    // Nested json objects can also be compared with equals method
    jsonObject1 = """
            {
              "lastName": "Mahajan",
              "firstName": "Amod",
              "address": {
                "city": "Katihar",
                "state": "Bihar"
              }
            }""";
    jsonObject2 = """
            {
              "lastName": "Mahajan",
              "firstName": "Amod",
              "address": {
                "city": "Katihar",
                "state": "Bihar"
              }
            }""";

    objectMapper = new ObjectMapper();
    jsonNode1 = objectMapper.readTree(jsonObject1);
    jsonNode2 = objectMapper.readTree(jsonObject2);

    System.out.println(jsonNode1.equals(jsonNode2));
  }
}
