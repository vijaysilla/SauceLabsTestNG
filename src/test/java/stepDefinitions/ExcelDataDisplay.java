package stepDefinitions;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.*;
import utilities.TestContext;

public class ExcelDataDisplay 
{

	@Given("extract test data which was read from excel earlier")
	public void extract_test_data_which_was_read_from_excel_earlier() 
	{
		System.out.println("extract test data which was read from excel earlier");
		System.out.printf("Tags given in jenkins pipeline - %s",System.getProperty("tags"));
	}

	@Then("Display read values from test data file")
	public void display_read_values_from_test_data_file() 
	{
		System.out.println("Display read values from test data file");
	}

}
