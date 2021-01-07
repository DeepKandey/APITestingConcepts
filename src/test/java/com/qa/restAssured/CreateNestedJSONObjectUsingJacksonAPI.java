/** @author Deepak Rai */
package com.qa.restAssured;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Map.Entry;

import static com.qa.util.LoggerUtil.log;

public class CreateNestedJSONObjectUsingJacksonAPI {

  @Test
  private void createNestedJSONObjectUsingJacksonAPItest() throws JsonProcessingException {

    // ObjectMapper object
    ObjectMapper objMapper = new ObjectMapper();

    // booking Details
    ObjectNode bookingDetails = objMapper.createObjectNode();
    bookingDetails.put("firstname", "Jim");
    bookingDetails.put("lastname", "Brown");
    bookingDetails.put("totalPrice", 111);
    bookingDetails.put("depositPaid", true);

    // booking date details
    ObjectNode bookingDatesDetails = objMapper.createObjectNode();
    bookingDatesDetails.put("checkin", "2021-07-01");
    bookingDatesDetails.put("checkout", "2021-08-01");

    bookingDetails.set("bookingDates", bookingDatesDetails);
    bookingDetails.put("additionalNeeds", "Breakfast");

    // To print created JSON object
    String createdPlainJSONObject =
        objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookingDetails);
    log("Created plain JSON object is: \n" + createdPlainJSONObject);

    // We can retrieve field value by passing field name.
    String firstName = bookingDetails.get("firstname").asText();
    log("\nFirst Name is: " + firstName);

    Boolean depositPaid = bookingDetails.get("depositPaid").asBoolean();
    log("Deposit paid is: " + depositPaid);

    // To retrieve value of nested ObjectNode
    String checkindate = bookingDetails.get("bookingDates").get("checkin").asText();
    log("checkin date is: " + checkindate);

    // To get size of ObjectNode means field names count
    log("Count of fields in ObjectNode: " + bookingDetails.size());

    // iterate over field name
    Iterator<String> allFieldNames = bookingDetails.fieldNames();
    log("\nField names are: ");
    while (allFieldNames.hasNext()) {
      log(allFieldNames.next());
    }

    // iterate over field values
    Iterator<JsonNode> allFieldValues = bookingDetails.elements();
    log("\nField Values are: ");
    while (allFieldNames.hasNext()) {
      log(allFieldValues.next().asText());
    }

    // retrieve all key-value pair
    Iterator<Entry<String, JsonNode>> allFieldsAndValues = bookingDetails.fields();
    log("\nAll fields and their values: ");
    while (allFieldsAndValues.hasNext()) {
      Entry<String, JsonNode> node = allFieldsAndValues.next();
      log("key is: " + node.getKey() + " , " + "Value is: " + node.getValue());
    }

    // remove a field from JSON object or ObjectNode
    String removedFieldValue = bookingDetails.remove("firstname").asText();
    log("\nValue of removed field is: " + removedFieldValue);
    String removedJSONObject =
        objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookingDetails);
    log("After removing field, JSON object is: \n" + removedJSONObject);

    // update field from JSON Object or ObjectNode
    // To replace a field value, use put() method for non ObjectNode type and
    // replace() or set() for ObjectNode
    bookingDetails.put("firstname", "Deepak");
    bookingDetails.put("lastname", "Rai");
    String updatedJsonObject =
        objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookingDetails);
    log("\nAfter updating field , JSON Object is : \n" + updatedJsonObject);
  }
}