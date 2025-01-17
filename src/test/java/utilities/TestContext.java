package utilities;

import java.util.List;
import java.util.Map;

import managers.PagesManager;
import managers.WebDriverManager;

public class TestContext 
{
	public WebDriverManager driverManager;
	public PagesManager pageManager;	
	public FilloExcelUtility excelData;
	
	public TestContext()
	{
		driverManager = new WebDriverManager();
		pageManager = new PagesManager(driverManager.getWebDriver());
		excelData = new FilloExcelUtility();
	}
	public WebDriverManager getWebDriverManagerObject()
	{
		return driverManager;
	}
	public PagesManager getPageManagerObject()
	{
		return pageManager;
	}
	public FilloExcelUtility getFilloExcelUtility()
	{
		return excelData;
	}
}
