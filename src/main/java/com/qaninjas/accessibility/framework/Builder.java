package com.qaninjas.accessibility.framework;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Builder {

	private final WebDriver driver;
	private final URL script;
	private final List<String> includes = new ArrayList<String>();
	private final List<String> excludes = new ArrayList<String>();
	private String options = "{}";
	private Boolean skipFrames = false;
	private int timeout = 30;
	private AccessibilityUtils accessibilityUtils = new AccessibilityUtils();
	
	/**
	 * Initializes the Builder class to chain configuration before analyzing pages.
	 * @param driver An initialized WebDriver
	 * @param script The javascript URL of aXe
	 */
	public Builder(final WebDriver driver, final URL script) {
		this.driver = driver;
		this.script = script;
	}

	/**
	 * Run aXe against the page.
	 * 
	 * @return An aXe results document
	 */
	public JSONObject analyze() {
		String axeContext;
		String axeOptions;

		if (includes.size() > 1 || excludes.size() > 0) {
			String includesJoined = "['" + StringUtils.join(includes, "'],['") + "']";
			String excludesJoined = excludes.size() == 0 ? "" : "['" + StringUtils.join(excludes, "'],['") + "']";

			axeContext = "document";
			axeOptions = String.format("{ include: [%s], exclude: [%s] }", includesJoined, excludesJoined);
		} else if (includes.size() == 1) {
			axeContext = String.format("'%s'", this.includes.get(0).replace("'", ""));
			axeOptions = options;
		} else {
			axeContext = "document";
			axeOptions = options;
		}

		String snippet = getSnippet(axeContext, axeOptions);
		return execute(snippet);
	}
	
	private String getSnippet(String context, String options) {
		return String.format(
			"var callback = arguments[arguments.length - 1];" + 
			"var context = %s;" + 
			"var options = %s;" + 
			"var result = { error: '', results: null };" + 
			"axe.run(context, options, function (err, res) {" + 
			"  if (err) {" + 
			"    result.error = err.message;" + 
			"  } else {" +
			"    result.results = res;" + 
			"  }" + 
			"  callback(result);" + 
			"});",
			context, options
		);
	}
	
	public JSONObject analyze(final WebElement context) {
		String snippet = getSnippet("arguments[0]", options);
		return execute(snippet, context);
	}

	private JSONObject execute(final String command, final Object... args) {
		accessibilityUtils.inject(this.driver, this.script, this.skipFrames);

		this.driver.manage().timeouts().setScriptTimeout(this.timeout, TimeUnit.SECONDS);

		Object response = ((JavascriptExecutor) this.driver).executeAsyncScript(command, args);
		
		JSONObject result = new JSONObject((Map) response);
		String error = result.getString("error");
		
		if (error != null && !error.isEmpty()) {
			throw new AxeRuntimeException(error);
		}
		
		JSONObject results = result.getJSONObject("results");
		return results;
	}


}
