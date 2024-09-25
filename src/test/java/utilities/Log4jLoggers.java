package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jLoggers
{
	private Logger logger= LogManager.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	private static Log4jLoggers instance;
	
	private Log4jLoggers()
	{
		
	}
	public static Log4jLoggers getLogInstance()
	{
		if (instance == null)
			instance = new Log4jLoggers();
		return instance;
	}
	public void startTestCase(String text)
	{
		logger.info("********************************************************************************************");
		logger.info("************************* "+text+" ********************************************");
		logger.info("********************************-S-T-A-R-T**************************************************");		
	}
	public void endTestCase()
	{
		logger.info("********************************************************************************************");		
		logger.info("**********************************-E-N-D-***************************************************");		
	}
	public void info(String msg)
	{
		logger.info(msg);
	}
	public void debug(String msg)
	{
		logger.debug(msg);
	}
	public void warn(String msg)
	{
		logger.warn(msg);
	}
	public void error(String msg)
	{
		logger.error(msg);
	}
	public void fatal(String msg)
	{
		logger.fatal(msg);
	}
}
