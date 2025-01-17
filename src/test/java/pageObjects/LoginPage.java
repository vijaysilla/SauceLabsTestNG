package pageObjects;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage
{
	private WebDriver driver;
	private WebDriverWait mywait;
	
	@FindBy(id="user-name")
	private WebElement userName;
	
	@FindBy(id="password")
	private WebElement pwd;
	
	@FindBy(id="login-button")
	private WebElement logButton;	
	
	@FindBy(xpath="//h3[@data-test='error']/button[@data-test=\"error-button\"]")
	private WebElement errorButton; 
	
	private By errorMsg;

	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		mywait = new WebDriverWait(driver,Duration.ofSeconds(5));
	}
	
	public void insertUserName(String unameText) 
	{		
		userName.sendKeys(unameText);
	}
	public void insertPassword(String pwdText)
	{
		pwd.sendKeys(pwdText);
	}
	public void clickLoginButton()
	{
		logButton.click();
	}
	public boolean validateLoginButtonExistence()
	{
		if (logButton.isDisplayed() && logButton.isEnabled())
		{
			return true;
		}
		return false;
	}
	public boolean getErrorMessageExistence()
	{	
		errorMsg = By.xpath("//h3[@data-test='error']");		
		try
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));			
			return driver.findElement(errorMsg).isDisplayed();
		}
		catch(TimeoutException e)
		{
			System.out.println("error message not found in given time");
			return false;
		}
		catch(ElementNotInteractableException e)
		{
			return false;
		}
	}
	public void clearLoginPage()
	{	if (userName.getAttribute("value") != null && !userName.getAttribute("value").isEmpty())
			userName.clear();
		if (pwd.getAttribute("value") != null && !pwd.getAttribute("value").isEmpty())
			pwd.clear();
		try
		{
			if (errorButton.isDisplayed() && errorButton.isEnabled())
				errorButton.click();			
		}
		catch (ElementNotInteractableException e) 
		{
			System.out.println(errorButton.getAttribute("name")+" is not available");
		}
//		catch (NoSuchElementException e)
//		{
//			System.out.println(errorButton.getAttribute("name")+" is not available");
//		}
	}
}
