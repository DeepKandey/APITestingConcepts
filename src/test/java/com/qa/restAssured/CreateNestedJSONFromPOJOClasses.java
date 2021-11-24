/* @author Deepak Rai */
package com.qa.restAssured;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.qa.util.LoggerUtil.log;

public class CreateNestedJSONFromPOJOClasses {

  @Test
  private void createNestedJSONFromPOJOClassesTest() throws IOException {

    NestedPOJO nestedPOJO = new NestedPOJO();
    nestedPOJO.setCompanyName("Sync");
    nestedPOJO.setCompanyHOCity("Pune");
    nestedPOJO.setCompanyCEO("Deepak");

    List<String> supportedSalaryBanks = new ArrayList<>();
    supportedSalaryBanks.add("HDFC");
    supportedSalaryBanks.add("ICICI");
    supportedSalaryBanks.add("AXIS");
    nestedPOJO.setSupportedSalaryBanks(supportedSalaryBanks);

    List<Integer> pinCodeOfCityOffice = new ArrayList<>();
    pinCodeOfCityOffice.add(560037);
    pinCodeOfCityOffice.add(360034);
    pinCodeOfCityOffice.add(456343);
    nestedPOJO.setPinCodeOfCityOffice(pinCodeOfCityOffice);

    // Just create an object of POJO class
    Employee employeeObjAsRequest1 = new Employee();
    employeeObjAsRequest1.setFirstName("Gunjan");
    employeeObjAsRequest1.setLastName("Saxena");
    employeeObjAsRequest1.setAge(29);
    employeeObjAsRequest1.setGender("Male");
    employeeObjAsRequest1.setSalary(3434343);
    employeeObjAsRequest1.setMarried(false);

    // Just create an object of POJO class
    Employee employeeObjAsRequest2 = new Employee();
    employeeObjAsRequest2.setFirstName("Deepak");
    employeeObjAsRequest2.setLastName("Rai");
    employeeObjAsRequest2.setAge(29);
    employeeObjAsRequest2.setGender("Male");
    employeeObjAsRequest2.setSalary(3434343);
    employeeObjAsRequest2.setMarried(false);

    // Just create an object of POJO class
    Employee employeeObjAsRequest3 = new Employee();
    employeeObjAsRequest3.setFirstName("Pankaj");
    employeeObjAsRequest3.setLastName("Yadav");
    employeeObjAsRequest3.setAge(29);
    employeeObjAsRequest3.setGender("Male");
    employeeObjAsRequest3.setSalary(3434343);
    employeeObjAsRequest3.setMarried(false);

    // Create a list of employees
    List<Employee> allEmployeesAsRequest = new ArrayList<>(Arrays.asList(employeeObjAsRequest1, employeeObjAsRequest2, employeeObjAsRequest3));
    nestedPOJO.setEmployee(allEmployeesAsRequest);

    Contractors seema = new Contractors();
    seema.setFirstName("Seema");
    seema.setLastName("Singh");
    seema.setContractFrom("Jan-2019");
    seema.setContractTo("JAN-2025");

    Contractors hari = new Contractors();
    hari.setFirstName("Hari");
    hari.setLastName("Prasad");
    hari.setContractFrom("Jan-2017");
    hari.setContractTo("JAN-2030");

    List<Contractors> allContractors = new ArrayList<>();
    allContractors.add(seema);
    allContractors.add(hari);
    nestedPOJO.setContractors(allContractors);

    CompanyPFDetails companyPFDetails = new CompanyPFDetails();
    companyPFDetails.setPfName("XYZ");
    companyPFDetails.setPfCity("Bengaluru");
    companyPFDetails.setPfYear(2012);
    companyPFDetails.setNoOfEmployees(10);
    nestedPOJO.setCompanyPFDetails(companyPFDetails);

    // POJO to JSON string
    ObjectMapper objMapper = new ObjectMapper();
    String nestedJSONPayLoad =
        objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(nestedPOJO);
    log(nestedJSONPayLoad);

    // write POJO to JSON File
    String userDir = System.getProperty("user.dir");
    File outPutJsonFile = new File(userDir + "\\src\\test\\resources\\NestedJSONPayload.json");
    objMapper.writerWithDefaultPrettyPrinter().writeValue(outPutJsonFile, nestedPOJO);
  }
}

@Getter
@Setter
class Contractors {
  private String firstName;
  private String lastName;
  private String contractFrom;
  private String contractTo;
}

@Setter
@Getter
class CompanyPFDetails {
  private String pfName;
  private String pfCity;
  private int pfYear;
  private int noOfEmployees;
}

@Getter
@Setter
class NestedPOJO {
  private String companyName;
  private String companyHOCity;
  private String companyCEO;
  private List<String> supportedSalaryBanks;
  private List<Integer> pinCodeOfCityOffice;
  List<Employee> employee;
  List<Contractors> contractors;
  CompanyPFDetails companyPFDetails;
}
