package utility;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Keyword extends BaseClass {
	protected static WebDriver driver;

	public static WebElement getElement(String parameter, WebDriver driver) throws Throwable {
		try {
			return fluWaits.getVisibleElement(getWebElement(parameter), driver);
		} catch (Throwable e) {
			ErrorHandler.handle(e, driver);
			return null;
		}
	}

	// To send text to a Text box.
	public static void sendText(String parameter, String keysToSend, WebDriver driver) throws Throwable {
		try {
			if(keysToSend!=null){
			fluWaits.getVisibleElement(getWebElement(parameter), driver).sendKeys(keysToSend);
			}
		}
		 catch (Throwable e) {
			ErrorHandler.handle(e, driver);
		}
	}

	// To click an element.
	public static void clickElement(String parameter, WebDriver driver) throws Throwable {
		try {
			fluWaits.getClickableElement(getWebElement(parameter), driver).click();
		} catch (Throwable e) {
			ErrorHandler.handle(e, driver);
		}

	}

	// To clear an element.
	public static void clearElement(String parameter, WebDriver driver) throws Throwable {
		try {
		
			fluWaits.getClickableElement(getWebElement(parameter), driver).clear();
			
		} catch (Throwable e) {
			ErrorHandler.handle(e, driver);

		}

	}

	// To select value from dropdown.
	public static void selectFromDropDown(String parameter, String text, WebDriver driver) throws Throwable {
		try {
		if(text!=null){
			Select drp = new Select(fluWaits.getVisibleElement(getWebElement(parameter), driver));
			drp.selectByVisibleText(text);
		}
		} catch (NumberFormatException e) {

		} catch (Throwable e) {
			ErrorHandler.handle(e, driver);

		}

	}
	
	
	//accept override
	
	public static void acceptOverRideClick(String parameter,WebDriver driver) throws Throwable {
		try{
		 //String getText=driver.findElement(By.xpath("//a[text()='Accept Overrides']")).getText();
		 String getText=driver.findElement((getWebElement(parameter))).getText();
		if(getText.contains("Accept Override"))
		{
			
			fluWaits.getClickableElement(getWebElement(parameter), driver).click();
		}}
	
			catch(Throwable e){
				System.out.println("Element not present");
			}
		
		
	
		
	}

	// To handle radio button and checkbox.
	public static void selectRadioButton(String parameter, WebDriver driver) throws Throwable {
		try {
			fluWaits.getVisibleElement(getWebElement(parameter), driver).click();
		} catch (Throwable e) {
			ErrorHandler.handle(e, driver);

		}
	}

	// To get text from an element.
	public static String getTextFromElement(String parameter, WebDriver driver) throws Throwable {
		try {
			return fluWaits.getVisibleElement(getWebElement(parameter), driver).getText();
		} catch (Throwable e) {
			ErrorHandler.handle(e, driver);
			return null;
		}
	}

	// To get value of any attribute from an element.
	public static String getValueOfAttribute(String parameter, String attribute, WebDriver driver) throws Throwable {
		try {
			return fluWaits.getVisibleElement(getWebElement(parameter), driver).getAttribute(attribute);
		} catch (Throwable e) {
			ErrorHandler.handle(e, driver);
			return null;
		}
	}

	// To handle frame
	public static void handleFrame(int frameno, WebDriver driver) throws Throwable {
		fluWaits.waitForPageToLoad(driver);
		driver.switchTo().frame(frameno);
	}
	
	// To handle frame by xpath
			public static void handleFrameByXpath(String parameter, WebDriver driver) throws Throwable {
				fluWaits.waitForPageToLoad(driver);
			    driver.switchTo().frame(fluWaits.getVisibleElement(getWebElement(parameter), driver));
			}



	// To check whether element is visible.
	public static boolean isElementVisible(String parameter, WebDriver driver) throws Throwable {
		try {
			return fluWaits.getVisibleElement(getWebElement(parameter), driver).isDisplayed();
		} catch (Throwable e) {
			ErrorHandler.handle(e, driver);
			return false;
		}
	}

	
	//TO check if the element is present
	
	public static boolean isElementPresent(String parameter, WebDriver driver) throws Throwable {
		try {
	        driver.findElement(getWebElement(parameter));
	        return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}
	

	
	
	
	// To check whether element is enabled.
	public static boolean isElementEnabled(String parameter, WebDriver driver) throws Throwable {
		try {
			return fluWaits.getVisibleElement(getWebElement(parameter), driver).isEnabled();
		} catch (Throwable e) {
			ErrorHandler.handle(e, driver);
			return false;

		}

	}

	// To check whether element is selected.
	public static boolean isElementSelected(String parameter, WebDriver driver) throws Throwable {
		try {
			return fluWaits.getVisibleElement(getWebElement(parameter), driver).isSelected();
		} catch (Throwable e) {
			ErrorHandler.handle(e, driver);
			return false;

		}

	}
	
	//To handle frame
	public static void pageHandle(WebDriver driver) throws Throwable{
		// Get current window handle
		String parentWinHandle = driver.getWindowHandle();
		// Get the window handles of all open windows
		Set<String> winHandles = driver.getWindowHandles();
		
		for(String handle: winHandles){
		    if(!handle.equals(parentWinHandle))
		    {
			    driver.switchTo().window(handle);
			    fluWaits.waitForPageToLoad(driver);
		    }
		}
	}
	

			
			//To go to the parent page
			public static void pageHandleMainPage(WebDriver driver) {
				String title ="T24 - Model Bank";
		     // Get the window handles of all open windows
		        Set<String> winHandles = driver.getWindowHandles();
		     // Loop through all handles
		        for(String handle: winHandles){
		        	driver.switchTo().window(handle);
		 
		        	 String titlepage = driver.getTitle();					
		            if(titlepage.equalsIgnoreCase(title)){
		            	driver.switchTo().window(handle);
		            	break;
		            }
		           
		        } 
			}
			
			//Close all page except main page
			public static void pageHandleOnlySelectedPage(String title,WebDriver driver) {
				 String currentHandle = null;
		     // Get the window handles of all open windows
		        Set<String> winHandles = driver.getWindowHandles();
		     // Loop through all handles
		        for(String handle: winHandles){
		        	driver.switchTo().window(handle);
		 
		        	 String titlepage = driver.getTitle();					
		            if(!titlepage.equalsIgnoreCase(title)){
		            	driver.switchTo().window(handle);
			            //driver.close();
		            }
		           if(titlepage.equalsIgnoreCase(title)) {
		        	    currentHandle=handle;
		            }
		           
		        } 
		        driver.switchTo().window(currentHandle);
			}
		

				//errorLoanDisbursement
				public static boolean errorTextVisible(String parameter,WebDriver driver) throws Throwable {
					try{
					 //String getText=driver.findElement(By.xpath("//a[text()='Accept Overrides']")).getText();
					 String getText=driver.findElement((getWebElement(parameter))).getText();
					if(getText.contains("exceeds available amount"))
					{
						return true;
					}else
						return false;
					}
						catch(Throwable e){
							System.out.println("Element not present");
							return false;
						}
				
				} 
			 

}
		 



