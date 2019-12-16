package utility;

import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.ReadOnlyFileSystemException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.apache.http.ConnectionClosedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class ErrorHandler {
	public static String errTrace="";
	static Logger log = Logger.getLogger(ErrorHandler.class);
	
	public static void handle(Throwable getError, WebDriver driver)throws Throwable
	{
		if(BaseClass.getAppStatus()==200){
			
			for(StackTraceElement stackTrace: Thread.currentThread().getStackTrace()){
				errTrace= errTrace + System.lineSeparator() + stackTrace.toString();
			}
	
			if (getError instanceof ConnectionClosedException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}
			else if (getError instanceof NoSuchSessionException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}
			else if (getError instanceof NoSuchElementException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}else if (getError instanceof TimeoutException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}else if (getError instanceof ScriptTimeoutException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}else if (getError instanceof UnhandledAlertException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}else if (getError instanceof SQLDataException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}else if (getError instanceof SQLException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}else if (getError instanceof SQLTimeoutException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}else if (getError instanceof SQLSyntaxErrorException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}else if (getError instanceof ReadOnlyFileSystemException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}else if (getError instanceof NoSuchFileException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}
			else if (getError instanceof InvalidPathException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}
			else if (getError instanceof ApplicationException){
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
				driver.quit();
				Assert.fail();
				}
			else{
				log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			}
		}
		else{
			driver.quit();
			log.info("Application is unavailable, script execution is terminated!!");
			log.info(getError.getLocalizedMessage()+"has occured"+errTrace);
			System.exit(0);
		}
	}


}
