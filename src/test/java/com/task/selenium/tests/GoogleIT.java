package com.task.selenium.tests;

import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.task.selenium.DriverBase;
import com.task.selenium.utilities.ReadDataFromExcelFile;
import com.task.selenium.utilities.ReadObject;
import com.task.selenium.utilities.UIOperations;

/**
 * GoogleIT class is a driver script to execute the tests on
 * https://www.google.de
 * 
 * @author Sunanda Jakeral
 *
 */
public class GoogleIT extends DriverBase {

	WebDriver webdriver = null;

	@Test(dataProvider = "testData")
	public void testLogin(String testcaseName, String keyword, String objectName, String objectType, String value)
			throws Exception {
		// TODO Auto-generated method stub

		if (testcaseName != null && testcaseName.length() != 0) {
			webdriver = new FirefoxDriver();
		}
		ReadObject object = new ReadObject();
		Properties allObjects = object.getObjectRepository();
		UIOperations operations = new UIOperations(webdriver);
		WebDriverWait wait = new WebDriverWait(webdriver, 100);
		
		// Call perform function to perform operation on UI
		if(keyword!=null) {
			operations.perform(allObjects, keyword, objectName, objectType, value, wait);
		}

	}

	@DataProvider(name = "testData")
	public Object[][] getDataFromDataprovider() throws IOException {
		Object[][] object = null;
		ReadDataFromExcelFile file = new ReadDataFromExcelFile();

		// Read the TestCase sheet
		Sheet testDataSheet = file.readExcel(System.getProperty("user.dir") + "\\", "TestCase.xls",
				"Google.de_DataSheet");
		// Find number of rows in excel file
		int rowCount = testDataSheet.getLastRowNum() - testDataSheet.getFirstRowNum();
		int columnCount = testDataSheet.getRow(1).getLastCellNum();

		object = new Object[rowCount][columnCount];
		for (int i = 1; i < rowCount; i++) {
			// Loop over all the rows
			Row row = testDataSheet.getRow(i);
			// Create a loop to print cell values in a row
			for (int j = 0; j < columnCount; j++) {
				if (null != row.getCell(j)) {
					// Print excel data in console
					object[i - 1][j] = row.getCell(j).toString();
					System.out.println(object[i - 1][j]);
				}
			}

		}

		return object;
	}

}