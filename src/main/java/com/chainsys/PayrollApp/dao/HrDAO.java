package com.chainsys.PayrollApp.dao;

import java.util.ArrayList;

import com.chainsys.PayrollApp.model.HrModel;

public interface HrDAO 
{
	String addGrade(int id,int grade);
	String addBasepay(int id,int basepay);
	String addCredit(int id,int allowance);
	ArrayList<HrModel> viewLeaveApplication();
}
