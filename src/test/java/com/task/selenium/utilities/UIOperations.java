package com.task.selenium.utilities;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIOperations {

	WebDriver driver;
			
	public UIOperations(WebDriver driver){
		this.driver = driver;
	}
	
			
	public void perform(Properties p,String operation,String objectName,String objectType,String value,WebDriverWait driverwait) throws Exception{
		System.out.println("");
		switch (operation.toUpperCase()) {
		case "CLICK":
			driverwait.until(ExpectedConditions.visibilityOf(driver.findElement(this.getObject(p, objectName, objectType))));
			//Perform click
			driver.findElement(this.getObject(p,objectName,objectType)).click();
			break;
		case "SETTEXT":
			driverwait.until(ExpectedConditions.visibilityOf(driver.findElement(this.getObject(p, objectName, objectType))));
			//Set text on control
			driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(value);
			break;
		case "GOTOURL":
			//Get url of application
			driver.get(value);//p.getProperty(value));
			driverwait.until(ExpectedConditions.visibilityOf(driver.findElement(this.getObject(p, objectName, objectType))));
			break;
		case "GETTEXT":
			driverwait.until(ExpectedConditions.visibilityOf(driver.findElement(this.getObject(p, objectName, objectType))));
			//Get text of an element
			driver.findElement(this.getObject(p,objectName,objectType)).getText();
			break;
		case "ENTER":
			driverwait.until(ExpectedConditions.visibilityOf(driver.findElement(this.getObject(p, objectName, objectType))));
			//Press enter from the keyboard
			driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(Keys.RETURN);
			break;
		case "SWITCHTOWINDOW":
			
			break;
		case "VALIDATE":
			if(driver.findElement(this.getObject(p, objectName, objectType)).getText() != null) {
				if(driver.findElement(this.getObject(p, objectName, objectType)).getText().contains(value)) {
					
				}
			}
			break;	
		default:
			break;
		}
	}
	
	/**
	 * Find element BY using object type and value
	 * @param p
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getObject(Properties p,String objectName,String objectType) throws Exception{
		//Find by xpath
		if(objectType.equalsIgnoreCase("XPATH")){
			
			return By.xpath(p.getProperty(objectName));
		}
		
		//Find by id
		if(objectType.equalsIgnoreCase("ID")){
			
			return By.id(p.getProperty(objectName));
		}

		//find by class
		else if(objectType.equalsIgnoreCase("CLASSNAME")){
			
			return By.className(p.getProperty(objectName));
			
		}
		//find by name
		else if(objectType.equalsIgnoreCase("NAME")){
			
			return By.name(p.getProperty(objectName));
			
		}
		//Find by css
		else if(objectType.equalsIgnoreCase("CSS")){
			
			return By.cssSelector(p.getProperty(objectName));
			
		}
		//find by link
		else if(objectType.equalsIgnoreCase("LINK")){
			
			return By.linkText(p.getProperty(objectName));
			
		}
		//find by partial link
		else if(objectType.equalsIgnoreCase("PARTIALLINK")){
			
			return By.partialLinkText(p.getProperty(objectName));
			
		}else
		{
			throw new Exception("Wrong object type");
		}
	}
}
