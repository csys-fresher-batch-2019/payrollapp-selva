package com.chainsys.payrollapp.dao;

import java.util.ArrayList;

import com.chainsys.payrollapp.model.HrModel;

public interface HrDAO 
{
	int addGrade(int id,int grade);
	int addBasepay(int id,int basepay);
	int addCredit(int id,int allowance);
	ArrayList<HrModel> viewLeaveApplication();
}
