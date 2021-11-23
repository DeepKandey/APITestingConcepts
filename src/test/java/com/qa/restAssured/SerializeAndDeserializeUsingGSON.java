/* @author Deepak Rai */
package com.qa.restAssured;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.qa.util.LoggerUtil.log;

public class SerializeAndDeserializeUsingGSON {
  /**
   * {@summary erializeJavaToJsonObjectUsingGSON}

   * @author deepak rai
   */
  @Test
  private void SerializeAndDeserializeObjectUsingGSONTest() throws IOException {
    Employee employeeObject = new Employee();
    employeeObject.setFirstName("Deepak");
    employeeObject.setLastName("Rai");
    employeeObject.setAge(29);
    employeeObject.setSalary(10987.77);
    employeeObject.setMarried(false);
    employeeObject.setGender("M");

    Gson gsonObj = new Gson();
    // toJson(object src) method converts Java object to JSON object
    String employeeJsonString = gsonObj.toJson(employeeObject);
    log("Non-pretty JSON String");
    log(employeeJsonString);

    // We can create configurable Gson instance using GsonBuilder class
    Gson gsonBuilderObj = new GsonBuilder().setPrettyPrinting().create();
    String employeeJsonStringUsingGsonBuilder = gsonBuilderObj.toJson(employeeObject);
    log("\npretty JSON String");
    log(employeeJsonStringUsingGsonBuilder);

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
    boolean married = employeeObjResponseFromJSONString.isMarried();

    log("\nDetails of Employee is as below:-");
    log("First Name : " + firsName);
    log("Last Name : " + lastName);
    log("Gender : " + gender);
    log("Age : " + age);
    log("Salary : " + salary);
    log("Married : " + married);

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
    boolean married1 = employeeObjResponseFromJSONFile.isMarried();

    log("\nDetails of Employee from json file is as below:-");
    log("First Name : " + firsName1);
    log("Last Name : " + lastName1);
    log("Gender : " + gender1);
    log("Age : " + age1);
    log("Salary : " + salary1);
    log("Married : " + married1);
  }
}
