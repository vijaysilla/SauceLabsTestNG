package DataReaders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigFileReader
{
	private static final Properties prop = new Properties();
	private static final String confFilePath  = System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties";
	private static ConfigFileReader instance ;
	
	// use private constructor to prevent object creation to this class
	private ConfigFileReader()
	{		
		loadProperties();		
	}
	
	//Allow user to use the single instance created to this class 
	public static ConfigFileReader getInstance()
	{
		if (instance == null)
			instance = new ConfigFileReader();
		return instance;
	}
	public String getConfigFilePath()
	{
		return confFilePath;
	}
	public void loadProperties()
	{
//		prop = new Properties();		
//		confFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties";
		try 
		{
			FileReader file = new FileReader(confFilePath);
			try 
			{
				
				prop.load(file);
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) 
		{					
			e.printStackTrace();
			throw new RuntimeException("config.properties not found at path "+confFilePath);
		}		
	}
	public String getUrl() 
	{
		
		String urlConfig = prop.getProperty("url");
		if (urlConfig != null)
			return urlConfig;
		else
			throw new RuntimeException("url is not defined in config file at path "+confFilePath);
	}
	public String getEnv()
	{
		String envConfig = prop.getProperty("env");
		if (envConfig != null) 
			return envConfig;
		else
			throw new RuntimeException("env is not defined in config file at path "+confFilePath);
	}
	public String getBrowser()
	{
		String browserConfig = prop.getProperty("browser_config");		
		if (browserConfig != null) 
			return browserConfig;
		else
			throw new RuntimeException("browser  is not defined in config file at path "+confFilePath);		
	}
	public Map getBrowserOptions()
	{
		Map<String,Object> confBrowserOptions = new HashMap<>();
		confBrowserOptions.put("headless_browser", prop.getProperty("headless_browser"));
		confBrowserOptions.put("disable_notifications", prop.getProperty("disable_notifications"));
		confBrowserOptions.put("dowload_path", prop.getProperty("dowload_path"));
		
		return confBrowserOptions;
	}
}
