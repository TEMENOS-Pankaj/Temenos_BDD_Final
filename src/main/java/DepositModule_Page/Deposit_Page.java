package DepositModule_Page;

import java.awt.Frame;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import LoginandHome_Page.Home_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import utility.BaseClass;
import utility.ErrorHandler;
import utility.Keyword;
import utility.fluWaits;

public class Deposit_Page extends BaseClass {
	
	public static void goToDepositPage()throws Throwable{
		Keyword.handleFrame(1, driver);
		Keyword.clickElement("userMenu", driver);
		Keyword.clickElement("productCatalogMenu", driver);
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		Keyword.handleFrameByXpath("productGroupFrame", driver);
		Keyword.clickElement("depositsMenu", driver);
		Keyword.clickElement("termDepositsMenu", driver);
		driver.switchTo().parentFrame();
		Keyword.handleFrameByXpath("productsFrame", driver);
		switch(data.get("Term")){
			case "Short":
				Keyword.clickElement("shortTermDepositLink", driver);	
				break;
			case "12 Months":
				Keyword.clickElement("12monthsDepositLink", driver);	
				break;
			case "Call":
				Keyword.clickElement("callDepositLink", driver);	
				break;
			case "3 Months":
				Keyword.clickElement("3monthsDeposit", driver);	
				break;
			case "5 Year":
				Keyword.clickElement("5yearDepositLink", driver);	
				break;
			case "6 Months":
				Keyword.clickElement("6monthsDeposit", driver);	
				break;
			case "9 Months":
				Keyword.clickElement("9monthsDeposit", driver);	
				break;
			default:
				Keyword.clickElement("shortTermDepositLink", driver);
				
		}
		
	}
	public static void verifyDepositPage() throws Throwable{
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		if (homepagepagetitle.equalsIgnoreCase("AA ARRANGEMENT ACTIVITY")) {
			Re.addStepLog( "<b>Successfully verified the saving account page. <b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "SAVING ACCOUNT page verification"));
		} else {
			Re.addStepLog( "<b>Unable to verify the saving account.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "SAVING ACCOUNT page verification"));
		}
} 
	
	public static void createDeposit(String testCaseId) throws Throwable{
	
		Keyword.sendText("customerTextBox", getOutputFromDB("CU_Individual_Customer_Creation", "TC_01","CustomerId"), driver);
		Keyword.sendText("currencyTextBox", data.get("Currency"), driver);
		Keyword.clickElement("validatebutton", driver);
		Keyword.clickElement("commitment link", driver);
		Keyword.sendText("amountTextBox", data.get("Amount"), driver);
		//if(data.get("Term").contains("Short")) {
		//Keyword.sendText("termTextBox", data.get("Term").substring(6), driver);
		//Keyword.sendText("termTextBox", "9 M", driver);
		//}
		Keyword.clickElement("settlementInstructionsLink", driver);
		Keyword.sendText("settlementAccountId1", getOutputFromDB("AccountSBCreation", "TC_01","AccountId"), driver);
		Keyword.sendText("settlementAccountId2", getOutputFromDB("AccountSBCreation", "TC_01","AccountId"), driver);
		if(data.get("Term").equalsIgnoreCase("5 Year")) {
			Keyword.clickElement("advancedPayInTab", driver);
			Keyword.clearElement("accountDebitActivityTextBox", driver);
			Keyword.sendText("accountDebitActivityTextBox", "DEPOSITS-ADJUST-DEPOSITINT", driver);
			Keyword.clickElement("advancedPayOutTab", driver);
			Keyword.clearElement("accountCreditActivityTextBox", driver);
			Keyword.sendText("accountCreditActivityTextBox", "DEPOSITS-ADJUST-DEPOSITINT", driver);
		}
		Keyword.clickElement("validatebutton", driver);
		Keyword.clickElement("commitButton", driver);
		/*String getText=Keyword.getTextFromElement("depositDropDown", driver);
		if(getText.contains("Have you")) {*/
		Keyword.selectFromDropDown("depositDropDown", "RECEIVED", driver);
		Re.addStepLog( "<b>Entered the all the mandatory fields which are marked with asterisks symbol.<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "T24 Model Bank mandatory field"));
		Keyword.clickElement("commitButton", driver);
		Keyword.acceptOverRideClick("acceptOverride", driver);
		
		
		
	}
	public static String authoriseDeposit(String testCaseID,String activityId) throws Throwable{
		
		 // Home_Page.Homepage("ENQ AA.ARRANGEMENT.ACTIVITY-NAU");
		  Keyword.pageHandle(driver); 
		  Keyword.clearElement("arrangementTextBox", driver);
		  Keyword.clearElement("activityTextBox", driver);
		  Keyword.sendText("activityTextBox", activityId,driver);
		  Keyword.clickElement("findButton", driver);
		  Keyword.clickElement("authoriseDepositButton", driver);
		  driver.manage().window().maximize();
		  Keyword.clickElement("approveDeposit", driver);
		  Keyword.pageHandle(driver);
		  String activity=Keyword.getTextFromElement("activityIdDeposit", driver);
		  Keyword.clickElement("authoriseButton", driver);
		  String txnMsg2= Keyword.getTextFromElement("transactionMsg", driver);
		  if (txnMsg2.contains("Txn Complete")) {
			  Re.addStepLog( "<b>Transaction is completed and deposit is authorised.<b>");
			  Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
			} else {
				 Re.addStepLog( "<b>Transaction is not completed<b>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
			}
		  writeOutputinDB("Deposit", testCaseID, "Arrangement_id", activity);
		  return activity;
	}
	
	public static void validateDeposit(String activityId,String testCaseId) throws Throwable{
		/* Keyword.pageHandleMainPage("T24 - Model Bank",driver);
		  Keyword.handleFrame(0, driver);
		  Keyword.clickElement("signOffButton", driver);
		  loginPage.login(adminUsername, adminPassword);*/
		  Home_Page.Homepage("AA.ARRANGEMENT.ACTIVITY S "+activityId);
		  Keyword.pageHandle(driver); 
		  driver.manage().window().maximize(); 
		  boolean cust=Keyword.getTextFromElement("verifyCustomer", driver).equalsIgnoreCase(getOutputFromDB("CU_Individual_Customer_Creation", "TC_01","CustomerId"));
		  boolean arrng=Keyword.getTextFromElement("verifyActivity", driver).equalsIgnoreCase(activityId);
		  if(cust&& arrng) {
			  Re.addStepLog( "<b>Verified the values.<b>");
		  }
		  else {
			  Re.addStepLog( "<b>Error in verifying values.<b>");
		  }
		 
		  Re.addStepLog( "<b>Validated and verified all the values.<b>");
		  Re.addScreenCaptureFromPath(captureScreenShot(driver, "Validated."));
	}
	
	public static boolean transactionEntryVerification(String custNo, String LCYAmount) throws Throwable{
		boolean elementPresenceAfterSearch = false;
		WebElement EntriesTable=Keyword.getElement("DEP_EntriesTableSize", driver);
		List<WebElement> rowVals = EntriesTable.findElements(By.tagName("tr"));
		int ListSize=rowVals.size();
       for(int i=1; i<=ListSize; i++ ) { 
	       
	     
	              
	              try 
	              {																   
	                     String transaction = fluWaits.getVisibleElement(By.xpath("//*[@id='r"+i+"']/td[3]"), driver).getText();
	                     												
	                     if(transaction.equals(custNo)) {
	                    	 
	                    	 String amount = fluWaits.getVisibleElement(By.xpath("//*[@id='r"+i+"']/td[9]"), driver).getText().replace(",","").replace(".00", "");
	                    	 System.out.println("AAAAAAAAAAAAAAAAA"+amount);
	                    	 
	                    	 if(amount.equals(LCYAmount))
	                    	 {
	                    		 elementPresenceAfterSearch = true;
		                           break;
	                    	 }
	                     }
	              }
	              catch (Throwable e)
	              {
	      				 ErrorHandler.handle(e, driver);
	                     elementPresenceAfterSearch = false;
	              }
	       }
		return elementPresenceAfterSearch;
	}
	
public static void goToAmendDepositPage(String ArrangementId)throws Throwable{
		Keyword.handleFrame(1, driver);
		Keyword.clickElement("AA_Dep_UserMenuLink", driver);
		Keyword.clickElement("AA_Dep_RetailOperationLink", driver);
		Keyword.clickElement("AA_Dep_FindDepositLink", driver);
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		Keyword.clearElement("AA_Dep_ArrangementIdinputBox", driver);
		Keyword.sendText("AA_Dep_ArrangementIdinputBox", ArrangementId, driver);
		Keyword.clickElement("AA_Dep_FindButton", driver);
		Keyword.clickElement("AA_Dep_OverviewButton", driver);
		Keyword.pageHandleOnlySelectedPage("Arrangement Overview (Deposits) - Model Bank", driver);
		driver.manage().window().maximize();
		String title=driver.getTitle();
		if(title.equalsIgnoreCase("Arrangement Overview (Deposits) - Model Bank")) {
			Re.addStepLog("<b>Validated Deposit Page.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Validated."));
	  }
	  else {
		  	Re.addStepLog("<b>Incorrect Page.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "not Validated."));
	  }
		
	}


public static String AmendDepositPageOperation()throws Throwable{
	Keyword.clickElement("AA_Dep_NewActivityLink", driver);
	Keyword.pageHandleOnlySelectedPage("New User Activities", driver);
	driver.manage().window().maximize();
	Keyword.clickElement("AA_Dep_InterestcalLink", driver);
	Keyword.clickElement("validatebutton", driver);
	Keyword.clearElement("AA_Dep_FixedRateInputbox", driver);
	Keyword.sendText("AA_Dep_FixedRateInputbox", data.get("Fixed.1"), driver);
	
	Keyword.selectFromDropDown("AA_Dep_AddSelect", data.get("Margin.1.1Select"), driver);
	Keyword.selectFromDropDown("AA_Dep_SingleMarginDropDown", data.get("Margin.SingleMarginSelect"), driver);
	Keyword.clearElement("AA_Dep_SingleMarginBox", driver);
	Keyword.sendText("AA_Dep_SingleMarginBox", data.get("SingleMarginInputBox"), driver);
	Keyword.clickElement("commitButton", driver);
	String txnMsg = Keyword.getTextFromElement("AA_Dep_TxnCompMsg", driver);
	String activityId = txnMsg.substring(14, 32);
	if (txnMsg.contains("Txn Complete")) {
		Re.addStepLog("<b>Transaction is completed and deposit is amended. <b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
	} else {
		Re.addStepLog("<b>Transaction is not completed. <b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
	}
	
	return activityId;
	
}
public static void authorizeAmendementRecord(String activityId)throws Throwable{
	Keyword.handleFrame(1, driver);
	Keyword.clickElement("AA_Dep_UserMenuLink", driver);
	Keyword.clickElement("AA_Dep_RetailOperationLink", driver);
	Keyword.clickElement("AA_Dep_UnauthoriseAAALink", driver);
	Keyword.pageHandle(driver);
	driver.manage().window().maximize();
	Keyword.clearElement("AA_Dep_ActivityinputBox", driver);
	Keyword.sendText("AA_Dep_ActivityinputBox", activityId, driver);
	Keyword.clearElement("AA_Dep_ArrangementIdinputBox", driver);
	Keyword.clickElement("AA_Dep_FindButton", driver);
	driver.manage().window().maximize();
	System.out.println(driver.getTitle());
	Keyword.clickElement("AA_Dep_AuthorizeButtonbtn", driver);
//	driver.findElement(By.xpath("//img[@title='Authorise']")).click();
	
	Keyword.pageHandleOnlySelectedPage("Arrangement Overview (Deposits) - Model Bank", driver);
	driver.manage().window().maximize();
	Keyword.selectFromDropDown("AA_Dep_ApprovePendingSelect", data.get("PendingApproveSelect"), driver);
	Keyword.clickElement("AA_Dep_DrilldownAuth", driver);
	Keyword.pageHandleOnlySelectedPage("AA ARRANGEMENT ACTIVITY", driver);
	driver.manage().window().maximize();
	Keyword.clickElement("AA_Dep_AuthorizeButton", driver);
	String txnMsg = Keyword.getTextFromElement("AA_Dep_TxnCompMsg", driver);
	if (txnMsg.contains("Txn Complete")) {
		Re.addStepLog("<b>Transaction is completed and deposit is amended and authorized. <b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
	
	
	} else {
		Re.addStepLog("<b>Transaction is not completed. <b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));

	}
}

public static void AmendThreeMonthsDepositPageOperation()throws Throwable{
	Keyword.clickElement("AA_Dep_NewActivityLink", driver);
	Keyword.pageHandleOnlySelectedPage("New User Activities", driver);
	driver.manage().window().maximize();
	Keyword.clickElement("AA_Dep_taxDefinationLink", driver);
	//Keyword.pageHandleOnlySelectedPage("AA ARRANGEMENT ACTIVITY", driver);
	System.out.println(driver.getTitle());
	//driver.manage().window().maximize();
	//Keyword.clickElement("AA_Dep_validateDeal", driver);
	//Keyword.sendText("AA_Dep_propertyClass ", data.get("PropertyClassTextIn"), driver);
	//Keyword.sendText("AA_Dep_taxCode", data.get("taxCodeTextIn"), driver);
	//Keyword.sendText("AA_Dep_ taxCondition", data.get("taxConditionTextIn"), driver);
	Keyword.clickElement("AA_Dep_validateDeal", driver);
	Keyword.clickElement("AA_Dep_propertyClass", driver);
	//driver.findElement(By.xpath("//a[text()='Property Class.1']//parent::label//following::td[2]//input")).sendKeys("INTEREST");
	Keyword.sendText("AA_Dep_propertyClass", data.get("PropertyClassTextIn"), driver);
	//Keyword.sendText("AA_Dep_taxCode", data.get("taxCodeText"), driver);
	Keyword.sendText("AA_Dep_taxCondition", data.get("taxConditionText"), driver);
	Keyword.clickElement("AA_Dep_validateDeal", driver);
	Keyword.clickElement("AA_Dep_commitDeal", driver);
	String txnMsg = Keyword.getTextFromElement("AA_Dep_TxnCompMsg", driver);
	if (txnMsg.contains("Txn Complete")) {
		Re.addStepLog("<b>Transaction is completed and deposit is amended. <b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
	} else {
		Re.addStepLog("<b>Transaction is not completed. <b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
	}
}

public static void AmendThreeMonthsDepositAuthorization(String arrangementId)throws Throwable{
	Keyword.clearElement("AA_Dep_ActivityTextbox", driver);
	Keyword.clearElement("AA_Dep_arrangementEnq", driver);
	Keyword.sendText("AA_Dep_arrangementEnq", arrangementId, driver);
	Keyword.clickElement("AA_Dep_FindButton", driver);
	Keyword.clickElement("AA_Dep_AuthorizeButtonbtn", driver);
	Keyword.pageHandle(driver);
	driver.manage().window().maximize();
	Keyword.clickElement("approveDeposit", driver);
	Keyword.pageHandle(driver);
	Keyword.clickElement("authoriseButton", driver);
	String txnMsg = Keyword.getTextFromElement("AA_Dep_TxnCompMsg", driver);
	if (txnMsg.contains("Txn Complete")) {
		Re.addStepLog("<b>Transaction is completed and deposit is amended and authorized. <b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
	
	
	} else {
		Re.addStepLog("<b>Transaction is not completed. <b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));

	}
	
}

public static void DepositRThree(String Testcase_id) throws Throwable
{
	
	 Keyword.clearElement("arrangementTextBox", driver);
	
	Keyword.sendText("arrangementTextBox", getOutputFromDB("Deposit",Testcase_id,"Arrangement_id"), driver);
	  Keyword.clickElement("findButton", driver);
	
	  Keyword.clickElement("DE_Overview", driver);

	  System.out.println("Overview clicked");
	  
	  Keyword.clickElement("DE_NewActivity", driver);
	  
		 Keyword.pageHandle(driver);
		 driver.manage().window().maximize();
		 
	  
	  Keyword.clickElement("DE_DoActivity", driver);
	  
		 
	  Keyword.clickElement("DE_Commitment", driver);
	  
	  Keyword.clearElement("MM_CallDays", driver);
	  Keyword.sendText("MM_CallDays", data.get("RollOver3"), driver);
	  
	  Thread.sleep(3000);
	  
		Keyword.clickElement("validatebutton", driver);
		Keyword.clickElement("commitButton", driver);
	  
		if(Keyword.isElementVisible("acceptOverrideText",driver))
		{
		Keyword.acceptOverRideClick("acceptOverrideText", driver);
		}
		 String txnMsg = Keyword.getTextFromElement("transactionText", driver);
			String DepositMatID = txnMsg.substring(14,32);

			writeOutputinDB("DepositRollThree", Testcase_id,"Transaction_id", DepositMatID);
			Keyword.pageHandleOnlySelectedPage("T24 - Model Bank",driver);
			  Keyword.handleFrame(0, driver);
			  Keyword.clickElement("signOffButton", driver);
	
}

public static void RthreeAuthorise(String testcase_id) throws Throwable
{
	//loginPage.login(username,password);
	  Home_Page.Homepage("AAA A "+getOutputFromDB("DepositRollThree", testcase_id,"Transaction_id"));
	  System.out.println("AAA A "+getOutputFromDB("DepositRollThree", testcase_id,"Transaction_id"));
	  Keyword.pageHandle(driver); 
	  driver.manage().window().maximize(); 
	
		Keyword.clickElement("DE_Authorise", driver);
		 String txnMsg = Keyword.getTextFromElement("transactionText", driver);
		if (txnMsg.contains("Txn Complete")) 
		{
			/*test.log(LogStatus.PASS, "<b>Transaction is completed and the transfer Id is generated<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Transaction created")));*/
			 Re.addStepLog("Transaction is completed and the transfer Id is generated. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} 
		else
		{
			/*test.log(LogStatus.FAIL, "<b>Transaction is not completed<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Authorisation failed")));*/
			 Re.addStepLog("Transaction is not completed <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Authorisation failed"));
		}
		driver.close();
		Keyword.pageHandleOnlySelectedPage("T24 - Model Bank", driver);
		Keyword.handleFrame(0, driver);
		Keyword.clickElement("signOffButton", driver);
	  
}

public static void RthreeCheckingentries(String testcase_id) throws Throwable
{
	Home_Page.Homepage("ENQ AA.FIND.ARRANGEMENT.AD");
	
	 Keyword.pageHandle(driver);
	 driver.manage().window().maximize();
		String homepagepagetitle1 = driver.getTitle();
		if (homepagepagetitle1.equalsIgnoreCase("Find Deposit Arrangements")) {
			/*test.log(LogStatus.PASS, "<b>Successfully verified the arrangement enquiry page. <b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));*/
			 Re.addStepLog("Successfully verified the arrangement enquiry page. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification"));
		} else {
			/*test.log(LogStatus.FAIL, "<b>Unable to verify the arrangement enquiry page.<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));*/
			 Re.addStepLog("Unable to verify the arrangement enquiry page. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification failed"));
		}
		
		 
		  Keyword.clearElement("arrangementTextBox", driver);
		  Keyword.sendText("arrangementTextBox", getOutputFromDB("Deposit",testcase_id,"Arrangement_id"), driver);
		
		
		  Keyword.clickElement("findButton", driver);
		
		  
		  Keyword.clickElement("DE_Overview", driver);
		  
			Keyword.pageHandleOnlySelectedPage("Arrangement Overview (Deposits) - Model Bank", driver);
			 driver.manage().window().maximize();
		  
		  String Date = Keyword.getTextFromElement("DE_MaturityDate", driver);
		  
		  if(Date.equalsIgnoreCase(data.get("RollOver3")))
		  {
			  
			 /* test.log(LogStatus.PASS, "<b>Successfully verified the Maturity Date. <b>"
						+ test.addScreenCapture(captureScreenShot(driver, "Maturity Date verification")));*/
			  Re.addStepLog("Successfully verified the Maturity Date. <br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Maturity Date verification"));
			}
		  else 
		  {
				/*test.log(LogStatus.FAIL, "<b>Unable to verify the Maturity Date.<b>"
						+ test.addScreenCapture(captureScreenShot(driver, "Maturity Date verification")));*/
				
				 Re.addStepLog("Unable to verify the Maturity Date. <br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Maturity Date verification failed"));
			}
		  
}

public static void DepositRSix(String Testcase_id) throws Throwable
{
	 Keyword.clearElement("arrangementTextBox", driver);
	//  Keyword.sendText("arrangementTextBox", getOutputId("Deposit",testCaseId), driver);
	
	Keyword.sendText("arrangementTextBox", getOutputFromDB("Deposit",Testcase_id,"Arrangement_id"), driver);
	  Keyword.clickElement("findButton", driver);
	
	//  Keyword.pageHandle(driver);
	  Keyword.clickElement("DE_Overview", driver);

	  System.out.println("Overview clicked");
	  
	  Keyword.clickElement("DE_NewActivity", driver);
	  
		 Keyword.pageHandle(driver);
		 driver.manage().window().maximize();
		 
	  
	  Keyword.clickElement("DE_DoActivity", driver);
	  
	/*  Keyword.pageHandle(driver);
		 driver.manage().window().maximize();*/
		 
	  Keyword.clickElement("DE_Commitment", driver);
	  
	  Keyword.clearElement("MM_CallDays", driver);
	  Keyword.sendText("MM_CallDays", data.get("RollOver6"), driver);
	  
	  Thread.sleep(3000);
	  
		Keyword.clickElement("validatebutton", driver);
		Keyword.clickElement("commitButton", driver);
	  
		if(Keyword.isElementVisible("acceptOverrideText",driver))
		{
		Keyword.acceptOverRideClick("acceptOverrideText", driver);
		}
		 String txnMsg = Keyword.getTextFromElement("transactionText", driver);
			String DepositMatID = txnMsg.substring(14,32);
			//outputExcelOperation("DepositRollthree", testCaseId, DepositMatID);
			writeOutputinDB("DepositRollSix", Testcase_id,"Transaction_id", DepositMatID);
			Keyword.pageHandleOnlySelectedPage("T24 - Model Bank",driver);
			  Keyword.handleFrame(0, driver);
			  Keyword.clickElement("signOffButton", driver);
	
}

public static void RsixAuthorise(String testcase_id) throws Throwable
{
	//loginPage.login(username,password);
	  Home_Page.Homepage("AAA A "+getOutputFromDB("DepositRollSix", testcase_id,"Transaction_id"));
	  System.out.println("AAA A "+getOutputFromDB("DepositRollSix", testcase_id,"Transaction_id"));
	  Keyword.pageHandle(driver); 
	  driver.manage().window().maximize(); 
	
		Keyword.clickElement("DE_Authorise", driver);
		 String txnMsg = Keyword.getTextFromElement("transactionText", driver);
		if (txnMsg.contains("Txn Complete")) 
		{
			/*test.log(LogStatus.PASS, "<b>Transaction is completed and the transfer Id is generated<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Transaction created")));*/
			
			 Re.addStepLog("Transaction is completed and the transfer Id is generated <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} 
		else
		{
			/*test.log(LogStatus.FAIL, "<b>Transaction is not completed<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Fund Transfer failed")));*/
			
			 Re.addStepLog("Transaction is not completed. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction failed"));
		}
		driver.close();
		Keyword.pageHandleOnlySelectedPage("T24 - Model Bank", driver);
		Keyword.handleFrame(0, driver);
		Keyword.clickElement("signOffButton", driver);
	  
}

public static void RsixCheckingentries(String testcase_id) throws Throwable
{
	Home_Page.Homepage("ENQ AA.FIND.ARRANGEMENT.AD");
	
	 Keyword.pageHandle(driver);
	 driver.manage().window().maximize();
		String homepagepagetitle1 = driver.getTitle();
		if (homepagepagetitle1.equalsIgnoreCase("Find Deposit Arrangements")) {
			/*test.log(LogStatus.PASS, "<b>Successfully verified the arrangement enquiry page. <b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));*/
			 Re.addStepLog("Successfully verified the arrangement enquiry page. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification"));
		} else {
			/*test.log(LogStatus.FAIL, "<b>Unable to verify the arrangement enquiry page.<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));*/
			 Re.addStepLog("Unable to verify the arrangement enquiry page. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification failed"));
		}
		
		 
		  Keyword.clearElement("arrangementTextBox", driver);
		  Keyword.sendText("arrangementTextBox", getOutputFromDB("Deposit","TC_01","Arrangement_id"), driver);
		
		
		  Keyword.clickElement("findButton", driver);
		
		  
		  Keyword.clickElement("DE_Overview", driver);
		  
			Keyword.pageHandleOnlySelectedPage("Arrangement Overview (Deposits) - Model Bank", driver);
			 driver.manage().window().maximize();
		  
		  String Date = Keyword.getTextFromElement("DE_MaturityDate", driver);
		  
		  if(Date.equalsIgnoreCase(data.get("RollOver6")))
		  {
			  
			 /* test.log(LogStatus.PASS, "<b>Successfully verified the Maturity Date. <b>"
						+ test.addScreenCapture(captureScreenShot(driver, "Maturity Date verification")));*/
			  Re.addStepLog("Successfully verified the Maturity Date. <br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Maturity Date verification"));
			}
		  else 
		  {
				/*test.log(LogStatus.FAIL, "<b>Unable to verify the Maturity Date.<b>"
						+ test.addScreenCapture(captureScreenShot(driver, "Maturity Date verification")));*/
				
				 Re.addStepLog("Unable to verify the Maturity Date. <br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Maturity Date verification failed"));
			}
		  
}


public static void DepositRshort(String Testcase_id) throws Throwable
{
	Keyword.pageHandleOnlySelectedPage("Find Deposit Arrangements", driver);
	 Keyword.clearElement("arrangementTextBox", driver);
	
	Keyword.sendText("arrangementTextBox", getOutputFromDB("Deposit",Testcase_id,"Arrangement_id"), driver);
	  Keyword.clickElement("findButton", driver);
	
	  Keyword.clickElement("DE_Overview", driver);

	  System.out.println("Overview clicked");
	  
	  Keyword.clickElement("DE_NewActivity", driver);
	  
		 Keyword.pageHandle(driver);
		 driver.manage().window().maximize();
		 
	  
	  Keyword.clickElement("DE_DoActivity", driver);
	  
		 
	  Keyword.clickElement("DE_Commitment", driver);
	  
	  Keyword.clearElement("MM_CallDays", driver);
	  Keyword.sendText("MM_CallDays", data.get("RollOverShort"), driver);
	  
	  Thread.sleep(3000);
	  
		Keyword.clickElement("validatebutton", driver);
		Keyword.clickElement("commitButton", driver);
	  
		if(Keyword.isElementVisible("acceptOverrideText",driver))
		{
		Keyword.acceptOverRideClick("acceptOverrideText", driver);
		}
		 String txnMsg = Keyword.getTextFromElement("transactionText", driver);
			String DepositMatID = txnMsg.substring(14,32);

			writeOutputinDB("DepositRollShort", Testcase_id,"Transaction_id", DepositMatID);
			Keyword.pageHandleOnlySelectedPage("T24 - Model Bank",driver);
			  Keyword.handleFrame(0, driver);
			  Keyword.clickElement("signOffButton", driver);
	
}

public static void RshortAuthorise(String testcase_id) throws Throwable
{
	//loginPage.login(username,password);
	  Home_Page.Homepage("AAA A "+getOutputFromDB("DepositRollShort", testcase_id,"Transaction_id"));
	  System.out.println("AAA A "+getOutputFromDB("DepositRollShort", testcase_id,"Transaction_id"));
	  Keyword.pageHandle(driver); 
	  driver.manage().window().maximize(); 
	
		Keyword.clickElement("DE_Authorise", driver);
		 String txnMsg = Keyword.getTextFromElement("transactionText", driver);
		if (txnMsg.contains("Txn Complete")) 
		{
			/*test.log(LogStatus.PASS, "<b>Transaction is completed and the transfer Id is generated<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Transaction created")));*/
			 Re.addStepLog("Transaction is completed and the transfer Id is generated. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} 
		else
		{
			/*test.log(LogStatus.FAIL, "<b>Transaction is not completed<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Authorisation failed")));*/
			 Re.addStepLog("Transaction is not completed <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Authorisation failed"));
		}
		driver.close();
		Keyword.pageHandleOnlySelectedPage("T24 - Model Bank", driver);
		Keyword.handleFrame(0, driver);
		Keyword.clickElement("signOffButton", driver);
	  
}

public static void RshortCheckingentries(String testcase_id) throws Throwable
{
	Home_Page.Homepage("ENQ AA.FIND.ARRANGEMENT.AD");
	
	 Keyword.pageHandle(driver);
	 driver.manage().window().maximize();
		String homepagepagetitle1 = driver.getTitle();
		if (homepagepagetitle1.equalsIgnoreCase("Find Deposit Arrangements")) {
			/*test.log(LogStatus.PASS, "<b>Successfully verified the arrangement enquiry page. <b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));*/
			 Re.addStepLog("Successfully verified the arrangement enquiry page. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification"));
		} else {
			/*test.log(LogStatus.FAIL, "<b>Unable to verify the arrangement enquiry page.<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));*/
			 Re.addStepLog("Unable to verify the arrangement enquiry page. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification failed"));
		}
		
		 
		  Keyword.clearElement("arrangementTextBox", driver);
		  Keyword.sendText("arrangementTextBox", getOutputFromDB("Deposit","TC_01","Arrangement_id"), driver);
		
		
		  Keyword.clickElement("findButton", driver);
		
		  
		  Keyword.clickElement("DE_Overview", driver);
		  
			Keyword.pageHandleOnlySelectedPage("Arrangement Overview (Deposits) - Model Bank", driver);
			 driver.manage().window().maximize();
		  
		  String Date = Keyword.getTextFromElement("DE_MaturityDate", driver);
		  
		  if(Date.equalsIgnoreCase(data.get("RollOverShort")))
		  {
			  
			 /* test.log(LogStatus.PASS, "<b>Successfully verified the Maturity Date. <b>"
						+ test.addScreenCapture(captureScreenShot(driver, "Maturity Date verification")));*/
			  Re.addStepLog("Successfully verified the Maturity Date. <br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Maturity Date verification"));
			}
		  else 
		  {
				/*test.log(LogStatus.FAIL, "<b>Unable to verify the Maturity Date.<b>"
						+ test.addScreenCapture(captureScreenShot(driver, "Maturity Date verification")));*/
				
				 Re.addStepLog("Unable to verify the Maturity Date. <br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Maturity Date verification failed"));
			}
		  
}

public static void ReversalThree(String TestcaseId) throws Throwable
{
	String custId;
	//Keyword.pageHandle(driver);
	driver.manage().window().maximize();
	/*String homepagepagetitle = driver.getTitle();
	if (homepagepagetitle.equalsIgnoreCase("Find Deposit Arrangements")) {
		test.log(LogStatus.PASS, "<b>Successfully verified the arrangement enquiry page. <b>"
				+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));
	} else {
		test.log(LogStatus.FAIL, "<b>Unable to verify the arrangement enquiry page.<b>"
				+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));
	}*/
	
	 
	  Keyword.clearElement("arrangementTextBox", driver);
	  System.out.println(getOutputFromDB("Deposit","TC_01","Arrangement_id"));
	  Keyword.sendText("arrangementTextBox", getOutputFromDB("Deposit","TC_01","Arrangement_id"), driver);
	 
	 /* if(TestcaseId=="TC_04")
	  {
		  System.out.println("Inside if loop");
		  custId=getOutputFromDB("Deposit","TC_01","Arrangement_id");
		  Keyword.sendText("arrangementTextBox", getOutputFromDB("Deposit","TC_01","Arrangement_id"), driver);
	  }*/

	  /* if(testCaseId=="TC_06")
	  {
		  custId=getOutputId("Deposit", testCaseId);
		  Keyword.sendText("arrangementTextBox", getOutputId("Deposit",testCaseId), driver);
	  }*/
	  
	  Keyword.clickElement("findButton", driver);
	  //Thread.sleep(3000);
	  
	  Keyword.clickElement("DE_Overview", driver);

	  System.out.println("Overview clicked");
	  
	  Keyword.clickElement("DE_Financial", driver);
	  System.out.println("Financial clicked");
	  Keyword.clickElement("DE_Reverse", driver);
	  System.out.println("Reverse clicked");
	  Thread.sleep(3000);
		Keyword.pageHandle(driver);
	  Keyword.clickElement("DE_Secondreverse", driver);
	  Thread.sleep(3000);
	  System.out.println("Second Reverse clicked");
}
public static void Reversalsix(String TestcaseId) throws Throwable
{
	
	
	String custId;
	//Keyword.pageHandle(driver);
	driver.manage().window().maximize();
	/*String homepagepagetitle = driver.getTitle();
	if (homepagepagetitle.equalsIgnoreCase("Find Deposit Arrangements")) {
		test.log(LogStatus.PASS, "<b>Successfully verified the arrangement enquiry page. <b>"
				+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));
	} else {
		test.log(LogStatus.FAIL, "<b>Unable to verify the arrangement enquiry page.<b>"
				+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));
	}*/
	
	 
	  Keyword.clearElement("arrangementTextBox", driver);
	  System.out.println(getOutputFromDB("Deposit",TestcaseId,"Arrangement_id"));
	  Keyword.sendText("arrangementTextBox", getOutputFromDB("Deposit",TestcaseId,"Arrangement_id"), driver);
	 
	
	  Keyword.clickElement("findButton", driver);
	  //Thread.sleep(3000);
	  
	  Keyword.clickElement("DE_Overview", driver);

	  System.out.println("Overview clicked");
	  
	  Keyword.clickElement("DE_Financial", driver);
	  System.out.println("Financial clicked");
	  Keyword.clickElement("DE_Reverse", driver);
	  System.out.println("Reverse clicked");
	  Thread.sleep(3000);
		Keyword.pageHandle(driver);
	  Keyword.clickElement("DE_Secondreverse", driver);
	  Thread.sleep(3000);
	  System.out.println("Second Reverse clicked");
	
}

public static void VerifyMaturity(LoginAndLogOut_Page loginPage,String TestcaseId) throws Throwable
{
	/*System.out.println("Inside the Verify maturity methos the test case id is "+TestcaseId);
	 Home_Page.Homepage("ENQ AA.FIND.ARRANGEMENT.AD");
		
	 Keyword.pageHandleOnlySelectedPage("Find Deposit Arrangements", driver);*/
	 
//	 Keyword.pageHandle(driver);
	 driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		System.out.println("+++++++==="+homepagepagetitle);
		if (homepagepagetitle.equalsIgnoreCase("Find Deposit Arrangements")) {
			
			 Re.addStepLog("Successfully verified the arrangement enquiry page. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification"));
		} else {
			
			
			 Re.addStepLog("Unable to verify the arrangement enquiry page. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification"));
		}
		
		 
		  Keyword.clearElement("arrangementTextBox", driver);
		  Keyword.sendText("arrangementTextBox", getOutputFromDB("Deposit", TestcaseId,"Arrangement_id"), driver );
		//Keyword.sendText("arrangementTextBox", "AA19108ZYLYV", driver);
		
		  Keyword.clickElement("findButton", driver);
		
		  
		  Keyword.clickElement("DE_Overview", driver);
	
		  System.out.println("Overview clicked");
		  
		  Keyword.clickElement("DE_NewActivity", driver);
		  
			 Keyword.pageHandle(driver);
			 driver.manage().window().maximize();
			 
		  
		  Keyword.clickElement("DE_DoActivity", driver);
		  
		/*  Keyword.pageHandle(driver);
			 driver.manage().window().maximize();*/
			 
		  Keyword.clickElement("DE_Commitment", driver);
		  
		 
			  Keyword.clearElement("MM_CallDays", driver);
		  Keyword.sendText("MM_CallDays", data.get("MaturityDate"), driver);
		  
		  Thread.sleep(3000);
		  
			Keyword.clickElement("validatebutton", driver);
			Keyword.clickElement("commitButton", driver);
		  System.out.println("aFTER COMMIT");
			if(Keyword.isElementVisible("acceptOverrideText",driver))
			{
			Keyword.acceptOverRideClick("acceptOverrideText", driver);
			}
			System.out.println("Before commit");
			 String txnMsg = Keyword.getTextFromElement("transactionText", driver);
			 System.out.println(txnMsg);
				String DepositMatID = txnMsg.substring(14,32);
			//	outputExcelOperation("DepositMaturity", TestcaseId, DepositMatID);
				
				writeOutputinDB("DepositMaturity",TestcaseId,"DepositMatid", DepositMatID);
		 
				/*getBrowser();
				Re.addStepLog("Browser Opened & Login Page Loaded<br>");
				loginPage = new LoginAndLogOut_Page(driver);
				loginPage.login(adminUsername,adminPassword);
				  loginPage.login(adminUsername,adminPassword);
				//  Home_Page.Homepage("AAA A "+getOutputFromDB("DepositMaturity", TestcaseId,"DepositMatid"));
		  
					Home_Page.Homepage("AAA A "+DepositMatID);
		  
		  Keyword.pageHandle(driver); 
		  driver.manage().window().maximize(); 
		
			Keyword.clickElement("DE_Authorise", driver);
			 String txnMsg1 = Keyword.getTextFromElement("transactionText", driver);
			if (txnMsg1.contains("Txn Complete")) 
			{
				test.log(LogStatus.PASS, "<b>Transaction is completed and the transfer Id is generated<b>"
						+ test.addScreenCapture(captureScreenShot(driver, "Transaction created")));
				 Re.addStepLog("Transaction is completed and the transfer Id is generated<br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction is completed and the transfer Id is generated"));
				
			} 
			else
			{
				test.log(LogStatus.FAIL, "<b>Transaction is not completed<b>"
						+ test.addScreenCapture(captureScreenShot(driver, "Fund Transfer failed")));
				 Re.addStepLog("Transaction is not completed<br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Fund Transfer failed"));
			}
			driver.close();
			Keyword.pageHandleMainPage(driver);
			Keyword.handleFrame(0, driver);
			Keyword.clickElement("signOffButton", driver);
		//	Thread.sleep(30000);
			loginPage.login(adminUsername,adminPassword);
			Home_Page.Homepage("ENQ AA.FIND.ARRANGEMENT.AD");
			
			 Keyword.pageHandle(driver);
			 driver.manage().window().maximize();
				String homepagepagetitle1 = driver.getTitle();
				if (homepagepagetitle1.equalsIgnoreCase("Find Deposit Arrangements")) {
					test.log(LogStatus.PASS, "<b>Successfully verified the arrangement enquiry page. <b>"
							+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));
					 Re.addStepLog("Successfully verified the arrangement enquiry page.<br>");
					 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification"));
				} else {
					test.log(LogStatus.FAIL, "<b>Unable to verify the arrangement enquiry page.<b>"
							+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));
					 Re.addStepLog("Unable to verify the arrangement enquiry page.<br>");
					 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification"));
				}
				
				 
				  Keyword.clearElement("arrangementTextBox", driver);
				  Keyword.sendText("arrangementTextBox", getOutputFromDB("Deposit", TestcaseId,"Transaction_id"), driver);
				
				
				  Keyword.clickElement("findButton", driver);
				
				  
				  Keyword.clickElement("DE_Overview", driver);
				  
					Keyword.pageHandleOnlySelectedPage("Arrangement Overview (Deposits) - Model Bank", driver);
					 driver.manage().window().maximize();
				  
				  String Date = Keyword.getTextFromElement("DE_MaturityDate", driver);
				  
				  
				  
				  if(Date.equalsIgnoreCase(data.get("MaturityDate")))
				  {
					  
					  test.log(LogStatus.PASS, "<b>Successfully verified the Maturity Date. <b>"
								+ test.addScreenCapture(captureScreenShot(driver, "Maturity Date verification")));
					  Re.addStepLog("Successfully verified the Maturity Date.<br>");
						 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Maturity Date verification"));
					}
				  */
}


}
