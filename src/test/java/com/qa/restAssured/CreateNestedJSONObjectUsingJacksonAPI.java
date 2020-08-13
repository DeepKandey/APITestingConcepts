/**
 * @author Deepak Rai
 */
package com.qa.restAssured;

import java.util.Iterator;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CreateNestedJSONObjectUsingJacksonAPI {

	@Test
	private void createNestedJSONObjectUsingJacksonAPItest() throws JsonProcessingException {

		// ObjectMapper object
		ObjectMapper objMapper = new ObjectMapper();

		// booking Details
		ObjectNode bookingDetails = objMapper.createObjectNode();
		bookingDetails.put("firstname", "Jim");
		bookingDetails.put("lastname", "Brown");
		bookingDetails.put("totalprice", 111);
		bookingDetails.put("depositpaid", true);

		// booking date details
		ObjectNode bookingDatesDetails = objMapper.createObjectNode();
		bookingDatesDetails.put("checkin", "2021-07-01");
		bookingDatesDetails.put("checkout", "2021-08-01");

		bookingDetails.set("bookingdates", bookingDatesDetails);
		bookingDetails.put("additionalneeds", "Breakfast");

		// To print created JSON object
		String createdPlainJSONObject = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookingDetails);
		System.out.println("Created plain JSON object is: \n" + createdPlainJSONObject);

		// We can retrieve field value by passing field name.
		String firstName = bookingDetails.get("firstname").asText();
		System.out.println("\nFirst Name is: " + firstName);

		Boolean depositpaid = bookingDetails.get("depositpaid").asBoolean();
		System.out.println("Deposit paid is: " + depositpaid);

		// To retrieve value of nested ObjectNode
		String checkindate = bookingDetails.get("bookingdates").get("checkin").asText();
		System.out.println("checkin date is: " + checkindate);

		// To get size of ObjectNode means field names count
		System.out.println("Count of fields in ObjectNode: " + bookingDetails.size());

		// iterate over field name
		Iterator<String> allFieldNames = bookingDetails.fieldNames();
		System.out.println("\nField names are: ");
		while (allFieldNames.hasNext()) {
			System.out.println(allFieldNames.next());
		}

		// iterate over field values
		Iterator<JsonNode> allFieldValues = bookingDetails.elements();
		System.out.println("\nField Values are: ");
		while (allFieldNames.hasNext()) {
			System.out.println(allFieldValues.next());
		}

		// retrieve all key-value pair
		Iterator<Entry<String, JsonNode>> allFieldsAndValues = bookingDetails.fields();
		System.out.println("\nAll fields and their values: ");
		while (allFieldsAndValues.hasNext()) {
			Entry<String, JsonNode> node = allFieldsAndValues.next();
			System.out.println("key is: " + node.getKey() + " , " + "Value is: " + node.getValue());
		}

		// remove a field from JSON object or ObjectNode
		String removedFieldValue = bookingDetails.remove("firstname").asText();
		System.out.println("\nValue of removed field is: " + removedFieldValue);
		String removedJSONObject = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookingDetails);
		System.out.println("After removing field, JSON object is: \n" + removedJSONObject);

		// update field from JSON Object or ObjectNode
		// To replace a field value, use put() method for non ObjectNode type and
		// replace() or set() for ObjectNode
		bookingDetails.put("firstname", "Deepak");
		bookingDetails.put("lastname", "Rai");
		String updatedJsonObject = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookingDetails);
		System.out.println("\nAfter updating field , JSON Object is : \n" + updatedJsonObject);

	}
}