package com.qa.restAssured;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.qa.util.LoggerUtil.log;

public class SerializeAndDeserializeUsingJackson {

  @Test
  private void createJSONUsingPOJOTest() throws JsonProcessingException {

    Employee employeeObjAsRequest1 = new Employee();
    employeeObjAsRequest1.setFirstName("Gunjan");
    employeeObjAsRequest1.setLastName("Saxena");
    employeeObjAsRequest1.setAge(29);
    employeeObjAsRequest1.setGender("Male");
    employeeObjAsRequest1.setSalary(3434343);
    employeeObjAsRequest1.setMarried(false);

    Employee employeeObjAsRequest2 = new Employee();
    employeeObjAsRequest2.setFirstName("Deepak");
    employeeObjAsRequest2.setLastName("Rai");
    employeeObjAsRequest2.setAge(29);
    employeeObjAsRequest2.setGender("Male");
    employeeObjAsRequest2.setSalary(3434343);
    employeeObjAsRequest2.setMarried(false);

    Employee employeeObjAsRequest3 = new Employee();
    employeeObjAsRequest3.setFirstName("Pankaj");
    employeeObjAsRequest3.setLastName("Yadav");
    employeeObjAsRequest3.setAge(29);
    employeeObjAsRequest3.setGender("Male");
    employeeObjAsRequest3.setSalary(3434343);
    employeeObjAsRequest3.setMarried(false);

    // Converting a Java class object to a JSON pay load as string
    ObjectMapper objectMapper = new ObjectMapper();
    // POJO as JSON
    String employeeJSON =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeObjAsRequest1);
    log("\nEmployee POJO as JSON: \n" + employeeJSON);

    // POJO as JSON Array using ArrayNode
    ArrayNode node =
        objectMapper
            .createArrayNode()
            .addPOJO(employeeObjAsRequest1)
            .addPOJO(employeeObjAsRequest2)
            .addPOJO(employeeObjAsRequest3);
    String employeeJSONArray =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    log("\nEmployee POJO as JSON Array using ArrayNode: \n" + employeeJSONArray);

    // Converting Employee JSON string to Employee class object
    Employee employeeObjAsResponse = objectMapper.readValue(employeeJSON, Employee.class);
    log("\nFirst Name of employee : " + employeeObjAsResponse.getFirstName());
    log("Last Name of employee : " + employeeObjAsResponse.getLastName());
    log("Age of employee : " + employeeObjAsResponse.getAge());
    log("Gender of employee : " + employeeObjAsResponse.getGender());
    log("Salary of employee : " + employeeObjAsResponse.getSalary());
    log("Marital status of employee : " + employeeObjAsResponse.isMarried());

    // Create a list of employees
    List<Employee> allEmployeesAsRequest =
        new ArrayList<>(
            Arrays.asList(employeeObjAsRequest1, employeeObjAsRequest2, employeeObjAsRequest3));

    // Converting a Java class object(ArrayList) to a JSON Array pay load as string
    // (Serialize)
    String employeeListJSONArray =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allEmployeesAsRequest);
    log("\nList of Employees POJO as JSON Array\n" + employeeListJSONArray);

    // Converting Employee JSON Array string to Employee class object(De-serialize)
    List<Employee> allEmployeesAsResponse =
        objectMapper.readValue(employeeListJSONArray, new TypeReference<>() {});

    System.out.println("-----------Loop Employee list--------");
    for (Employee employee : allEmployeesAsResponse) {
      log("\nFirst Name of employee : " + employee.getFirstName());
      log("Last Name of employee : " + employee.getLastName());
      log("Age of employee : " + employee.getAge());
      log("Gender of employee : " + employee.getGender());
      log("Salary of employee : " + employee.getSalary());
      log("Marital status of employee : " + employee.isMarried());
    }
  }
}

@Getter
@Setter
class Employee {
  private String firstName;
  private String lastName;
  private String gender;
  private int age;
  private double salary;
  private boolean married;
}
