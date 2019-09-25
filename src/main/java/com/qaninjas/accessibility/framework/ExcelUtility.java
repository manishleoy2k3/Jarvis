package com.qaninjas.accessibility.framework;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qaninjas.framework.constants.ReportConstants;

public class ExcelUtility {

	public void createWorkBook() {
		String fileLocation = ReportConstants.VOILATIONS_LOCATION;
		Workbook workbook = new XSSFWorkbook();
		FileOutputStream fileOut;
		
		try {
			fileOut = new FileOutputStream(fileLocation + "VoilationReport.xlsx");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
