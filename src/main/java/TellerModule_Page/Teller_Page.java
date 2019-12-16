package TellerModule_Page;

import utility.Keyword;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.relevantcodes.extentreports.LogStatus;

import utility.BaseClass;

import utility.fluWaits;


public class Teller_Page extends BaseClass{

	static String netAmt;
	public static void Tellercreation() throws Throwable
	{
		
		Keyword.sendText("TT_transid",data.get("Teller_id"), driver);
		
		Keyword.clickElement("TT_Edit",driver);
		
		Keyword.clearElement("TT_user", driver);
		Keyword.sendText("TT_user",data.get("Username"), driver);
		
	}
	
	public static void DepositForeignCurrency() throws Throwable
	{
	
		Keyword.sendText("TT_Account",data.get("Account"), driver);
		Keyword.sendText("TT_DepositAmount",data.get("DepositAmount"), driver);
		Keyword.sendText("TT_Unit",data.get("Unit"), driver);
		Keyword.sendText("TT_CreditCurrency",data.get("CrCurrency"), driver);
		
		Keyword.clickElement("validatebutton",driver);
		
		
		netAmt =  Keyword.getTextFromElement("TT_NetCredit", driver);
		System.out.println(netAmt);
		
		
	}
	
	public static void AccountingEntry(String Transaction_id) throws Throwable
	{
		Keyword.clearElement("TT_TransactionRef", driver);
		
		Keyword.sendText("TT_TransactionRef",Transaction_id, driver);
		//Thread.sleep(3000);
		Keyword.clickElement("TT_Find", driver);
		
		Re.addStepLog( "<b>Clicked on the Find button.<b>");
		
		 Keyword.pageHandleOnlySelectedPage("List Of Entries", driver);
		String FcyAmt = Keyword.getTextFromElement("TT_FcyAmt", driver);
		if(FcyAmt.equalsIgnoreCase(netAmt))
		{
			Re.addStepLog( "<b>Entries verified successfully.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} 
		else 
		{
			Re.addStepLog( "<b>Entries not verified.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
			 
		 }
		
		
	}
	
}
