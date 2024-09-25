package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
	private WebDriver driver;
	
	@FindBy(id="user-name")
	private WebElement userName;
	
	@FindBy(id="password")
	private WebElement pwd;
	
	@FindBy(id="login-button")
	private WebElement logButton;
	
	@FindBy(xpath="//h3[@data-test='error']")
	private WebElement errorMsg;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
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
	public boolean getErrorMessageExistence()
	{	
		return errorMsg.isDisplayed();
	}
	
}
