package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	
	private static final String REG_EXCEL_PATH="./src/test/resources/testdata/opencarttestdata.xlsx";
	private static  Workbook book;
	private static Sheet sheet;
	
	
    public static Object[][] getTestData(String sheetname) {
    	System.out.println("reading data from the sheet " + sheetname);
    	Object data[][]=null;
    	
    	try {
			FileInputStream ip=new FileInputStream(REG_EXCEL_PATH);
			
			book=WorkbookFactory.create(ip);
			
			sheet=book.getSheet(sheetname);
			
			//intializing object array using new key word with row and coloumn
			
			//new Object[row][column]--  > row- the last rowno;  column -first get the zeroth row and get the last cell num of it
			data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0;i<sheet.getLastRowNum();i++) {
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
					
					//to fill the data 2D array
					//actual data from excel starting from second row
					
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    		return data;
    	
    }

}
