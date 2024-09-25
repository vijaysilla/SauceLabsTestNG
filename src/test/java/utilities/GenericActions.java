package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;

public class GenericActions 
{
	public static void CaptureScreenShot(WebDriver driver, String testName, Scenario scenario)
	{
		TakesScreenshot  screen = (TakesScreenshot ) driver;
		byte[] bt = screen.getScreenshotAs(OutputType.BYTES);
		scenario.attach(bt, "jpeg/png", testName);
	}
}
