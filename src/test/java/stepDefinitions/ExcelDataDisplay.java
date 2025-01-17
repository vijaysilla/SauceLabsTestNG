package stepDefinitions;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.*;
import utilities.TestContext;

public class ExcelDataDisplay 
{
	TestContext testContext;
	List<Map<String,String>> excelData;
	public ExcelDataDisplay(TestContext testContext)
	{
		this.testContext = testContext;
	}
	@Given("extract test data which was read from excel earlier")
	public void extract_test_data_which_was_read_from_excel_earlier() 
	{
		excelData = testContext.excelData;
	}

	@Then("Display read values from test data file")
	public void display_read_values_from_test_data_file() 
	{
		System.out.println(excelData);
	}

}
