package com.qaninjas.framework.utility.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import junit.framework.Assert;

public class UpdateExcel {

	private Workbook workBook = null;
	private Sheet workSheet = null;
	private Row row;
	private Cell cell;
	private static Logger logger = Logger.getLogger(UpdateExcel.class);
	
	public Sheet getSheet(String filePath, String sheetName) {
		File file = new File(filePath);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			workBook = WorkbookFactory.create(fileInputStream);
			workSheet = workBook.getSheet(sheetName);
		} catch(Exception e) {
			logger.error("Not able to retrieve sheet.");
			Assert.fail("Not able to retrieve sheetName " + e.getMessage());
		}
		return workSheet;
	}
	
	public void setCellData(String filePath, String sheetName, String result, int rowNum, int colNum) {
		workSheet = getSheet(filePath, sheetName);
		FileOutputStream fileOutputStream = null;
		row = workSheet.getRow(rowNum);
		if(row == null) {
			row = workSheet.createRow(rowNum);
		}
		cell = row.getCell(colNum);
		if(cell == null) {
			cell = row.createCell(colNum);
			cell.setCellValue(result);
		} else {
			cell.setCellValue(result);
		}
		try {
			fileOutputStream = new FileOutputStream(filePath);
			workBook.write(fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
			
		} catch(Exception e) {
			logger.error("Not able to set cell data sheet.");
			Assert.fail("Not able to set cell data sheet. " + e.getMessage());		
		}finally {
			fileOutputStream = null;
		}
	}
	
	public void addSheet(String fileName, String sheetName, String columns) {
		try {
			workBook = WorkbookFactory.create(new FileInputStream(fileName));
			for(int sheetIndex = 0; sheetIndex < workBook.getNumberOfSheets(); sheetIndex++) {
				if(workBook.getSheetName(sheetIndex).equals(sheetName)) {
					workBook.close();
					logger.info(sheetName + " is already created in workbook");
				}
				workSheet = workBook.createSheet(sheetName);
				addReportHeader(columns);
				workBook.write(new FileOutputStream(fileName));
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void addReportHeader(String cellNames) {
		
	}

	private String getCellValue(Cell cell) {

		String strCellValue = "";
		try {
			if(cell == null) {
				strCellValue = "";
			} else {
				switch (cell.getCellTypeEnum()) {
				case BOOLEAN:
					strCellValue = new String(new Boolean(cell.getBooleanCellValue()).toString());
					break;
				case STRING:
					strCellValue = cell.getRichStringCellValue().getString().toString();
					break;
				case NUMERIC:
					if(DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
						strCellValue = dateFormat.format(cell.getDateCellValue());
					} else {
						Double value = cell.getNumericCellValue();
						Long longValue = value.longValue();
						strCellValue = new String(longValue.toString());
					}					
					break;
				case BLANK:
					strCellValue = "";
					break;
				default:
					strCellValue = "";
				}
			}
			return strCellValue.trim();
		}catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public int getRowCount(Sheet testSetWorkSheet) {
		int iNumber = 0;
		try {
			iNumber = testSetWorkSheet.getLastRowNum() +1;
		}catch(Exception e) {
			
		}
		return iNumber;
	}

	/*public Object[][] getDataWithMap(String filepath, String sheetName){
		return data;
	}*/
	
	public Object[][] getDataWithMapBasedOnKey(String filePath, String sheetName, String key){
		workSheet = getSheet(filePath, sheetName);
		int totalRows = workSheet.getLastRowNum();
		int totalColumns = workSheet.getRow(0).getLastCellNum();
		
		int flag=0, keyColumnIndex = 0, rowIndex = 0;
		Object[][] data = new Object[1][2];
		
		HashMap<String, String> map = new HashMap<String, String>();
		String headerName[] = getColumnHeaders(filePath, sheetName);
		
		for(int columnCount = 0; columnCount < totalColumns; columnCount++) {
			System.out.println("" + getCellValue(workSheet.getRow(0).getCell(columnCount)));
			if(getCellValue(workSheet.getRow(0).getCell(columnCount)).equalsIgnoreCase("key")) {
				flag++;
				keyColumnIndex = columnCount;
				break;
			}
		}
		if(flag == 1) {
			for(int rowCount = 1; rowCount <= totalRows; rowCount++) {
				if(getCellValue(workSheet.getRow(rowCount).getCell(keyColumnIndex)).equalsIgnoreCase(key)) {
					rowIndex = rowCount;			
					for(int columnCount = 1; columnCount <= totalColumns; columnCount++) {
						map.put(headerName[columnCount], getCellValue(workSheet.getRow(rowIndex).getCell(columnCount)));
						if(columnCount == totalColumns - 1) {
							data[0][0] = getCellValue(workSheet.getRow(rowIndex).getCell(0));
							data[0][1] = map;
						}
					}
				}
			}
		} else {
			data = null;
		}
		return data;
	}
	
	public String[] getColumnHeaders(String filePath, String sheetName) {
		workSheet = getSheet(filePath, sheetName);
		int totalColumns = workSheet.getRow(0).getLastCellNum();
		String headerName[] = new String[totalColumns];
		for(int headerCount = 0; headerCount < totalColumns; headerCount++) {
			headerName[headerCount] = getCellValue(workSheet.getRow(0).getCell(headerCount));
		}
		return headerName;
	}
	
	
}

