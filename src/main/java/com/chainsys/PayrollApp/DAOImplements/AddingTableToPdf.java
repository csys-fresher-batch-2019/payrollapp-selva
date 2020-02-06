package com.chainsys.PayrollApp.DAOImplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 
 
import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Table; 
public class AddingTableToPdf { 
	public static void main(String args[]) throws Exception {  
		Connection con = UserLogin.connect();
      	String sql = "select emp_id from final_salary";
      	PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{
			String file = "Employee"+rs.getInt("emp_id")+".pdf";
			PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));       
			Document doc = new Document(pdfDoc);        
			Table table = new Table(2);
			table.addCell("Raju"); 
		    table.addCell("Raju"); 
		    table.addCell("Raju"); 
		    table.addCell("Raju"); 
		    table.addCell("Raju"); 
		    table.addCell("Raju"); 
		    doc.add(table);
		    doc.close();
		}        
      System.out.println("Table created successfully.."); 
   }     
}