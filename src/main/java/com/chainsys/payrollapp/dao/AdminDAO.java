package com.chainsys.payrollapp.dao;

import com.chainsys.payrollapp.model.*;
public interface AdminDAO {
	
	String addUsers(AdminModel a);
	String removeUsers(int number);
	String calculateLOP();
	void resetPassword(int id);

}
