package com.chainsys.PayrollApp.dao;

import com.chainsys.PayrollApp.model.*;
public interface AdminDAO {
	
	String addUsers(AdminModel a);
	String removeUsers(int number);
	String calculateLOP();
	void resetPassword(int id);

}
