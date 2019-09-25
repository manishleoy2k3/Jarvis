package com.qaninjas.keyword.parser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.qaninjas.framework.constants.KeywordConstants;
import com.qaninjas.keyword.Keywords;

public abstract class TestParser {

	private static  Logger logger = Logger.getLogger(TestParser.class);
	private KeywordConstants keywordConstant = new KeywordConstants();
	
	public void parseMethods(String packageName) {
		String className = "";
		String keyword = "";
		String methodName = "";
		List<String> keyWordInfo = new ArrayList<String>();
		logger.debug("reading classes from package " + packageName);
		
		try {
			Class[] classes = getClasses(packageName);
			for(Class<Class> clas : classes){
				className = clas.getCanonicalName();
				logger.debug("Collecting methods from class " + className);
				Class myClass = Class.forName(className);
				Method[] methods = myClass.getMethods();
				
				for(Method method : methods){
					keyWordInfo = new ArrayList<String>();
					Keywords anno = method.getAnnotation(Keywords.class);
					if(anno != null && !anno .description().isEmpty() && !anno.author().isEmpty()){
						logger.debug(anno.description().toString().trim() + " is selected");
						keyword = anno.description().toString().trim();
						methodName = method.getName().trim();
						keyWordInfo.add(0, className);
						keyWordInfo.add(1, methodName);
						keyWordInfo.add(2, anno.description());
						keyWordInfo.add(3, anno.category());
						keyWordInfo.add(4, anno.author());
						keywordConstant.updateTestCaseParseData(keyword, keyWordInfo);
						logger.debug(methodName + " is method name.");
					}
				}				 
			}
		} catch (Exception e) {
			logger.error("Exception..." + e);
			Assert.fail("Error while collection keywords");
		}		
	}

	private Class[] getClasses(String packageName) throws ClassNotFoundException, IOException{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		
		while(resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for(File directory : dirs){
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	private List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException{
		List<Class> classes = new ArrayList<Class>();
		if(directory.exists()){
			return classes;
		}
		File[] files = directory.listFiles();
		
		for(File file : files){
			if(file.isDirectory()){
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if(file.getName().endsWith(".class")){
				classes.add(Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}
}
