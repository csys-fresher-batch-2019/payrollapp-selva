package com.chainsys.payrollapp.dao;

import java.util.ArrayList;

import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.chainsys.payrollapp.model.HrModel;

public interface HrDAO 
{
	@SqlUpdate("update employee set performance_grade = ? where emp_id = ?")
	int addGrade(int grade,int id);
	@SqlUpdate("update employee set salary = ? where emp_id = ?")
	int addBasepay(int basepay,int id);
	@SqlUpdate("update credits set allowance = ? where emp_id = ?")
	int addCredit(int allowance,int id);
	@SqlQuery("select * from leave_info where status = 'PENDING' ")
	ArrayList<HrModel> viewLeaveApplication();
}
