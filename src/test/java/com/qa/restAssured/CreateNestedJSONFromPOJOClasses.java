/**
 * @author Deepak Rai
 */
package com.qa.restAssured;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateNestedJSONFromPOJOClasses {

	@Test
	private void createNestedJSONFromPOJOClassestest() throws IOException {

		NestedPOJO nestedPOJO = new NestedPOJO();
		nestedPOJO.setCompanyName("Sync");
		nestedPOJO.setCompanyHOCity("Pune");
		nestedPOJO.setCompanyCEO("Deepak");

		List<String> supportedSalaryBanks = new ArrayList<String>();
		supportedSalaryBanks.add("HDFC");
		supportedSalaryBanks.add("ICICI");
		supportedSalaryBanks.add("AXIS");
		nestedPOJO.setSupportedSalaryBanks(supportedSalaryBanks);

		List<Integer> pincodesOfCityOffice = new ArrayList<Integer>();
		pincodesOfCityOffice.add(560037);
		pincodesOfCityOffice.add(360034);
		pincodesOfCityOffice.add(456343);
		nestedPOJO.setPincodesOfCityOffice(pincodesOfCityOffice);

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
		List<Employee> allEmployeesAsRequest = new ArrayList<>();
		allEmployeesAsRequest
				.addAll(Arrays.asList(employeeObjAsRequest1, employeeObjAsRequest2, employeeObjAsRequest3));
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

		List<Contractors> allContractors = new ArrayList<Contractors>();
		allContractors.add(seema);
		allContractors.add(hari);
		nestedPOJO.setContractors(allContractors);

		CompanyPFDeails companyPFDeails = new CompanyPFDeails();
		companyPFDeails.setPfName("XYZ");
		companyPFDeails.setPfCity("Benagluru");
		companyPFDeails.setPfYear(2012);
		companyPFDeails.setNoOfEmployees(10);
		nestedPOJO.setCompanyPFDeails(companyPFDeails);

		// POJO to JSON string
		ObjectMapper objMapper = new ObjectMapper();
		String nestedJSONPayLoad = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(nestedPOJO);
		System.out.println(nestedJSONPayLoad);

		// write POJO to JSON File
		String userDir = System.getProperty("user.dir");
		File outPutJsonFile = new File(userDir + "\\src\\test\\resources\\NestedJSONPayload.json");
		objMapper.writerWithDefaultPrettyPrinter().writeValue(outPutJsonFile, nestedPOJO);

	}
}

class Contractors {
	private String firstName;
	private String lastName;
	private String contractFrom;
	private String contractTo;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContractFrom() {
		return contractFrom;
	}

	public void setContractFrom(String contractFrom) {
		this.contractFrom = contractFrom;
	}

	public String getContractTo() {
		return contractTo;
	}

	public void setContractTo(String contractTo) {
		this.contractTo = contractTo;
	}
}

class CompanyPFDeails {
	private String pfName;
	private String pfCity;
	private int pfYear;
	private int noOfEmployees;

	public String getPfName() {
		return pfName;
	}

	public void setPfName(String pfName) {
		this.pfName = pfName;
	}

	public String getPfCity() {
		return pfCity;
	}

	public void setPfCity(String pfCity) {
		this.pfCity = pfCity;
	}

	public int getPfYear() {
		return pfYear;
	}

	public void setPfYear(int pfYear) {
		this.pfYear = pfYear;
	}

	public int getNoOfEmployees() {
		return noOfEmployees;
	}

	public void setNoOfEmployees(int noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}
}

class NestedPOJO {

	private String companyName;
	private String companyHOCity;
	private String companyCEO;
	private List<String> supportedSalaryBanks;
	private List<Integer> pincodesOfCityOffice;
	List<Employee> employee;
	List<Contractors> contractors;
	CompanyPFDeails companyPFDeails;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyHOCity() {
		return companyHOCity;
	}

	public void setCompanyHOCity(String companyHOCity) {
		this.companyHOCity = companyHOCity;
	}

	public String getCompanyCEO() {
		return companyCEO;
	}

	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}

	public List<String> getSupportedSalaryBanks() {
		return supportedSalaryBanks;
	}

	public void setSupportedSalaryBanks(List<String> supportedSalaryBanks) {
		this.supportedSalaryBanks = supportedSalaryBanks;
	}

	public List<Integer> getPincodesOfCityOffice() {
		return pincodesOfCityOffice;
	}

	public void setPincodesOfCityOffice(List<Integer> pincodesOfCityOffice) {
		this.pincodesOfCityOffice = pincodesOfCityOffice;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public List<Contractors> getContractors() {
		return contractors;
	}

	public void setContractors(List<Contractors> contractors) {
		this.contractors = contractors;
	}

	public CompanyPFDeails getCompanyPFDeails() {
		return companyPFDeails;
	}

	public void setCompanyPFDeails(CompanyPFDeails companyPFDeails) {
		this.companyPFDeails = companyPFDeails;
	}

}