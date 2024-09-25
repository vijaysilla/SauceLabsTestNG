package managers;

import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import DataReaders.ConfigFileReader;

public class WebDriverManager
{
	private WebDriver driver;
	private static String browser_config;
	private static String env_config;
	private static String url_config;	
	private String browser_maven;
	private String browser_type;
	private ChromeOptions chromeoptions = new ChromeOptions();
	private FirefoxOptions firefoxoptions = new FirefoxOptions();
	
	
	public WebDriverManager()
	{
		
		url_config = ConfigFileReader.getInstance().getUrl();
		browser_config = ConfigFileReader.getInstance().getBrowser();
		env_config = ConfigFileReader.getInstance().getEnv();
		browser_maven = System.getProperty("browser");
		browser_type = browser_maven != null ? browser_maven : browser_config;
	}
	
	public WebDriver getWebDriver()
	{
		if (driver == null)
			driver = createWebDriver();
		return driver;
	}
	
	public WebDriver createWebDriver()
	{		
		switch (env_config)
		{
		case "remote":
			createRemoteWebDriver();
			break;
		case "local":
			createLocalWebDriver();
			break;
		default:
			throw new RuntimeException("env type is not defined properly in config.properties at path - "+ConfigFileReader.getInstance().getConfigFilePath());
		}
		driver.get(url_config);
		driver.manage().window().maximize();
		return driver;
	}
	
	public void createLocalWebDriver()
	{
		Object options;
		options = createBrowserOptions();
		if (options instanceof ChromeOptions)
		{
			driver = new ChromeDriver((ChromeOptions)options);
		}
		else if (options instanceof FirefoxOptions)
		{
			driver = new FirefoxDriver((FirefoxOptions)options);
		}		
	}
	
	public void createRemoteWebDriver()
	{

		
	}
	
	public Object createBrowserOptions()
	{
		Map <String, Object> prefs = new HashMap<>();
		Map<String, Object> configBrowserOptions;
		String download_path = System.getProperty("user.dir")+"\\";
	
		configBrowserOptions = ConfigFileReader.getInstance().getBrowserOptions();
			
		for(String key: configBrowserOptions.keySet())
		{
			switch (browser_type.toLowerCase())
			{
			case "chrome":
				
				if (key == "headless_browser" && configBrowserOptions.get(key) == "true")
				{
					chromeoptions.addArguments("--headless");
				}
				
				if (key == "disable_notifications" && configBrowserOptions.get(key) == "true")
				{
					chromeoptions.addArguments("--disable-notifications");
				}
				
				if (key == "dowload_path" && configBrowserOptions.get(key) != null)
				{
					download_path = download_path+configBrowserOptions.get(key);
					prefs.put("download.default_directory", download_path);
				}
				
				if (! prefs.isEmpty())
				{
					chromeoptions.setExperimentalOption("prefs", prefs);
				}
				break;			
				
			case "firefox":
				
				if (key == "headless_browser" && configBrowserOptions.get(key) == "true")
				{
					firefoxoptions.addArguments("--headless");
				}
				
				if (key == "disable_notifications" && configBrowserOptions.get(key) == "true")
				{
					firefoxoptions.addArguments("--disable-notifications");
				}
				
				if (key == "dowload_path" && configBrowserOptions.get(key) != null)
				{
					download_path = download_path+configBrowserOptions.get(key);
					firefoxoptions.addPreference("browser.download.dir", download_path);
					firefoxoptions.addPreference("browser.download.folderList", 2);
				}
				break;
				
			default:
				throw new RuntimeException("browser options are not created because browser type is not correctly defined in config.properties at path ..."+ConfigFileReader.getInstance().getConfigFilePath());
			}
		}
		
		switch(browser_type.toLowerCase()) 
		{
		case "chrome":
			return chromeoptions;

		case "firefox":
			return firefoxoptions;
		
		default:
			throw new RuntimeException("browser options are not defined because of browser type is not correctly defined...");
		}
		
	}
}
