package com.chainsys.payrollapp.dao;

import java.util.ArrayList;
import com.chainsys.payrollapp.model.*;

public interface AdminDAO {
	
	int addUsers(AdminModel a);
	int removeUsers(int number);
	int calculateLOP() throws ClassNotFoundException;
	int resetPassword(int id);
	ArrayList<AdminModel> viewDetails()throws Exception;

}
