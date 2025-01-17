package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.codoid.products.exception.FilloException;

import io.cucumber.java.en.*;
import pageObjects.LoginPage;
import pageObjects.SwagLabsHomePage;
import utilities.Log4jLoggers;
import utilities.TestContext;
import utilities.FilloExcelUtility;

public class shoppingCart 
{
//	public WebDriver driver;
	public TestContext testContext; 
	public LoginPage loginPage;
	public SwagLabsHomePage homePage;
	public Log4jLoggers log = Log4jLoggers.getLogInstance();
	public FilloExcelUtility filloExcel; 
	public String sno;
	
	public shoppingCart(TestContext testContext)
	{
		this.testContext = testContext;
	}
	
	@Given("Log into sauce labs application with credentials {string} and password {string}")
	public void applicationLogin(String uname, String pwd) 
	{
		if (loginPage == null)
			loginPage = testContext.getPageManagerObject().getLoginPageObject();
		loginPage.insertUserName(uname);
		loginPage.insertPassword(pwd);
		loginPage.clickLoginButton();		
		if (loginPage.getErrorMessageExistence())
		{
			log.info("Login failed due to wrong credentials - "+uname+" and "+pwd);
			Assert.fail("Login failed due to wrong credentials - "+uname+" and "+pwd);			
		}
		log.info("Sauce lab application login successfully done");
	}

	
	@When("Log into sauce labs application with credentials {string} {string} from {string} located in {string}")
	public void insertLoginDetails(String uname, String pwd, String filename, String sheetname) throws FilloException, InterruptedException
	{
		String sno;
		
		filloExcel = testContext.getFilloExcelUtility();
		filloExcel.excelGetConnection(filename, sheetname);
		sno = filloExcel.getSNOByUsername(uname, sheetname);
		pwd = filloExcel.getPwdBySNO(sheetname, sno, "Password");
		
		loginPage.insertUserName(uname);
		loginPage.insertPassword(pwd);
		loginPage.clickLoginButton();
		Thread.sleep(2000);
	}

	@Then("Validate user login for user {string} from {string} located in {string}")
	public void validateCreds(String uname,String filename,String sheetname) throws FilloException 
	{
		boolean errorMsg = loginPage.getErrorMessageExistence();
		String results = (errorMsg == true ? "Fail" : "PASS");
//		Assert.assertFalse(errorMsg, "Login is failed with wrong credentials");
		String sno = filloExcel.getSNOByUsername(uname, sheetname);
		filloExcel.updateExcelResults(sheetname, results, sno);
	}
	
	@Then("Validate application launch")
	public void validateAppLaunch() 
	{
		loginPage = testContext.getPageManagerObject().getLoginPageObject();
		Assert.assertTrue(loginPage.validateLoginButtonExistence(), "User not at login page hence execution can't continue");
	}
	
	@Then("Reset application login page")
	public void resetToAppLoginpage()
	{
		homePage = testContext.getPageManagerObject().getSwagLabsHomePageObject();
		
		boolean errorExist = loginPage.getErrorMessageExistence();
		if (errorExist == true)
		{
			loginPage.clearLoginPage();
		}
		else
		{
			homePage.clickThreeLinesMenuButton();
			homePage.clickLogoutLink();
			loginPage.clearLoginPage();
		}
	}
}
