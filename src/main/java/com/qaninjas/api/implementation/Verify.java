package com.qaninjas.api.implementation;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.qaninjas.framework.api.interfaces.IGeneric;
import com.qaninjas.framework.api.interfaces.IResponseHeader;
import com.qaninjas.framework.api.interfaces.IResponseJsonPath;
import com.qaninjas.framework.api.interfaces.IResponseTime;
import com.qaninjas.framework.api.interfaces.IStatusCode;
import com.qaninjas.framework.api.interfaces.IStatusLine;
import com.qaninjas.framework.api.interfaces.IVerify;
import com.qaninjas.framework.api.interfaces.IXPath;

import io.restassured.response.Response;

public class Verify implements IVerify, IGeneric{

	private static  Logger logger = Logger.getLogger(Verify.class);

	public IResponseHeader header(String headerName) {
		try {
			responseHeader.setHeaderVal(responseBuilder.getResponse().getHeader(headerName));
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Get Header failed");
		}
		return ResponseHeader.getInstance();
	}

	public IStatusCode statusCode() {
		try {
			statusCode.setStatusCode(responseBuilder.getResponse().getStatusCode());
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Get Status code failed");
		}
		return statusCode;
	}

	public IStatusLine statusLine() {
		try {
			statusLine.setStatusLine(responseBuilder.getResponse().getStatusLine());
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Get Status line failed");
		}
		return statusLine;
	}

	public IResponseTime responseTime(TimeUnit tUnit) {
		try {
			if((tUnit == TimeUnit.MICROSECONDS) || (tUnit == TimeUnit.MILLISECONDS) || (tUnit == TimeUnit.SECONDS)
					|| (tUnit == TimeUnit.MINUTES)){
				responseTime.setResponseTime((int) responseBuilder.getResponse().timeIn(TimeUnit.SECONDS));
				responseTime.setResponseTimeUnit(tUnit);
			}else {
				logger.error("TimeUnit should be either Minutes, Seconds, MilliSeconds or MicroSeconds");
				util.failTestCase("Incorrect TimeUnit passed",
						"TimeUnit should be either Minutes, Seconds, MilliSeconds or MicroSeconds");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Get Response Time failed");
		}
		return responseTime;
	}

	public IResponseJsonPath JsonPath(String jpath) {
		try {
			responseJsonPath.setJsonPath(jpath);
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Set JSON Path failed");
		}
		return ResponseJsonPath.getInstance();
	}

	public IXPath xPath(String xPath) {
		try {
			XPath.getInstance().setXpathValue(xPath);
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Set XPath failed");
		}
		return XPath.getInstance();
	}

	public void jsonResponseSchema(String filePath) {
		Response response = responseBuilder.getResponse();
		try {
			response.then().assertThat().body(matchesJsonSchema(new File(filePath)));
		} catch(AssertionError a) {
			logger.error("JSON response schema validation failed.");
			util.failTestCase("Assertion Fialed", "JSON response schema validation failed");
		}
	}
}