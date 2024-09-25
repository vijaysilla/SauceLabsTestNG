package testRunners;

import org.openqa.selenium.WebDriver;

import managers.WebDriverManager;
import utilities.Log4jLoggers;

public class testDemo {

	public static void main(String[] args) 
	{
		WebDriver driver;
		Log4jLoggers log = Log4jLoggers.getLogInstance();
		
		log.startTestCase("simple webdriver open");
		WebDriverManager manager = new WebDriverManager();
		log.info("chrome driver initialized");
		driver = manager.getWebDriver();		
		driver.get("https://www.saucedemo.com/");
		log.info("saudemo url opened");
		driver.close();
		log.info("chrome browser closed");
		log.endTestCase();
	}

}
