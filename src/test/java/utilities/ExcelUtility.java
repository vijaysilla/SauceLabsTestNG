package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DataReaders.ConfigFileReader;

public class ExcelUtility 
{
	private static ExcelUtility instance;
	public String filePath;
	public Workbook wb;
	public Sheet sheet;

	private ExcelUtility()
	{
		
	}
	public static ExcelUtility getInstance() 
	{
		if (instance == null)
			instance = new ExcelUtility();
		return instance;
	}
	public List<Map<String, String>> readTestData()
	{
		List<Map<String,String>> excelData = new ArrayList<>();
		filePath = System.getProperty("user.dir")+"//"+ConfigFileReader.getInstance().getTestDataPath();
		try 
		{
			FileInputStream file = new FileInputStream(filePath);
			wb = new XSSFWorkbook(file);
			sheet = wb.getSheetAt(0);
			Row header = sheet.getRow(0);
			
			int rowsCount = sheet.getLastRowNum();
			System.out.printf("Total row numbers - %s%n",rowsCount);
			for(int rowpos=1; rowpos<=rowsCount; rowpos++)
			{
				Row row = sheet.getRow(rowpos);
				Map<String, String> rowdata = new HashMap<>();
				int colsCount = row.getLastCellNum();
				for(int colpos=0; colpos<=colsCount-1; colpos++)
				{
					Cell cell = row.getCell(colpos);
					String key = header.getCell(colpos).toString();					
					String value = row.getCell(colpos).toString();					
					rowdata.put(key, value);
				}
				excelData.add(rowdata);
			}
//			System.out.println(excelData);
//			for(int i=0; i<excelData.size();i++)
//			{
//				System.out.println(excelData.get(i));
//			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return excelData;
	}
	
}
