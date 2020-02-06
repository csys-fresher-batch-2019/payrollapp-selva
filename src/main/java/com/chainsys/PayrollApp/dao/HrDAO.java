package com.chainsys.PayrollApp.dao;

import java.util.ArrayList;

import com.chainsys.PayrollApp.model.HrModel;

public interface HrDAO 
{
	String addGrade(int id,int grade)throws Exception;
	String addBasepay(int id,int basepay) throws Exception;
	String addCredit(int id,int allowance) throws Exception;
	ArrayList<HrModel> viewLeaveApplication() throws Exception;
}
