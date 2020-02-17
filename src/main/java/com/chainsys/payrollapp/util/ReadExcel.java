package com.chainsys.payrollapp.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class ReadExcel 
{
	static Logger logger = Logger.getInstance();
	static String fileName = "credits.xlsx";
	static FileInputStream excelFile =null;
	static Workbook workbook;
    public static Object[] AdminData() 
    {
    	try 
        {
    		excelFile = new FileInputStream(new File(fileName));
            workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) 
            {
            	Row currentRow = iterator.next();
            	List<Object> list = new LinkedList<Object>();
            	//Object[] p = {};
                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) 
                {
                	Cell currentCell = cellIterator.next();
                    if (currentCell.getCellType() == Cell.CELL_TYPE_STRING)
                    {
                    	//System.out.print(currentCell.getStringCellValue() + "||");
                        list.add((Object)currentCell.getStringCellValue());
                        
                    } 
                    else if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) 
                    {
                    	list.add((Object)((int)currentCell.getNumericCellValue()));
                        //System.out.print(a + "||");
                    }
                }
                Object[] obj = new Object[list.size()];
                for (int i =0; i < list.size(); i++) 
                    obj[i] = list.get(i); 
                return obj;
                //String sql = "insert into employee(emp_id,emp_name,designation," + 
                	//	" leaves_taken,salary,total_leaves,food,cab_facility,email,experience)"+ 
                		//" values(?,?,?,0,0,12,?,?,?,0)";
               // JdbcUtil.executeUpdate(sql,obj);
            }
            
        }
    	catch (FileNotFoundException e) 
    	{
    		throw new RuntimeException(e);
        }
    	catch (IOException e) {
    		throw new RuntimeException(e);
        }
		return null;
    }
}

