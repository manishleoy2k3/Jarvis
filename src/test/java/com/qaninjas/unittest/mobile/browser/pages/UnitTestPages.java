package com.qaninjas.unittest.mobile.browser.pages;

import com.qaninjas.framework.Report;
import com.qaninjas.unittest.desktop.browser.pages.AnnotationDemoPage;
import com.qaninjas.unittest.desktop.browser.pages.BrowserJavascriptExecutePage;
import com.qaninjas.unittest.desktop.browser.pages.HomePage;
import com.qaninjas.unittest.desktop.browser.pages.SeleniumEasyTestPage;

public interface UnitTestPages {

	HomePage browserElements = new HomePage();
	AnnotationDemoPage annotationDemo = AnnotationDemoPage.getInstance();
	Report report = Report.getInstance();
	SeleniumEasyTestPage seleniumEasyTest = new SeleniumEasyTestPage();
	BrowserJavascriptExecutePage jsExecute = new BrowserJavascriptExecutePage();
}
