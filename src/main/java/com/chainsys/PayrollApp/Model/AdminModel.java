package com.chainsys.PayrollApp.Model;

enum FacilityChoices
{
	Y,N
}
public class AdminModel {

	public int empId;
	public String empName;
	public String designation;
	public String foodFacility;
	public String cabFacility;
	public String email;
	//public FacilityChoices choice;
	public int leavesTaken;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getFoodFacility() {
		return foodFacility;
	}
	public void setFoodFacility(String foodFacility) {
		this.foodFacility = foodFacility;
	}
	public String getCabFacility() {
		return cabFacility;
	}
	public void setCabFacility(String cabFacility) {
		this.cabFacility = cabFacility;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int LOPPerDay;
}
