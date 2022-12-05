package com.qa.restAssured;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

public class CompareTwoJsonArray {

    String jsonArray1;
    String jsonArray2;
    ObjectMapper objectMapper;
    JsonNode jsonNode1;
    JsonNode jsonNode2;

    @Test
    public void compareTwoJsonArrays() throws JsonProcessingException {


        jsonArray1 = """
                [
                  {
                    "lastName": "Mahajan",
                    "firstName": "Amod",
                    "address": {
                      "city": "Katihar",
                      "state": "Bihar"
                    }
                  },
                  {
                    "lastName": "Animesh",
                    "firstName": "Prashant",
                    "address": {
                      "city": "Kolkata",
                      "state": "WB"
                    }
                  }
                ]""";

        jsonArray2 = """
                [
                  {
                    "lastName": "Mahajan",
                    "firstName": "Amod",
                    "address": {
                      "city": "Katihar",
                      "state": "Bihar"
                    }
                  },
                  {
                    "lastName": "Animesh",
                    "firstName": "Prashant",
                    "address": {
                      "city": "Kolkata",
                      "state": "WB"
                    }
                  }
                ]""";

        objectMapper = new ObjectMapper();
        jsonNode1 = objectMapper.readTree(jsonArray1);
        jsonNode2 = objectMapper.readTree(jsonArray2);

        // Checking if both json objects are same
        System.out.println(jsonNode1.equals(jsonNode2));
    }

    @Test
    public void compareTwoJsnArraysWithDifferentOrderOfElements() throws JsonProcessingException {
        // Change in order of elements in array will impact, and it will not be considered same
        jsonArray1 = """
                [
                  {
                    "lastName": "Mahajan",
                    "firstName": "Amod",
                    "address": {
                      "city": "Katihar",
                      "state": "Bihar"
                    }
                  },
                  {
                    "lastName": "Animesh",
                    "firstName": "Prashant",
                    "address": {
                      "city": "Kolkata",
                      "state": "WB"
                    }
                  }
                ]""";

        jsonArray2 = """
                [
                  {
                    "lastName": "Animesh",
                    "firstName": "Prashant",
                    "address": {
                      "city": "Kolkata",
                      "state": "WB"
                    }
                  },
                  {
                    "lastName": "Mahajan",
                    "firstName": "Amod",
                    "address": {
                      "city": "Katihar",
                      "state": "Bihar"
                    }
                  }
                ]""";

        objectMapper = new ObjectMapper();
        jsonNode1 = objectMapper.readTree(jsonArray1);
        jsonNode2 = objectMapper.readTree(jsonArray2);

        System.out.println(jsonNode1.equals(jsonNode2));
    }
}
