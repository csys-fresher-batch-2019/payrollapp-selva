package com.chainsys.payrollapp.dao;

import java.util.ArrayList;

import com.chainsys.payrollapp.model.HrModel;

public interface HrDAO 
{
	String addGrade(int id,int grade);
	String addBasepay(int id,int basepay);
	String addCredit(int id,int allowance);
	ArrayList<HrModel> viewLeaveApplication();
}
