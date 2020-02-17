package com.chainsys.payrollapp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectIdUtil {
	public ArrayList<Integer> selectUserId(String designation) throws SQLException
	{
		String sql = "select emp_id from employee where designation = ?";
		ArrayList<Integer> idList = new  ArrayList<>();
		try(Connection con = Connections.connect();
		PreparedStatement pst = con.prepareStatement(sql);)
		{
			pst.setString(1,designation);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				idList.add(rs.getInt("emp_id"));
			}
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
		return idList;
	}
}
