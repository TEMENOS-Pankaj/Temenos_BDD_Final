package FundTransferModule_Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import utility.BaseClass;
import utility.ErrorHandler;
import utility.Keyword;
import utility.fluWaits;

public class FundTransferOutwardRemittance_Page extends BaseClass {
	
	public static void transferOutward(String debitAcc) throws Throwable {
		Keyword.pageHandle(driver);
		Keyword.sendText("FTOR_debitAccount", debitAcc, driver);
		Keyword.sendText("FTOR_beneficiary", data.get("Beneficiary.1"), driver);
		Keyword.sendText("FTOR_creditAmount", data.get("Credit Amount"), driver);
		Keyword.sendText("FTOR_creditCurrency", data.get("Credit Currency"), driver);
		Re.addStepLog("<b>Entered the all the mandatory fields. <b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "T24 Model Bank mandatory field"));
		Keyword.clickElement("commitButton", driver);
		Re.addStepLog("<b>Committed the record using the option highlighted. <b>");
		Keyword.acceptOverRideClick("acceptOverrideText", driver);
	}
	public static boolean debitedAmountVerification(String debitAcc, String transferAmount) throws Throwable{
		boolean elementPresenceAfterSearch = false;
		
		 try 
         {
			 //	while(driver.findElement(By.xpath("//img[contains(@src,'right')]")).isDisplayed()) 
		
			WebElement EntriesTable=Keyword.getElement("FTOR_enquiryTable", driver);
			List<WebElement> rowVals = EntriesTable.findElements(By.tagName("tr"));
			int ListSize=rowVals.size();
	       for(int i=1; i<=ListSize; i++ ) {
	                     String debitAccount = fluWaits.getVisibleElement(By.xpath("//*[@id='r"+i+"']/td[2]"), driver).getText();
	                     												
	                     if(debitAccount.equals(debitAcc)) {
	                    	 
	                     	 String amount = fluWaits.getVisibleElement(By.xpath("//*[@id='r"+i+"']/td[9]"), driver).getText().split("\\.")[0].replace(",", "");
	                    	 if(amount.equalsIgnoreCase(transferAmount))
	                    	 {
	                    		 elementPresenceAfterSearch = true;
		                           break;
	                    	 }
				
	                     }
	                    
	       }
	     
	       }
		 catch (Throwable e) {
   			ErrorHandler.handle(e, driver);
                  elementPresenceAfterSearch = false;
           }
		return elementPresenceAfterSearch;
	}

}
