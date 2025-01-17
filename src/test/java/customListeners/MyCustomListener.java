package customListeners;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.messages.types.TestCaseStarted;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;

public class MyCustomListener implements EventListener  
{
	public ExtentReports extent;
	public ExtentTest test;
	
	public MyCustomListener()
	{
		if (extent == null)
			setupExtentReports();			
	}
	public void setupExtentReports()
	{
		Properties prop = new Properties();
		try 
		{
			FileReader file = new FileReader("\\src\\test\\resources\\extent.properties");
			prop.load(file);
			ExtentSparkReporter sparkReport = new ExtentSparkReporter(prop.getProperty("extent.reporter.spark.out"));
			sparkReport.loadXMLConfig(prop.getProperty("extent.reporter.spark.config"));
			extent = new ExtentReports();
			extent.attachReporter(sparkReport);
		} 
		
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	@Override
	public void setEventPublisher(EventPublisher publisher)
	{
		publisher.registerHandlerFor(TestCaseStarted.class, this::onScenarioStarted);
		publisher.registerHandlerFor(TestCaseFinished.class, this::onTestFinish);
	}
	private void onScenarioStarted(TestCaseStarted event)
	{
		
	}
	private void onTestFinish(TestCaseFinished event)
	{
		event.getTestCase().getName();
	}
	
}
