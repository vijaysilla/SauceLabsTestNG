package stepDefinitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import DataReaders.ConfigFileReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import utilities.ExcelUtility;
import utilities.TestContext;

public class ExcelData 
{
	List<Map<String,String>> excelData;
	TestContext testContext;
	public ExcelData(TestContext testContext)
	{
		this.testContext = testContext;
		
	}
	@Given("Read the test data from data file")
	public void readfile() 
	{
		excelData = ExcelUtility.getInstance().readTestData();
		testContext.excelData = excelData;
	}
	@Then("Display read values from test data file at row num {string}")
	public void displayValuesAtRow(String rowno, DataTable dt) 
	{
		Integer rownum;
		List<Map<String,String>> dtTestData = dt.asMaps();
		System.out.println(dtTestData);
		for(Map<String,String> dtRow:dtTestData)
		{
			rownum = Integer.parseInt(dtRow.get("SNO"));
			System.out.printf("Row number - %d%n",rownum);
			Map<String,String> rowdata = excelData.get(rownum-1); 
			rowdata.forEach((key,value) ->
											{
												System.out.printf("%s - %s%n",key,value);													
											}
							);			
		}			
	}
	@Then("display again excel values")
	public void display_again_excel_values() 
	{
		System.out.println(excelData);
	}
}
