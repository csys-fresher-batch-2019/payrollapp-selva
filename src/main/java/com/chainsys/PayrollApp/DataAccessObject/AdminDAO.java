package com.chainsys.PayrollApp.DataAccessObject;

import com.chainsys.PayrollApp.Model.*;
public interface AdminDAO {
	
	String addUsers(AdminModel a) throws Exception;
	String removeUsers(int number) throws Exception;
	String calculateLOP()throws Exception;
	void resetPassword(int id) throws Exception;

}
