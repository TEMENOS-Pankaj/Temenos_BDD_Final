package ForexModule_Page;

import com.relevantcodes.extentreports.LogStatus;

import utility.BaseClass;
import utility.Keyword;

public class Forex_Page extends BaseClass{

	public static void ForexSpot() throws Throwable
	{
		
		//Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		
		/*if(homepagepagetitle.equalsIgnoreCase("FOREX"))
		{
			test.log(LogStatus.PASS, "<b>Successfully verified the Forex page. <b>");
					//+ test.addScreenCapture(captureScreenShot(driver, "Teller page verification")));
			 Re.addStepLog("Successfully verified the Forex page. <br>");
			  
		} 
	else {
			test.log(LogStatus.FAIL, "<b>Unable to verify the Forex page.<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Teller page verification")));
			Re.addStepLog("Unable to verify the Forex page.<br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Unable to verify the Forex page."));
		}*/
		
		Keyword.sendText("FX_Counterparty",data.get("CounterParty"), driver);
		
		Keyword.sendText("FX_Dealdate",data.get("DealDate"), driver);
		Keyword.sendText("FX_CurrencyBought",data.get("CurrencyBought"), driver);
		Keyword.sendText("FX_CurrencySold",data.get("CurrencySold"), driver);
		Keyword.sendText("FX_AmountBought",data.get("AmountBought"), driver);
		
		Keyword.sendText("FX_SpotRate",data.get("SpotRate"), driver);
		
	}
	
	
	
	public static void AccountEntries(String transactionid) throws Throwable
	{
		Keyword.clearElement("TT_TransactionRef", driver);
		
		Keyword.sendText("TT_TransactionRef",transactionid, driver);
		
		Keyword.clickElement("TT_Find", driver);
		
		 // test.log(LogStatus.PASS, "<b>Clicked on the Find button.<b>");
		  Re.addStepLog("Clicked on the Find button <br>");
		  
		 Keyword.pageHandleOnlySelectedPage("List Of Entries",driver);
		
	
		String Fcyamt = Keyword.getTextFromElement("FX_FcyAmt", driver);
		System.out.println(Fcyamt);
		String BuyAmt = data.get("AmountBought");
		
		if(Fcyamt.equalsIgnoreCase(BuyAmt))
		{
			//test.log(LogStatus.PASS, "<b>Entries verified successfully.<b>");
				//	+ test.addScreenCapture(captureScreenShot(driver, "Transaction created")));
			 Re.addStepLog("Entries verified successfully. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Entries verified successfully."));

		}
		else 
		{
			/*test.log(LogStatus.FAIL, "<b>Entries not verified.<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Entries not verified")));*/
			Re.addStepLog("Entries not verified.<br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Entries not verified."));
			 
		 }
		
	}
	
	
	public static void ForwardEntries() throws Throwable
	{
		
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		
		if(homepagepagetitle.equalsIgnoreCase("FOREX"))
		{
			test.log(LogStatus.PASS, "<b>Successfully verified the Teller page. <b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Teller page verification")));
		} 
	else {
			test.log(LogStatus.FAIL, "<b>Unable to verify the Teller page.<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Teller page verification")));
		}
		
Keyword.sendText("FX_Counterparty",data.get("CounterParty"), driver);
		
		Keyword.sendText("FX_Dealdate",data.get("DealDate"), driver);
		Keyword.sendText("FX_ValueDate",data.get("ValueDate"), driver);
		Keyword.sendText("FX_CurrencyBought",data.get("CurrencyBought"), driver);
		Keyword.sendText("FX_CurrencySold",data.get("CurrencySold"), driver);
		Keyword.sendText("FX_AmountBought",data.get("AmountBought"), driver);
		
		Keyword.sendText("FX_ForwardDate",data.get("ForwardRate"), driver);
		
		
	}
	
	public static void RevForwardEntries(String Tc_id) throws Throwable
	{
		
		String outputid = getOutputFromDB("ForexForward", Tc_id, "Transaction_id");
		
		System.out.println("+++____++++---"+outputid);
		Keyword.clearElement("FX_TransRef", driver);
		Keyword.sendText("FX_TransRef",outputid, driver);
		
		Keyword.clickElement("TT_Find", driver);
		
	}
}
