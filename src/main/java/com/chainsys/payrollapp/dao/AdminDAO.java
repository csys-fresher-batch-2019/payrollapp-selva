package com.chainsys.payrollapp.dao;

import com.chainsys.payrollapp.model.*;

public interface AdminDAO {
	
	int addUsers(AdminModel a);
	int removeUsers(int number);
	int calculateLOP() throws ClassNotFoundException;
	int resetPassword(int id);

}
