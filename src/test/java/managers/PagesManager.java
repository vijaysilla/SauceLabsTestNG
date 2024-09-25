package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.LoginPage;
import pageObjects.SwagLabsHomePage;

public class PagesManager 
{
	WebDriver driver;
	private LoginPage loginpage;
	private SwagLabsHomePage homepage;
	
	public PagesManager(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public LoginPage getLoginPageObject()
	{
		loginpage = new LoginPage(driver);
		return loginpage;
	}
	public SwagLabsHomePage getSwagLabsHomePageObject()
	{
		homepage = new SwagLabsHomePage(driver);	
		return homepage;
	}
	
}
