package session23;

import java.util.List;

import session22.EmployeePojoClass;

public class NestedJSONPojoClass {

	
	private String companyName;
	private String Street;
	private String City;
	private String state;
	private String pincode;
	private List <String > bankAccount;
	
	private List<EmployeePojoClass> employeeList;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public List<String> getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(List<String> bankAccount) {
		this.bankAccount = bankAccount;
	}

	public List<EmployeePojoClass> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeePojoClass> employeeList) {
		this.employeeList = employeeList;
	}
	
	
	
}
