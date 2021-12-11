package com.qa.restAssured;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class SimpleAndNestedJsonArray {

    @Test
    public static void simpleJsonArray(){

    String jsonArrayString =
       """
    {
      "firstName": "Deepak",
      "lastName": "Rai",
      "address": [
        {
          "type": "permanent",
          "city": "Pune",
          "state": "MH"
        },
        {
          "type": "temp",
          "city": "Patna",
          "state": "BR"
        }
      ]
    }
    """;

    // Get JsonPath instance of above JSON string
    JsonPath jsonPath = JsonPath.from(jsonArrayString);

    // Since address holds a JSON array we can get particular indexed element using index
    String addressType1 = jsonPath.getString("address[0].type");
    System.out.println("Address type is : " + addressType1);

    String addressType2 = jsonPath.getString("address[1].type");
    System.out.println("Another address type is : " + addressType2);

    // We can get address types as a list as well
    List<String> allAddressTypes = jsonPath.getList("address.type");
    System.out.println("All address type as list: " + allAddressTypes);

    // We can get complete address array as List
    // Since it holds Json objects which can be a Map
    List<Map<String, Object>> allAddress = jsonPath.getList("address");
    for (Map<String, Object> address : allAddress) {
      System.out.println(address);
    }

    // Json Object contains json elements having key value pair. So, getting the elements as Map
    System.out.println("First index values of address array as Map: " + jsonPath.getMap("address[0]"));
  }

  @Test
  public static void nestedJsonArray(){

  String jsonArrayString = """
          {
            "firstName": "Deepak",
            "lastName": "Rai",
            "address": [
              [
                {
                  "type": "permanent",
                  "city": "Bengaluru",
                  "state": "KA"
                },
                {
                  "type": "temp",
                  "city": "Bhopal",
                  "state": "MP"
                }
              ],
              [
                {
                  "type": "communication",
                  "city": "Delhi",
                  "state": "DL"
                },
                {
                  "type": "old",
                  "city": "Kanpur",
                  "state": "UP"
                }
              ]
            ]
          }""";


  //Get JsonPath instance of above JSON string
  JsonPath jsonPath = JsonPath.from(jsonArrayString);

  // Since address holds nested JSON arrays we can get particular indexed element using index
  // followed by another index
  String addressType1 = jsonPath.getString("address[0][0].type");
		System.out.println("Address type is : "+addressType1);

  String addressType2 = jsonPath.getString("address[0][1].type");
		System.out.println("Second address type is : "+addressType2);

  String addressType3 = jsonPath.getString("address[1][0].type");
		System.out.println("Third type is : "+addressType3);

  String addressType4 = jsonPath.getString("address[1][1].type");
		System.out.println("Fourth address type is : "+addressType4);

  // We can get address types from first array of address
  List<String> allAddressTypesOfFirstElementOfArray = jsonPath.getList("address[0].type");
		System.out.println("All address types of first element of Array: "+allAddressTypesOfFirstElementOfArray);

  // We can get address types from second array of address
  List<String> allAddressTypesOfSecondElementOfArray = jsonPath.getList("address[1].type");
		System.out.println("All address types of second element of Array: "+allAddressTypesOfSecondElementOfArray);

  // We can get address types from all elements of array of address
  List<String> allAddressTypes = jsonPath.getList("address.type");
		System.out.println("All address types: "+allAddressTypes);

}
}
