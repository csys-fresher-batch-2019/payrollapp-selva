package com.chainsys.payrollapp;

import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.payrollapp.util.SelectIdUtil;

public class TestSelect 
{
	public static void main(String[] args) 
	{
		String desg = "Admin";
		 SelectIdUtil selectid = new SelectIdUtil();
		 ArrayList<Integer> idlist = new ArrayList<>();
		    try {
				idlist = selectid.selectUserId(desg);
				for(Integer obj:idlist)
				{
					System.out.println(obj);
				}
		    }
		    catch(SQLException e)
		    {
		    	throw new RuntimeException(e);
		    }
	}
}
