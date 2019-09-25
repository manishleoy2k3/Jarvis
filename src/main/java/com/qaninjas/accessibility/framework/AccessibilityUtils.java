package com.qaninjas.accessibility.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.constants.ReportConstants;
import com.qaninjas.framework.utility.excel.UpdateExcel;

public class AccessibilityUtils {

	private String lineSeparator = System.getProperty("line.separator");
	private UpdateExcel updateExcel = new UpdateExcel();
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private GetHeadLessBrowser getWCAG = new GetHeadLessBrowser();

	private String getContents(URL script) {
		final StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;

		URLConnection connection;
		try {
			connection = script.openConnection();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append(lineSeparator);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ignored) {
					// TODO Auto-generated catch block
					ignored.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Recursively injects a script to the top level document with the option to
	 * skip iframes.
	 * 
	 * @param driver     WebDriver instance to inject into
	 * @param scriptUrl  URL to the script to inject
	 * @param skipFrames True if the script should not be injected into iframes
	 */
	public void inject(final WebDriver driver, final URL scriptUrl, Boolean skipFrames) {
		final String script = getContents(scriptUrl);

		if (!skipFrames) {
			final ArrayList<WebElement> parents = new ArrayList<WebElement>();
			injectIntoFrames(driver, script, parents);
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.switchTo().defaultContent();
		js.executeScript(script);
	}

	/**
	 * Recursively injects a script into all iframes and the top level document.
	 * 
	 * @param driver    WebDriver instance to inject into
	 * @param scriptUrl URL to the script to inject
	 */
	public void inject(final WebDriver driver, final URL scriptUrl) {
		inject(driver, scriptUrl, false);
	}

	/**
	 * Recursively find frames and inject a script into them.
	 * 
	 * @param driver  An initialized WebDriver
	 * @param script  Script to inject
	 * @param parents A list of all toplevel frames
	 */
	private void injectIntoFrames(final WebDriver driver, final String script, final ArrayList<WebElement> parents) {
		final JavascriptExecutor js = (JavascriptExecutor) driver;
		final List<WebElement> frames = driver.findElements(By.tagName("iframe"));

		for (WebElement frame : frames) {
			driver.switchTo().defaultContent();

			if (parents != null) {
				for (WebElement parent : parents) {
					driver.switchTo().frame(parent);
				}
			}

			driver.switchTo().frame(frame);
			js.executeScript(script);

			ArrayList<WebElement> localParents = (ArrayList<WebElement>) parents.clone();
			localParents.add(frame);

			injectIntoFrames(driver, script, localParents);
		}
	}

	public String excelReport(JSONArray violations) {
		final StringBuilder sb = new StringBuilder();
		sb.append("Found ").append(violations.length()).append(" accessibility violations:");

		for (int violationCount = 0; violationCount < violations.length(); violationCount++) {
			JSONObject violation = violations.getJSONObject(violationCount);
			sb.append(lineSeparator).append(violationCount + 1).append(") ").append("<JSTORM REPORT>")
			.append(lineSeparator).append("Issue Description").append(violation.getString("help")).append(lineSeparator);

			if (violation.has("helpUrl")) {
				String helpUrl = violation.getString("helpUrl");
				String impact = violation.getString("impact");
				sb.append(": ").append(helpUrl);
				sb.append(lineSeparator).append("Impact: ").append(impact);
			}

			JSONArray nodes = violation.getJSONArray("nodes");

			for (int nodesCount = 0; nodesCount < nodes.length(); nodesCount++) {
				JSONObject node = nodes.getJSONObject(nodesCount);
				sb.append(lineSeparator).append("  ").append(getOrdinal(nodesCount + 1)).append(") Element affected: ")
						.append(node.getJSONArray("target")).append(lineSeparator);

				JSONArray all = node.getJSONArray("all");
				JSONArray none = node.getJSONArray("none");

				for (int k = 0; k < none.length(); k++) {
					all.put(none.getJSONObject(k));
				}

				appendFixes(sb, all, "Resolutions: ");
				appendFixes(sb, node.getJSONArray("any"), "Resolutions: ");
			}
		}
		insertDataToExcel(sb.toString());
		return sb.toString();
	}

	private void insertDataToExcel(String voilations) {
		getWCAG.openHeadLessBrowser();
		ArrayList<String> guidelines = new ArrayList<>();
		String voilation, description, link, impact, resolutions, sheetName, guideline, axApi;
		
		String filePath = ReportConstants.VOILATIONS_LOCATION + "VoilationReport.xlsx";
		sheetName = driver.getTitle();
				
		if(sheetName.length() > 31)
			sheetName = StringUtils.left(sheetName, 30);
		updateExcel.addSheet(filePath, sheetName, FrameworkConstants.ACCESSCONFIG.get("EXCEL_REPORT_COLUMNS"));
		
		List<String> voilationList = new ArrayList<String>(
				Arrays.asList(voilations.toString().trim().split("<--JSTORM REPORT-->")));
		int rowCount = 1;
		for(int parentVoilation = 1; parentVoilation < voilationList.size(); parentVoilation++) {
			String srNo = String.valueOf(parentVoilation);
			voilation = voilationList.get(parentVoilation);
			description = voilation.split("Learn more:")[0].split("Issue Description:")[1];
			link = voilation.split("Impact:")[0].split("Learn more:")[1];
			axApi = link;
			impact = voilation.split("Element affected:")[0].split("Impact")[1];
			String[] subIssue = voilation.split("Element affected:");
			
			for(int childVoilation = 1; childVoilation < voilationList.size(); childVoilation++) {
				resolutions = subIssue[childVoilation].split("Resolutions:")[1].trim();
				resolutions = resolutions.substring(0, resolutions.length() - 3);
				updateExcel.setCellData(filePath, sheetName, srNo + "." + childVoilation, rowCount, 0);
				
				updateExcel.setCellData(filePath, sheetName, subIssue[childVoilation].split("Resolutions:")[0].trim(), rowCount, 1);
				
				updateExcel.setCellData(filePath, sheetName, description, rowCount, 2);
				updateExcel.setCellData(filePath, sheetName, impact.trim(), rowCount, 3);
				updateExcel.setCellData(filePath, sheetName, resolutions, rowCount, 5);
				updateExcel.setCellData(filePath, sheetName, axApi, rowCount, 7);
				guidelines.add(axApi);
				rowCount++;
			}
		}
		
		for(int findRule = 0; findRule < voilationList.size(); findRule++) {
			guideline = getWCAG.getWCAGGuideline(guidelines.get(findRule));
			link = FrameworkConstants.ACCESSCONFIG.get("").replace("{rule}", guideline);
			if(guideline.equalsIgnoreCase("Best Practice"))
				link = "";

			if(guideline.contains(",")) {
				String links = "";
				String[] rules = guideline.split(",");
				for(String rule : rules){
					links = links + FrameworkConstants.ACCESSCONFIG.get("").replace("{rule}", rule);
				}
				link = links;
			}
			updateExcel.setCellData(filePath, sheetName, guideline, rowCount, 4);
			updateExcel.setCellData(filePath, sheetName, link, rowCount, 6);
			rowCount++;
		}
		getWCAG.closeHeadLessBrowser();
	}

	/**
	 * @param violations JSONArray of violations
	 * @return readable report of accessibility violations found
	 */
	public String htmlReport(final JSONArray violations) {
		final StringBuilder sb = new StringBuilder();
		sb.append("Found ").append(violations.length()).append(" accessibility violations:");

		for (int i = 0; i < violations.length(); i++) {
			JSONObject violation = violations.getJSONObject(i);
			sb.append(lineSeparator).append(i + 1).append(") ").append("<JSTORM REPORT>").append(violation.getString("help"));

			if (violation.has("helpUrl")) {
				String helpUrl = violation.getString("helpUrl");
				sb.append(": ").append(helpUrl);
			}

			JSONArray nodes = violation.getJSONArray("nodes");

			for (int j = 0; j < nodes.length(); j++) {
				JSONObject node = nodes.getJSONObject(j);
				sb.append(lineSeparator).append("  ").append(getOrdinal(j + 1)).append(") ")
						.append(node.getJSONArray("target")).append(lineSeparator);

				JSONArray all = node.getJSONArray("all");
				JSONArray none = node.getJSONArray("none");

				for (int k = 0; k < none.length(); k++) {
					all.put(none.getJSONObject(k));
				}

				appendFixes(sb, all, "Fix all of the following:");
				appendFixes(sb, node.getJSONArray("any"), "Fix any of the following:");
			}
		}
		return sb.toString();
	}

	private void appendFixes(final StringBuilder sb, final JSONArray arr, final String heading) {
        if (arr != null && arr.length() > 0) {
            sb
                    .append("    ")
                    .append(heading)
                    .append(lineSeparator);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject fix = arr.getJSONObject(i);

                sb
                        .append("      ")
                        .append(fix.get("message"))
                        .append(lineSeparator);
            }

            sb.append(lineSeparator);
        }
    }

	private static String getOrdinal(int number) {
        String ordinal = "";

        int mod;

        while (number > 0) {
            mod = (number - 1) % 26;
            ordinal = (char) (mod + 97) + ordinal;
            number = (number - mod) / 26;
        }

        return ordinal;
    }
}
