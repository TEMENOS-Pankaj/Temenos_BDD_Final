package utility;
	import java.util.function.Function;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class fluWaits extends BaseClass{
	       protected static WebDriver driver;
	       public static WebElement getVisibleElement(By locator, WebDriver driver) throws Throwable {
	              WebElement element = null;
	              WebDriverWait wait = new WebDriverWait(fluWaits.waitForPageToLoad(driver), Integer.parseInt(timeout));
	              element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	              return element;
	       }
	       
	       public static boolean isElementVisible(By locator, WebDriver driver) throws Throwable {
	              WebElement element = null;
	              WebDriverWait wait = new WebDriverWait(fluWaits.waitForPageToLoad(driver), Integer.parseInt(timeout));
	              element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	              return element.isDisplayed();
	       }
	       
	       public static WebElement getClickableElement(By locator, WebDriver driver) throws Throwable {
	             WebElement element = null;
	             WebDriverWait wait = new WebDriverWait(fluWaits.waitForPageToLoad(driver), Integer.parseInt(timeout));
	             element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	             return element;
	       }
	       
	       public static WebDriver waitForPageToLoad(WebDriver driver) throws Throwable {
	             new WebDriverWait(driver, Integer.parseInt(timeout)).until(
	                       new Function<WebDriver, Object>() {
	                                  public Object apply(WebDriver webDriver) {
	                                         return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
	                                  }
	                           });
	             return driver;
	       }
	       
	}


