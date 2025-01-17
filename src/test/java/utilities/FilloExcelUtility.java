package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import DataReaders.ConfigFileReader;

public class FilloExcelUtility 
{
	Fillo fillo;
	String credsDataPath = ConfigFileReader.getInstance().getCredsDataPath();
	String dataFileName;
	String sheetName;
	String dataFullPath;
	String queriesPath = ConfigFileReader.getInstance().getQueriesPath();
	File flQueries;
	File flCreds;
//	FileInputStream credsFIS;
	FileInputStream queriesFIS;
	Map<String,String> queries;
	public Connection conn;
	public Recordset rs;
	Yaml yaml = new Yaml();
	
	public void excelGetConnection(String dataFileName, String sheetName) 
	{
		this.dataFileName = dataFileName;
		this.sheetName = sheetName;
		this.dataFullPath = new File(credsDataPath+"\\"+this.dataFileName).getAbsolutePath();
		
		this.flQueries = new File(this.queriesPath);
		this.flCreds = new File(this.dataFullPath);
		this.fillo = new Fillo();
		
		System.out.println("credentials full path - "+dataFullPath);
		System.out.println("queries full path - "+queriesPath);		
		
		try 
		{
			if (this.flQueries.exists())
			{
				this.queriesFIS = new FileInputStream(flQueries);
				this.queries = yaml.load(queriesFIS);
			}
			else
				throw new FileNotFoundException("Queries yaml file is not found check config.properties file");

			if (this.flCreds.exists())
			{
				this.conn = fillo.getConnection(this.dataFullPath);				
			}
			else
				throw new FilloException("Credentials file is not found check config.properties file");		
			
		} 
		catch (FileNotFoundException e) 
		{		
			e.printStackTrace();
		} 
		catch (FilloException e) 
		{
			e.printStackTrace();
		}		
	}	
	
	public String getSNOByUsername(String uname,String sheetname) throws FilloException
	{
		String returnValue;
		
		String query1 = this.queries.get("extractSNOByUname").replace("{sheet_name}", sheetname).replace("{cond_val}", uname);
//		String query1 = "SELECT SNO from emp_data where Username = 'standard_user'";
		System.out.println(query1);
		
		this.rs = this.conn.executeQuery(query1);
		System.out.printf("Query records - %d%n",this.rs.getCount());
		
		if (!this.rs.next())			
		{
			System.out.printf("There is no records found for user name - %s in sheet - %s%n",uname,sheetname);
			throw new FilloException("No matching record found for given query");
		}
		
		returnValue = this.rs.getField("SNO");
		System.out.printf("sno value - %s for user name - %s%n",returnValue,uname);

		return returnValue;

	}
	public String getPwdBySNO(String sheetname, String snoValue, String exprs) throws FilloException
	{
		String returnValue;
		
		String query1 = this.queries.get("extractSingleCondBySNO").replace("{extract_cols}", exprs).replace("{sheet_name}", sheetname).replace("{cond_val}", snoValue);
		System.out.println(query1);
		
		this.rs = this.conn.executeQuery(query1);
		
		if (!this.rs.next())			
		{
			System.out.printf("There is no records found for SNO - %s%n",snoValue);
			throw new FilloException("No matching record found for given query");
		}
		
		returnValue = this.rs.getField("Password");			
		System.out.printf("password value - %s for sno - %s%n",returnValue,snoValue);
		
		return returnValue;
	}
	public void updateExcelResults(String sheetname,String results,String condcol) throws FilloException
	{
		String query1 = queries.get("updateResultsBySNO").replace("{sheet_name}", sheetname).replace("{cond_val}", condcol).replace("{blank}", results);
		System.out.println(query1);
		if (this.conn == null)
			this.conn = fillo.getConnection(this.dataFullPath);	
		this.conn.executeUpdate(query1);
		
	}
}
