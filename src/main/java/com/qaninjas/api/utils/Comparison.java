package com.qaninjas.api.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;

import org.apache.log4j.Logger;
import com.aventstack.extentreports.Status;
import com.qaninjas.framework.api.interfaces.IGeneric;

public class Comparison implements IGeneric{

	private static Comparison instance = null;
	private static  Logger logger = Logger.getLogger(Comparison.class);
	
	private enum primitiveTypes{
		INTEGER, DOUBLE, STRING, LONG, FLOAT,BOOLEAN, CHARACTER
	}
	
	protected Comparison() {
		
	}
	
	public static Comparison getInstance() {
		if(null == instance) {
			instance = new Comparison();
		}
		return instance;
	}
	
	public void isEqualTo(boolean comparisionType, Object expectedValue, Object actualValue) {
		String primitiveTypeSubString[] = expectedValue.getClass().getName().split("java.lang.");
		
		if(comparisionType == true) {
			switch(primitiveTypes.valueOf(primitiveTypeSubString[1].toUpperCase())) {
			case INTEGER:
				try {
					assertThat("Field Validation", (Integer) expectedValue, (equalTo((Integer) actualValue)));
					logger.info("Field validation successful. Expected: equal to: "+ (Integer) expectedValue + "Actual : "+ (Integer) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : equal to :"+ (Integer) expectedValue + "Actual : " + (Integer) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case DOUBLE:
				try {
					assertThat("Field Validation", (Double) expectedValue, (equalTo((Double) actualValue)));
					logger.info("Field validation successful. Expected: equal to: "+ (Double) expectedValue + "Actual : "+ (Double) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : equal to :"+ (Double) expectedValue + "Actual : " + (Double) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case STRING:
				try {
					assertThat("Field Validation", (String) expectedValue, (equalTo((String) actualValue)));
					logger.info("Field validation successful. Expected: equal to: "+ (String) expectedValue + "Actual : "+ (String) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : equal to :"+ (String) expectedValue + "Actual : " + (String) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case LONG:
				try {
					assertThat("Field Validation", (Long) expectedValue, (equalTo((Long) actualValue)));
					logger.info("Field validation successful. Expected: equal to: "+ (Long) expectedValue + "Actual : "+ (Long) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : equal to :"+ (Long) expectedValue + "Actual : " + (Long) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case FLOAT:
				try {
					assertThat("Field Validation", (Float) expectedValue, (equalTo((Float) actualValue)));
					logger.info("Field validation successful. Expected: equal to: "+ (Float) expectedValue + "Actual : "+ (Float) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : equal to :"+ (Float) expectedValue + "Actual : " + (Float) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case BOOLEAN:
				try {
					assertThat("Field Validation", (Boolean) expectedValue, (equalTo((Boolean) actualValue)));
					logger.info("Field validation successful. Expected: equal to: "+ (Boolean) expectedValue + "Actual : "+ (Boolean) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : equal to :"+ (Boolean) expectedValue + "Actual : " + (Boolean) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case CHARACTER:
				try {
					assertThat("Field Validation", (Character) expectedValue, (equalTo((Character) actualValue)));
					logger.info("Field validation successful. Expected: equal to: "+ (Character) expectedValue + "Actual : "+ (Character) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : equal to :"+ (Character) expectedValue + "Actual : " + (Character) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
				
			default:
				try {
					assertThat("Field Validation", expectedValue, (equalTo(actualValue)));
					logger.info("Field validation successful. Expected: equal to: "+ expectedValue + "Actual : "+ actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : equal to :"+ expectedValue + "Actual : " + actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
			} // switch case ends here
		} else {
			isNotEqualsTo(expectedValue, actualValue);
		}
	}

	private void isNotEqualsTo(Object expectedValue, Object actualValue) {
		String primitiveTypeSubString[] = expectedValue.getClass().getName().split("java.lang.");
		
			switch(primitiveTypes.valueOf(primitiveTypeSubString[1].toUpperCase())) {
			case INTEGER:
				try {
					assertThat("Field Validation", (Integer) expectedValue, not(equalTo((Integer) actualValue)));
					logger.info("Field validation successful. Expected: not equal to: "+ (Integer) expectedValue + "Actual : "+ (Integer) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : not equal to :"+ (Integer) expectedValue + "Actual : " + (Integer) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case DOUBLE:
				try {
					assertThat("Field Validation", (Double) expectedValue, not(equalTo((Double) actualValue)));
					logger.info("Field validation successful. Expected: not equal to: "+ (Double) expectedValue + "Actual : "+ (Double) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : not equal to :"+ (Double) expectedValue + "Actual : " + (Double) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case STRING:
				try {
					assertThat("Field Validation", (String) expectedValue, not(equalTo((String) actualValue)));
					logger.info("Field validation successful. Expected: not equal to: "+ (String) expectedValue + "Actual : "+ (String) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : not equal to :"+ (String) expectedValue + "Actual : " + (String) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case LONG:
				try {
					assertThat("Field Validation", (Long) expectedValue, not(equalTo((Long) actualValue)));
					logger.info("Field validation successful. Expected: not equal to: "+ (Long) expectedValue + "Actual : "+ (Long) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : not equal to :"+ (Long) expectedValue + "Actual : " + (Long) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case FLOAT:
				try {
					assertThat("Field Validation", (Float) expectedValue, not(equalTo((Float) actualValue)));
					logger.info("Field validation successful. Expected: not equal to: "+ (Float) expectedValue + "Actual : "+ (Float) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : not equal to :"+ (Float) expectedValue + "Actual : " + (Float) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case BOOLEAN:
				try {
					assertThat("Field Validation", (Boolean) expectedValue, not(equalTo((Boolean) actualValue)));
					logger.info("Field validation successful. Expected: not equal to: "+ (Boolean) expectedValue + "Actual : "+ (Boolean) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : not equal to :"+ (Boolean) expectedValue + "Actual : " + (Boolean) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case CHARACTER:
				try {
					assertThat("Field Validation", (Character) expectedValue, not(equalTo((Character) actualValue)));
					logger.info("Field validation successful. Expected: not equal to: "+ (Character) expectedValue + "Actual : "+ (Character) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : not equal to :"+ (Character) expectedValue + "Actual : " + (Character) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
				
			default:
				try {
					assertThat("Field Validation", expectedValue, not(equalTo(actualValue)));
					logger.info("Field validation successful. Expected: not equal to: "+ expectedValue + "Actual : "+ actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : not equal to :"+ expectedValue + "Actual : " + actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : not equal to : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
			} // switch case ends here
	}
	
	@SuppressWarnings("incomplete-switch")
	public void greaterOrEqualsTo(Object expectedValue, Object actualValue) {
		String primitiveTypeSubString[] = expectedValue.getClass().getName().split("java.lang.");
		
			switch(primitiveTypes.valueOf(primitiveTypeSubString[1].toUpperCase())) {
			case INTEGER:
				try {
					assertThat("Field Validation", (Integer) expectedValue, greaterThanOrEqualTo((Integer) actualValue));
					logger.info("Field validation successful. Expected: greaterOrEqualsTo : "+ (Integer) expectedValue + "Actual : "+ (Integer) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : greaterOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : greaterOrEqualsTo :"+ (Integer) expectedValue + "Actual : " + (Integer) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : greaterOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case DOUBLE:
				try {
					assertThat("Field Validation", (Double) expectedValue, greaterThanOrEqualTo((Double) actualValue));
					logger.info("Field validation successful. Expected: greaterOrEqualsTo : "+ (Double) expectedValue + "Actual : "+ (Double) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : greaterOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : greaterOrEqualsTo :"+ (Double) expectedValue + "Actual : " + (Double) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : greaterOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			
			case LONG:
				try {
					assertThat("Field Validation", (Long) expectedValue, greaterThanOrEqualTo((Long) actualValue));
					logger.info("Field validation successful. Expected: greaterOrEqualsTo : "+ (Long) expectedValue + "Actual : "+ (Long) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : greaterOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : greaterOrEqualsTo :"+ (Long) expectedValue + "Actual : " + (Long) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : greaterOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case FLOAT:
				try {
					assertThat("Field Validation", (Float) expectedValue, greaterThanOrEqualTo((Float) actualValue));
					logger.info("Field validation successful. Expected: greaterOrEqualsTo : "+ (Float) expectedValue + "Actual : "+ (Float) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : greaterOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : greaterOrEqualsTo :"+ (Float) expectedValue + "Actual : " + (Float) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : greaterOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
						
			} // switch case ends here
	 }
	
	@SuppressWarnings({ "incomplete-switch", "unused" })
	public void lessOrEqualsTo(Object expectedValue, Object actualValue) {
		String primitiveTypeSubString[] = expectedValue.getClass().getName().split("java.lang.");
		
			switch(primitiveTypes.valueOf(primitiveTypeSubString[1].toUpperCase())) {
			case INTEGER:
				try {
					assertThat("Field Validation", (Integer) expectedValue, lessThanOrEqualTo((Integer) actualValue));
					logger.info("Field validation successful. Expected: lessOrEqualsTo : "+ (Integer) expectedValue + "Actual : "+ (Integer) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : lessOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : lessOrEqualsTo :"+ (Integer) expectedValue + "Actual : " + (Integer) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : lessOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case DOUBLE:
				try {
					assertThat("Field Validation", (Double) expectedValue, lessThanOrEqualTo((Double) actualValue));
					logger.info("Field validation successful. Expected: lessOrEqualsTo : "+ (Double) expectedValue + "Actual : "+ (Double) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : lessOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : lessOrEqualsTo :"+ (Double) expectedValue + "Actual : " + (Double) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : lessOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			
			case LONG:
				try {
					assertThat("Field Validation", (Long) expectedValue, lessThanOrEqualTo((Long) actualValue));
					logger.info("Field validation successful. Expected: lessOrEqualsTo : "+ (Long) expectedValue + "Actual : "+ (Long) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : lessOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : lessOrEqualsTo :"+ (Long) expectedValue + "Actual : " + (Long) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : lessOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				}
				break;
			case FLOAT:
				try {
					assertThat("Field Validation successful. Expected : LessOrEqualTo ", (Float) expectedValue, lessThanOrEqualTo((Float) actualValue));
					logger.info("Field validation successful. Expected: lessOrEqualsTo : "+ (Float) expectedValue + "Actual : "+ (Float) actualValue);
					reportConstant.getParentTestNode().log(Status.PASS, "Expected : lessOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
					
				} catch(AssertionError a) {
					logger.error("Field validation failed. Expected : lessOrEqualsTo :"+ (Float) expectedValue + "Actual : " + (Float) actualValue);
					reportConstant.getParentTestNode().log(Status.FAIL, "Expected : lessOrEqualsTo : " + expectedValue.toString() + "Actual : " + actualValue.toString());
				} catch(Exception e) {
					util.failTestCase("Assertion Failed", "Expected : lessOrEqualTo " + null + "Actual: " + null);
				}
			}
	 }
	
	private <T extends Comparable<T>> void lessComparison(T expectedValue, T actualValue) {
		assertThat("Field Validation", actualValue, lessThanOrEqualTo(expectedValue));
	}
	
	private <T extends Comparable<T>> void greaterComparison(T expectedValue, T actualValue) {
		assertThat("Field Validation", actualValue, greaterThanOrEqualTo(expectedValue));
	}
	
	public void contains(String expectedValue, String actualValue) {
		
		try {
			assertThat("Field Validation", actualValue, containsString(expectedValue));
			logger.info("Field validation successful. Expected: contains : "+ expectedValue + "Actual : "+ actualValue);
			reportConstant.getParentTestNode().log(Status.PASS, "Expected : contains : " + expectedValue.toString() + "Actual : " + actualValue.toString());
		} catch(AssertionError a) {
			logger.error("Field validation failed. Expected : contains :"+ expectedValue + "Actual : " + actualValue);
			util.failTestCase("Assertion Failed", "Expected : contains " + expectedValue + "Actual: " + actualValue);
		} catch(Exception e) {
			util.failTestCase("Assertion Failed", "Expected : contains " + null + "Actual: " + null);
		}		
	}
}
