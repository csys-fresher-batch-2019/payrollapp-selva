package com.chainsys.payrollapp.dao;

import java.util.ArrayList;
import com.chainsys.payrollapp.model.HrModel;

public interface HrDAO 
{
	int addGrade(int grade,int id);
	int addBasepay(int basepay,int id);
	int addCredit(int allowance,int id);
	ArrayList<HrModel> viewLeaveApplication();
}
