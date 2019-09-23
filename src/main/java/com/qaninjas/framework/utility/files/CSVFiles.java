package com.qaninjas.framework.utility.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.Field;
import org.apache.log4j.Logger;


public class CSVFiles {

	private static  Logger logger = Logger.getLogger(CSVFiles.class);

	public boolean createCSVFile(String filePath, String header) {
		logger.info("Creating new CSV file " + filePath);
		
		try {
			File file = new File(filePath);
			if(file.getAbsoluteFile().exists()) {
				FileUtils.forceDelete(file);
				file.getAbsoluteFile().createNewFile();
			}
			file.getAbsoluteFile().createNewFile();
			updateCSVFile(filePath, header);
			logger.info("CSV file created successfully " + filePath);
			return true;
		} catch (Exception e) {
			logger.error("Exception...", e);
			return false;
		}
	}

	private void updateCSVFile(String fileName, String line) {
		logger.info("Updating new CSV file " + fileName);
		
		try {
			FileWriter fileWriter = new FileWriter(fileName, true);
			fileWriter.append(line);
			fileWriter.append("\n");
			fileWriter.close();
			
		} catch (IOException e) {
			logger.error("Exception...", e);
		}
		logger.info("CSV file updated successfully " + fileName);
	}
}
