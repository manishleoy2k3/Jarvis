package com.qaninjas.framework.interfaces;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface ITable {

	List<WebElement> getTable(By elementLocator);
}
