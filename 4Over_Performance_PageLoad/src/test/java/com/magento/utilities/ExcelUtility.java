package com.magento.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public static FileInputStream fis;
	
	public String getCellValueAsString(Cell cell) {
        String cellValue = null;
        switch (cell.getCellType()) {
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue= cell.getCellFormula();
            case BLANK:
                cellValue="BLANK";
            default:
                cellValue ="DEFAULT";
        }
        return cellValue;
    }
	
	public int getRowCount(String filePath, String sheetName) throws EncryptedDocumentException, IOException {
		fis = new FileInputStream(System.getProperty("user.dir")+filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet s = wb.getSheet(sheetName);
		return s.getLastRowNum();
	}
	
	public Map<String, String> readExcelCellValueGivenTheKey(String filePath, String sheetName) throws EncryptedDocumentException, IOException {
		Map<String, String> sheetDataAsMap = new HashMap<String, String>();
		fis = new FileInputStream(System.getProperty("user.dir")+filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet s = wb.getSheet(sheetName);
		
		int rows = getRowCount(filePath, sheetName);
		for(int i=1; i<rows; i++) {
			sheetDataAsMap.put(getCellValueAsString(s.getRow(i).getCell(0)), getCellValueAsString(s.getRow(i).getCell(1)));
		}
		
		return sheetDataAsMap;
	}
	
//	find whether sheet exists
	public boolean isSheetExists(String filePath, String sheetName) throws EncryptedDocumentException, IOException {

		fis = new FileInputStream(System.getProperty("user.dir")+filePath);
		Workbook wb = WorkbookFactory.create(fis);
		int index = wb.getSheetIndex(sheetName);
		
		if(index==-1) {
			index = wb.getSheetIndex(sheetName.toUpperCase());
			if(index==-1)
				return false;
			else
				return true;
		}
		else
			return true;
		
	}
	
//	returns number of columns in a sheet
	public int getColumnCount(String filePath, String sheetName) throws EncryptedDocumentException, IOException {
		fis = new FileInputStream(System.getProperty("user.dir")+filePath);
		Workbook wb = WorkbookFactory.create(fis);
		
//		check if sheet exists
		if(!isSheetExists(filePath, sheetName)) 
			return -1;
		
		Sheet s = wb.getSheet(sheetName);
		Row r = s.getRow(0);
		
		if(r == null)
			return 1;
		
		return r.getLastCellNum();
	}
	
	public String getCellData(String filePath, String sheetName,int rowNum, int colNum){
		try{
			if(rowNum <=0)
				return "";
			fis = new FileInputStream(System.getProperty("user.dir")+filePath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheet(sheetName);
			Row row = sheet.getRow(rowNum-1);
			if(row==null)
				return "";

			return getCellValueAsString(row.getCell(colNum));
		}
		catch(Exception e){

			e.printStackTrace();
			return "Row "+rowNum+" or Column "+colNum +" does not exist in the excel";
		}
	}
}
