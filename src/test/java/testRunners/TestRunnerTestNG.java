package testRunners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(
	monochrome=true,
	features= 	{"classpath:features"},
	glue = 		{"stepDefinitions","hooks"},
	plugin = 	{"html:test-output/reports/htmlreport.html", "json:test-output/reports/suaceProject.json",
				 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				 "pretty", "json:target/cucumber.json"
			 	},
	tags = "@exceltest"
	
)

public class TestRunnerTestNG extends AbstractTestNGCucumberTests
{
	@Override
//	@DataProvider(parallel=true)
	@DataProvider()
	public Object[][] scenarios()
	{
		return super.scenarios();
	}
}
