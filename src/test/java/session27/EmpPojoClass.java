package session27;

import java.util.Map;

public class EmpPojoClass {
	/* "firstname": "Suresh",
	    "lastname": "Mehra",
	    "gender": "Male",
	    "age": 35,
	    "salary": 10000.0,
	    "hobbies": [
	        "Reading",
	        "Music"
	    ],
	    "familyMembers": {
	        "1": "Mother",
	        "2": "Father"
	    },
	    "married": true*/
	
	private String firstname;
	private String lastname;
	private String gender;
	private int age;
	private double salary;
	
	private String[] hobbies;
	
	private Map <String, String>familyMembers;
	
	private boolean married; 

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String[] getHobbies() {
		return hobbies;
	}

	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}

	public Map<String, String> getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(Map<String, String> familyMembers) {
		this.familyMembers = familyMembers;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}
	
	
	
	
}
