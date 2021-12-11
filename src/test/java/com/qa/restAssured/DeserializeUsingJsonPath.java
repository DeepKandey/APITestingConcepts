package com.qa.restAssured;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class DeserializeUsingJsonPath {

    @Test
    public void deserializeWithJsonPath(){
        String jsonObject = """
               {
               "firstName": "Lothaire",
               "lastName": "Benazet",
               "gender": "Male"
                }""";

        JsonPath jsonPath = JsonPath.from(jsonObject);

        Employee employee = jsonPath.getObject("", Employee.class);
        System.out.println("First name is : "+ employee.getFirstName());
        System.out.println("Last name is : "+ employee.getLastName());
        System.out.println("Gender is : "+ employee.getGender());

        Employee employee1 = jsonPath.getObject("$", Employee.class);
        System.out.println("First name is : "+ employee1.getFirstName());
        System.out.println("Last name is : "+ employee1.getLastName());
        System.out.println("Gender is : "+ employee1.getGender());
    }
}
