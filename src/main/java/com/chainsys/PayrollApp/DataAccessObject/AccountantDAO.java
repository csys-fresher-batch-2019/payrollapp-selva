package com.chainsys.PayrollApp.DataAccessObject;

public interface AccountantDAO 
{
	String calculatePF() throws Exception ;
	String calculateIncrement() throws Exception ;
	String calculatesalary() throws Exception ;
	String markAttendance() throws Exception ;
}
