package com.qa.restAssured.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.testng.annotations.Test;

public class EditJsonObjectOnFly {

    @Test
    public void quickEditToJSonObject() throws JsonProcessingException {
        String jsonObject = """
                {
                  "firstname": "Deepak",
                  "lastname": "Rai"
                }""";

        ObjectMapper mapper= new ObjectMapper();
        ObjectNode objectNode = mapper.readValue(jsonObject, ObjectNode.class);
        objectNode.put("role","admin");

        ObjectNode bookingDetails= mapper.createObjectNode();
        bookingDetails.put("firstName","Jim");
        bookingDetails.put("lastName","Brown");

        objectNode.set("bookingDetails", bookingDetails);
        System.out.println(objectNode.toPrettyString());
    }

    @Test
    public void addNewKeyInsideJSonObject() throws JsonProcessingException {
        String jsonObject = """
                {
                  "firstname" : "Deepak",
                  "lastname" : "Rai",
                  "bookingDetails" : {
                    "firstname" : "Jim",
                    "lastname" : "Brown"
                  }
                }""";

        ObjectMapper mapper= new ObjectMapper();
        ObjectNode objectNode = mapper.readValue(jsonObject, ObjectNode.class);

        objectNode.with("bookingDetails").put("gender","male");
        System.out.println(objectNode.toPrettyString());
    }

    @Test
    public void removeExistingKey() throws JsonProcessingException {
        String jsonObject = """
                {
                  "firstname" : "Deepak",
                  "lastname" : "Rai",
                  "bookingDetails" : {
                    "firstname" : "Jim",
                    "lastname" : "Brown"
                  }
                }""";

        ObjectMapper mapper= new ObjectMapper();
        ObjectNode objectNode = mapper.readValue(jsonObject, ObjectNode.class);

        objectNode.remove("firstname");
        objectNode.with("bookingDetails").remove("firstname");
        System.out.println(objectNode.toPrettyString());
    }
}
