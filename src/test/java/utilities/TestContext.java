package utilities;

import managers.PagesManager;
import managers.WebDriverManager;

public class TestContext 
{
	public WebDriverManager driverManager;
	public PagesManager pageManager;
	
	public TestContext()
	{
		driverManager = new WebDriverManager();
		pageManager = new PagesManager(driverManager.getWebDriver());
	}
	public WebDriverManager getWebDriverManagerObject()
	{
		return driverManager;
	}
	public PagesManager getPageManagerObject()
	{
		return pageManager;
	}
}
