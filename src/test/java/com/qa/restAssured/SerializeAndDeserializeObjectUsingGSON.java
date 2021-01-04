/** @author Deepak Rai */
package com.qa.restAssured;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.testng.annotations.Test;

public class SerializeAndDeserializeObjectUsingGSON {
  /**
   * {@summary erializeJavaToJsonObjectUsingGSON}
   *
   * @param
   * @return
   * @author deepak rai
   */
  @Test
  private void SerializeAndDeserializeObjectUsingGSONtest() throws IOException {

    // Create a Employee java object
    Employee employeeObject = new Employee();
    employeeObject.setFirstName("Deepak");
    employeeObject.setLastName("Rai");
    employeeObject.setAge(29);
    employeeObject.setSalary(10987.77);
    employeeObject.setMarried(false);
    employeeObject.setGender("M");

    // create GSON object
    Gson gsonObj = new Gson();
    // toJson(object src) method converts Java object to JSON object
    String employeeJsonString = gsonObj.toJson(employeeObject);
    System.out.println("Non-pretty JSON String");
    System.out.println(employeeJsonString);

    // We can create configurable Gson instance using GsonBuilder class
    Gson gsonBuilderObj = new GsonBuilder().setPrettyPrinting().create();
    String employeeJsonStringUsingGsonBuilder = gsonBuilderObj.toJson(employeeObject);
    System.out.println("\npretty JSON String");
    System.out.println(employeeJsonStringUsingGsonBuilder);

    // File write
    String userDir = System.getProperty("user.dir");
    File outputJsonFile =
        new File(userDir + "\\src\\test\\resources\\EmployeePayloadUsingGson.json");
    FileWriter fileWriter = new FileWriter(outputJsonFile);
    gsonBuilderObj.toJson(employeeObject, fileWriter);
    fileWriter.flush();

    // Pass JSON string and POJO class for deserialize
    Employee employeeObjResponseFromJSONString =
        gsonObj.fromJson(employeeJsonString, Employee.class);

    // Now use getter method to retrieve values
    String firsName = employeeObjResponseFromJSONString.getFirstName();
    String lastName = employeeObjResponseFromJSONString.getLastName();
    String gender = employeeObjResponseFromJSONString.getGender();
    int age = employeeObjResponseFromJSONString.getAge();
    double salary = employeeObjResponseFromJSONString.getSalary();
    boolean married = employeeObjResponseFromJSONString.getMarried();

    System.out.println("\nDetails of Employee is as below:-");
    System.out.println("First Name : " + firsName);
    System.out.println("Last Name : " + lastName);
    System.out.println("Gender : " + gender);
    System.out.println("Age : " + age);
    System.out.println("Salary : " + salary);
    System.out.println("Married : " + married);

    // deserialize from json file
    File inputJsonFile =
        new File(userDir + "\\src\\test\\resources\\EmployeePayloadUsingGson.json");
    FileReader fileReader = new FileReader(inputJsonFile);
    Employee employeeObjResponseFromJSONFile = gsonObj.fromJson(fileReader, Employee.class);

    // Now use getter method to retrieve values
    String firsName1 = employeeObjResponseFromJSONFile.getFirstName();
    String lastName1 = employeeObjResponseFromJSONFile.getLastName();
    String gender1 = employeeObjResponseFromJSONFile.getGender();
    int age1 = employeeObjResponseFromJSONFile.getAge();
    double salary1 = employeeObjResponseFromJSONFile.getSalary();
    boolean married1 = employeeObjResponseFromJSONFile.getMarried();

    System.out.println("\nDetails of Employee from json file is as below:-");
    System.out.println("First Name : " + firsName1);
    System.out.println("Last Name : " + lastName1);
    System.out.println("Gender : " + gender1);
    System.out.println("Age : " + age1);
    System.out.println("Salary : " + salary1);
    System.out.println("Married : " + married1);
  }
}
