package hooks;

import io.cucumber.java.*;
import utilities.GenericActions;
import utilities.Log4jLoggers;
import utilities.TestContext;

public class PrePostExecutionActions 
{
	TestContext testContext;
	
	public PrePostExecutionActions(TestContext testContext)
	{
		this.testContext = testContext;
	}
	
	@Before
	public void preExecutionSetup(Scenario scenario)
	{
		scenario.log("execution started");
		Log4jLoggers.getLogInstance().startTestCase(scenario.getName());
	}
	
	@After(order=1)
	public void evidenceCapture(Scenario scenario)
	{
		scenario.log("execution ended");
		if (scenario.isFailed())
		{
			GenericActions.CaptureScreenShot(testContext.getWebDriverManagerObject().getWebDriver(), scenario.getName(), scenario);
			Log4jLoggers.getLogInstance().info(scenario.getName()+" - failed and captured screen shot at screenshots folder");
		}		
	}
	
	@After(order=0)
	public void tearDown()
	{		
		Log4jLoggers.getLogInstance().endTestCase();
		if (testContext.getWebDriverManagerObject().getWebDriver() != null)		
			testContext.getWebDriverManagerObject().getWebDriver().close();
		if (testContext.getFilloExcelUtility().conn != null)
				testContext.getFilloExcelUtility().conn.close();
		if (testContext.getFilloExcelUtility().rs != null)
				testContext.getFilloExcelUtility().rs.close();		
	}
	
}
