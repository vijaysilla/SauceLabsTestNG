package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import pageObjects.LoginPage;
import pageObjects.SwagLabsHomePage;
import utilities.Log4jLoggers;
import utilities.TestContext;

public class shoppingCart 
{
//	public WebDriver driver;
	public TestContext testContext;
	public LoginPage loginPage;
	public SwagLabsHomePage homePage;
	public Log4jLoggers log = Log4jLoggers.getLogInstance();
	
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

	@Given("User at home page or not")
	public void user_at_home_page_or_not() 
	{

	}

	@Then("validate each item in home page haveing Add to cart button")
	public void validate_each_item_in_home_page_haveing_add_to_cart_button() 
	{

	}
}
