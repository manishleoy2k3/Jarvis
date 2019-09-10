package com.api.framework.utility.files;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel {

	private Workbook workBook = null;
	private Sheet workSheet = null;
	private String[][] data;
	private Sheet sheet;
	
	public Sheet getSheet(String filePath, String sheetName) {
	
		File file = new File(filePath);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			workBook = WorkbookFactory.create(fileInputStream);
			workSheet = workBook.getSheet(sheetName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return workSheet;
	}
	
	public String[][] getExcelData(String filePath, String sheetName) {
		try{
			sheet = getSheet(filePath, sheetName);
			int totalRows = sheet.getLastRowNum();
			int totalColumns = sheet.getRow(0).getLastCellNum();
			data = new String[totalRows + 1][totalColumns];
			
			for(int rowCount =0; rowCount <= totalRows; rowCount++) {
				for(int columnCount = 0; columnCount < totalColumns; columnCount++) {
					data[rowCount][columnCount] = getCellValue(sheet.getRow(rowCount).getCell(columnCount));
				}
			}
			return data;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String[][] getExcelData(String filePath, String sheetName, String key) {
		try{
			sheet = getSheet(filePath, sheetName);
			int totalRows = sheet.getLastRowNum();
			int totalColumns = sheet.getRow(0).getLastCellNum();
			data = new String[totalRows][totalColumns];
			
			for(int rowCount =1; rowCount <= totalRows; rowCount++) {
				for(int columnCount = 0; columnCount < totalColumns; columnCount++) {
					if(sheet.getRow(rowCount).getCell(0).getStringCellValue().equalsIgnoreCase(key)) {
						data[rowCount - 1][columnCount] = getCellValue(sheet.getRow(rowCount).getCell(columnCount));
					}
					
				}
			}
			return data;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getCellData(int rowNum, int colNum, String sheetName) throws Exception {
		Row row;
		Cell cell;
		try {
			row = workSheet.getRow(rowNum);
			if(row == null) {
				row = workSheet.createRow(rowNum);
				cell = row.getCell(colNum);
				if(cell == null) {
					cell = row.createCell(colNum);
				}
			} else {
				cell = row.getCell(colNum);
				if(cell == null) {
					cell = row.createCell(colNum);
				}
			}
			return cell.getStringCellValue();
		} catch(Exception e) {
			return "";
		}
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
		sheet = getSheet(filePath, sheetName);
		int totalRows = sheet.getLastRowNum();
		int totalColumns = sheet.getRow(0).getLastCellNum();
		
		int flag=0, keyColumnIndex = 0, rowIndex = 0;
		Object[][] data = new Object[1][2];
		
		HashMap<String, String> map = new HashMap<String, String>();
		String headerName[] = getColumnHeaders(filePath, sheetName);
		
		for(int columnCount = 0; columnCount < totalColumns; columnCount++) {
			System.out.println("" + getCellValue(sheet.getRow(0).getCell(columnCount)));
			if(getCellValue(sheet.getRow(0).getCell(columnCount)).equalsIgnoreCase("key")) {
				flag++;
				keyColumnIndex = columnCount;
				break;
			}
		}
		if(flag == 1) {
			for(int rowCount = 1; rowCount <= totalRows; rowCount++) {
				if(getCellValue(sheet.getRow(rowCount).getCell(keyColumnIndex)).equalsIgnoreCase(key)) {
					rowIndex = rowCount;			
					for(int columnCount = 1; columnCount <= totalColumns; columnCount++) {
						map.put(headerName[columnCount], getCellValue(sheet.getRow(rowIndex).getCell(columnCount)));
						if(columnCount == totalColumns - 1) {
							data[0][0] = getCellValue(sheet.getRow(rowIndex).getCell(0));
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
		sheet = getSheet(filePath, sheetName);
		int totalColumns = sheet.getRow(0).getLastCellNum();
		String headerName[] = new String[totalColumns];
		for(int headerCount = 0; headerCount < totalColumns; headerCount++) {
			headerName[headerCount] = getCellValue(sheet.getRow(0).getCell(headerCount));
		}
		return headerName;
	}
	
	
}

