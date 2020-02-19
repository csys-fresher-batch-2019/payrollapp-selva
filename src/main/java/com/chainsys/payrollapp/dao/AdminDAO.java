package com.chainsys.payrollapp.dao;

import java.util.ArrayList;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.chainsys.payrollapp.model.*;

public interface AdminDAO {
	
	int addUsers(AdminModel a);
	int removeUsers(int number);
	int calculateLOP() throws ClassNotFoundException;
	@SqlUpdate("update user_login set passwd = 'pass123',active = 0 where emp_id = ?")
	int resetPassword(int id);
	ArrayList<AdminModel> viewDetails()throws Exception;

}
