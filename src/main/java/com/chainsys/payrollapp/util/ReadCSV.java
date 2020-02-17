package com.chainsys.payrollapp.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.chainsys.payrollapp.daoimplements.AdminOperations;
import com.chainsys.payrollapp.model.AdminModel;

public class ReadCSV {

	public static void main(String[] args) throws IOException {
		AdminOperations ao = new AdminOperations();
		 Reader in = new FileReader("D:\\resource\\Book.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
        for (CSVRecord record : records) {
        	//String lastName = "";
        	//String firstName = "";
        	System.out.println(record.get("Employee ID")+" "+record.get("Email Address"));
        	AdminModel am = new AdminModel();
        	am.setEmpId(Integer.parseInt(record.get("Employee ID")));
        	am.setEmpName(record.get("Employee ID"));
        	am.setDesignation(record.get("Designation"));
        	am.setFoodFacility(record.get("Food Subscription"));
        	am.setCabFacility(record.get("Cab Subscription"));
        	am.setEmail(record.get("Email Address"));
        	ao.addUsers(am);
        }
	}
	//class="container"
}
