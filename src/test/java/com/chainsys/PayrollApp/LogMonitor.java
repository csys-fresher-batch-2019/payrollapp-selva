package com.chainsys.PayrollApp;

import java.sql.CallableStatement;
import java.sql.Connection;

import com.chainsys.PayrollApp.DAOImplements.UserLogin;

public class LogMonitor {

	public String swipe(int empId) throws Exception 
	{
		Connection con = UserLogin.connect();
		CallableStatement stmt = con.prepareCall("{call entry_gate(?)}");
		stmt.setInt(1,empId);
		boolean update = stmt.execute();
		if(!update)
			return empId+" Swipe Success!!!!";
		else
			return "Failed!!!";
	}
}
