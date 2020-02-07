package com.chainsys.PayrollApp.daoimplements;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.chainsys.PayrollApp.util.Logger;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ReadExcel 
{
	static Logger logger = Logger.getInstance();
	static String fileName = "credits.xlsx";
	static FileInputStream excelFile =null;
	static Workbook workbook;
    public static void AdminData() 
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
            	List data = new List();
            	//Object[] p = {};
                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) 
                {
                	Cell currentCell = cellIterator.next();
                    if (currentCell.getCellType() == Cell.CELL_TYPE_STRING)
                    {
                    	System.out.print(currentCell.getStringCellValue() + "--");
                        data.add(currentCell.getStringCellValue());
                        
                    } 
                    else if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) 
                    {
                    	int a = (int)currentCell.getNumericCellValue();
                        System.out.print(a + "--");
                    }
                }
                System.out.println(data);
            }
        }
    	catch (FileNotFoundException e) 
    	{
            logger.error(e);
        }
    	catch (IOException e) {
            logger.error(e);
        }
    }
}

