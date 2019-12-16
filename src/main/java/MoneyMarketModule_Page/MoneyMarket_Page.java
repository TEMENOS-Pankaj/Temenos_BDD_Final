package MoneyMarketModule_Page;

import utility.BaseClass;
import utility.Keyword;

public class MoneyMarket_Page extends BaseClass{

	
	public static void MoneyMarket(String TestcaseId,String Transaction_id) throws Throwable
	{
		
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		
		if(homepagepagetitle.equalsIgnoreCase("MONEY MARKET"))
		{
			/*test.log(LogStatus.PASS, "<b>Successfully verified the Money Market page. <b>"
					+ test.addScreenCapture(captureScreenShot(driver, "MoneyMarket page verification")));*/
			
			Re.addStepLog("Successfully verified the Money market page.<br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Successfully verified the Money market page."));

		} 
	else {
			/*test.log(LogStatus.FAIL, "<b>Unable to verify the Money Market page.<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Unable to verify the Money Market page")));*/
			Re.addStepLog("Unable to verify the Money market page.<br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Money Market creation failed"));
		}
	
		Keyword.pageHandleOnlySelectedPage("MONEY MARKET", driver);
		driver.manage().window().maximize();
		
		Keyword.sendText("MM_DueDate",data.get("IntDueDate"), driver);
		
	Keyword.sendText("MM_Counterparty",data.get("CounterParty"), driver);
	
	//Keyword.sendText("FX_Dealdate",data.get("DealDate"), driver);
	Keyword.sendText("MM_Currency",data.get("Currency"), driver);
	Keyword.sendText("MM_Amount",data.get("Amount"), driver);
	Keyword.sendText("MM_Interestrate",data.get("InterestRate"), driver);
	Keyword.sendText("MM_CallDays",data.get("Notice"), driver);
	
	Keyword.clickElement("validatebutton",driver);
	//Thread.sleep(3000);
	Keyword.clickElement("commitButton",driver);
//	Thread.sleep(3000);
	
	if(Keyword.isElementVisible("acceptOverrideText",driver))
	{
	Keyword.acceptOverRideClick("acceptOverrideText", driver);
	}
	
	//test.log(LogStatus.PASS, "<b>Committed the record using the option highlighted.<b>");
	Re.addStepLog("Committed the record using the option highlighted.<br>");

	String txnMsg = Keyword.getTextFromElement("transactionText", driver);
	Transaction_id = txnMsg.substring(14,26);
	
	System.out.println("******"+Transaction_id);
	
	//outputExcelOperation("MoneyMarket_Call",TestcaseId,Transaction_id);
	writeOutputinDB("MoneyMarket_Call", TestcaseId, "Transaction_id", Transaction_id);
	if (txnMsg.contains("Txn Complete")) {
		/*test.log(LogStatus.PASS, "<b>Transaction is completed and the Money market Id is generated<b>"
				+ test.addScreenCapture(captureScreenShot(driver, "Transaction created")));*/
		Re.addStepLog("Transaction is completed and the Money market Id is generated<br>");
		 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction is completed and the Money market Id is generated"));

		
		
	} else {
		/*test.log(LogStatus.FAIL, "<b>Transaction is not completed<b>"
				+ test.addScreenCapture(captureScreenShot(driver, "Transaction creation failed")));*/
		
		Re.addStepLog("Transaction is not completed.<br>");
		 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
	}
	
	
}
	
	public static void AuthoriseMM(String Transaction_id) throws Throwable
	{
		
		Keyword.clearElement("MM_Trans", driver);
		Keyword.sendText("MM_Trans",getOutputFromDB("MoneyMarket_Call","TC_01","Transaction_id"), driver);
		
		Keyword.clickElement("MM_Find", driver);
		
	driver.manage().window().maximize();
		
		Keyword.clickElement("MM_FirstAuth",driver);
		Keyword.clickElement("MM_SecAuth",driver);
		
	/*	test.log(LogStatus.PASS,
				"<b>authorize the record.<b>" + test.addScreenCapture(captureScreenShot(driver, "authorize record")));*/
		Re.addStepLog("Authorise the record.<br>");
			
			String txnMsgAuth = Keyword.getTextFromElement("transactionText", driver);
			if (txnMsgAuth.contains("Txn Complete")) {
				/*test.log(LogStatus.PASS, "<b>Transaction is authorized and the Customer Id is generated<b>"
						+ test.addScreenCapture(captureScreenShot(driver, "Transaction created")));*/
				Re.addStepLog("Transaction is authorized and the MM Id is generated<br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation authorised"));

			} else {
				/*test.log(LogStatus.FAIL, "<b>Transaction is not completed<b>"
						+ test.addScreenCapture(captureScreenShot(driver, "Transaction creation failed")));*/
				Re.addStepLog("Transaction is not completed<br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));


			}
		
	}
	
	public static void AccountEntries(String Transaction_id) throws Throwable
	{
		
		Keyword.clearElement("MM_Trans", driver);
		Keyword.sendText("MM_Trans",getOutputFromDB("MoneyMarket_Call","TC_01","Transaction_id"), driver);
		
		Keyword.clickElement("MM_Find", driver);
		
		 Keyword.pageHandleOnlySelectedPage("List Of Entries",driver);
			
			
			String Fcyamt = Keyword.getTextFromElement("MM_AcctEntries", driver);
			System.out.println(Fcyamt);
			String BuyAmt = data.get("Amount");
			
			if(Fcyamt.equalsIgnoreCase(BuyAmt))
			{
				/*test.log(LogStatus.PASS, "<b>Entries verified successfully.<b>"
						+ test.addScreenCapture(captureScreenShot(driver, "Transaction created")));*/
				Re.addStepLog("Entries verified successfully.<br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Entries verified successfully."));

			}
			else 
			{
				/*test.log(LogStatus.FAIL, "<b>Entries not verified.<b>"
						+ test.addScreenCapture(captureScreenShot(driver, "Transaction creation failed")));*/
				Re.addStepLog("Entries not verified.<br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
				
				 
			 }
			
		
		
	}
	
}
