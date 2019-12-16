package FundTransferModule_Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utility.BaseClass;
import utility.ErrorHandler;
import utility.Keyword;
import utility.fluWaits;

public class FundTransfer_Page extends BaseClass {
	
	
	//Transfer Between Accounts
	public static void transferBetweenAccounts(String accNo) throws Throwable
	{
		Keyword.pageHandleOnlySelectedPage("FUNDS.TRANSFER", driver);
		Keyword.sendText("debitAccount_Textbox", data.get("Debit Account"), driver);
		Re.addStepLog("<b>Debit account:<b>"+ data.get("Debit Account"));
		
	
		Keyword.sendText("debitCurrency_Textbox", data.get("Debit Currency"), driver);
		Re.addStepLog("<b>Debit Currency:<b>"+ data.get("Debit Currency"));
		Keyword.pageHandleOnlySelectedPage("FUNDS.TRANSFER", driver);

		Keyword.sendText("debitAmount_Textbox", data.get("Debit Amount"), driver);
		Re.addStepLog("<b>Debit Amount:<b>"+ data.get("Debit Amount"));
		Keyword.pageHandleOnlySelectedPage("FUNDS.TRANSFER", driver);
		
		//get data from account: account no 
		Keyword.sendText("credit Account_Textbox",accNo, driver);
		Re.addStepLog("<b>Credit Account:<b>"+ accNo);
	
		Keyword.sendText("creditCurrency_Textbox", data.get("Credit Currency"), driver);
		Re.addStepLog("<b>Debit Currency:<b>" +data.get("Credit Currency"));
		Keyword.pageHandleOnlySelectedPage("FUNDS.TRANSFER", driver);
		
		Keyword.clickElement("commitButton", driver);
		Re.addStepLog("<b>Clicked on commit button.<b>");
		Keyword.pageHandleOnlySelectedPage("FUNDS.TRANSFER", driver);
		
		Keyword.acceptOverRideClick("acceptOverrideText", driver);
	
	}

	public static boolean debitedAmountVerification(String transactionId, String transferAmount) throws Throwable{
		boolean elementPresenceAfterSearch = false;
		
		 try 
         {
			 //	while(driver.findElement(By.xpath("//img[contains(@src,'right')]")).isDisplayed()) 
		
			WebElement EntriesTable=Keyword.getElement("EntriesTableSize", driver);
			List<WebElement> rowVals = EntriesTable.findElements(By.tagName("tr"));
			int ListSize=rowVals.size();
	       for(int i=1; i<=ListSize; i++ ) {
	                     String transaction = fluWaits.getVisibleElement(By.xpath("//*[@id='r"+i+"']/td[3]"), driver).getText();
	                     												
	                     if(transaction.equals(transactionId)) {
	                    	 
	                     	 String amount = fluWaits.getVisibleElement(By.xpath("//*[@id='r"+i+"']/td[5]"), driver).getText();
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

	public static String checkbalance() throws Throwable	{
		String initialBlce = null;
		WebElement EntriesTable=Keyword.getElement("EntriesTableSize", driver);
		List<WebElement> rowVals = EntriesTable.findElements(By.tagName("tr"));
		int ListSize=rowVals.size();
		for(int i=1;i<=ListSize;i++) {
			if(i==ListSize) {
				
			 initialBlce=fluWaits.getVisibleElement(By.xpath("//*[@id='r"+i+"']/td[5]"),driver).getText();
			 driver.close();
			}
		}
		
		return initialBlce ;
		
	}

	public static String checkinitialbalance(String accountNO) throws Throwable	{
		Keyword.pageHandleOnlySelectedPage("Statement Entries Today", driver);
		driver.manage().window().maximize();
		Keyword.clearElement("AccountNoFindTextBox", driver);
		Keyword.sendText("AccountNoFindTextBox", accountNO, driver);
		Keyword.clickElement("AccountNoFindButton", driver);
		driver.manage().window().maximize();
		String initBlnce=null;
	
		    if(Keyword.isElementPresent("LastPageButton",driver)) {
		    	Keyword.clickElement("LastPageButton", driver);
		    	initBlnce= checkbalance();
		    }
		    else {
		     initBlnce= checkbalance();
		    }
		return initBlnce;	
	}
public static boolean checkentries(String transactionId, String transferAmount) throws Throwable {
	boolean result1 = false;
	boolean result2= false;
	boolean result=debitedAmountVerification(transactionId, transferAmount);
	if(result) {
		return result;
	}
	else
		try {
		while(driver.findElement(By.xpath("//a[@title='Next Page']")).isDisplayed()) {
			Keyword.clickElement("EntriesNextButton", driver);
			 result1=debitedAmountVerification(transactionId, transferAmount);
			
		}
		
	return result1;
		}
	catch(Exception E) {
		 result2=debitedAmountVerification(transactionId, transferAmount);
		 return result2;
	}
}
}
