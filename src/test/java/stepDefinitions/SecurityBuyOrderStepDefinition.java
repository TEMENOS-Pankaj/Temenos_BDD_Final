package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;

import LoginandHome_Page.Home_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import SecurityModule_Page.Security_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utility.BaseClass;
import utility.Keyword;

public class SecurityBuyOrderStepDefinition extends BaseClass {
	String className="";
	LoginAndLogOut_Page loginPage;
	String custId,accNo,name,orderBuyID,OrderExecutionID,tradeReference,tradeCompletionID;
	
	@Given("^Input data for Security Buy Order \"([^\"]*)\"$")
	public void input_data_for_Security_Buy_Order(String TestcaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		className = this.getClass().getName();
		createDirectory(className);	
		BaseClass.ReadOR("Common_OR");
		data =BaseClass.ReadData( "Security_BuyOrder",TestcaseId);
	}
	
	@Then("^user onboards the customer \"([^\"]*)\"$")
	public void user_onboards_the_customer(String testCaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		custId =getOutputFromDB("CU_Individual_Customer_Creation", testCaseId, "CustomerId") ;
		accNo = getOutputFromDB("AccountSBCreation", testCaseId, "AccountId");
		name = getOutputFromDB("CU_Individual_Customer_Creation", testCaseId, "Customer_Name");

		Home_Page.Homepage("CUSTOMER.SECURITY I " + custId);
		Security_Page.onboardCustomer();
		String txnMsg = Keyword.getTextFromElement("transactionText", driver);
		if (txnMsg.contains("Txn Complete")) {
			Re.addStepLog("<b>Customer is onboarded.<b>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} else {
			Re.addStepLog("<b>Transaction is not completed<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
		}
	   
	    
	}


	@Then("^user authorizes the onboarded customer$")
	public void user_authorizes_the_onboarded_customer() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Home_Page.Homepage("CUSTOMER.SECURITY A " + custId);
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		Keyword.clickElement("authoriseButton", driver);
	}

	@Then("^user creates Portfolio for the customer$")
	public void user_creates_Portfolio_for_the_customer() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Home_Page.Homepage("SEC.ACC.MASTER,INP.CUS I " + custId +"-2");
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		Security_Page.createPortfolio(accNo, name);
		String txnMsg2 = Keyword.getTextFromElement("transactionText", driver);
		if (txnMsg2.contains("Txn Complete")) {
			Re.addStepLog( "<b>Portfolio is created.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} else {
			Re.addStepLog("<b>Error while creating Portfolio<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
		}
	    
	}

	@Then("^user creates Buy Order$")
	public void user_creates_Buy_Order() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Security_Page.securitiesBuyOrder(custId);

		String txnMsg3 = Keyword.getTextFromElement("transactionText", driver);
		orderBuyID = txnMsg3.substring(14, 30);

		if (txnMsg3.contains("Txn Complete")) {
			Re.addStepLog(  "<b>Transaction is completed and Security BUY order is created<b>");
					Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} else {
			Re.addStepLog(  "<b>Transaction is not completed<b>");
					Re.addScreenCaptureFromPath(captureScreenShot(driver, "Fund Transfer failed"));
		}

		driver.close();
	    
	}

	@Then("^user executes the order$")
	public void user_executes_the_order() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Home_Page.Homepage("SC.EXE.SEC.ORDERS,EXECUTE I  " + orderBuyID);
		Keyword.pageHandleOnlySelectedPage("EXECUTE OPEN ORDERS", driver);
		driver.manage().window().maximize();

		if (Keyword.isElementVisible("SQ_SecurityNumVerify", driver)) {
			Re.addStepLog( "<b>Order to execute.<b>" );
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "authorize record"));

			Keyword.sendText("SQ_TradeDate", data.get("Value Date"), driver);
			Keyword.sendText("SQ_ValueDate", data.get("Value Date"), driver);
			
			Security_Page.brokerDetails();
			Keyword.clickElement("SQ_CommitButton", driver);
			Keyword.acceptOverRideClick("SQ_acceptOverridesOrderExecution", driver);

		} else {
			Re.addStepLog(  "<b>Unable to find the order to execute.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "authorize record"));
		}

		String txnMsgAuth = Keyword.getTextFromElement("transactionText", driver);
		OrderExecutionID = txnMsgAuth.substring(14, 30);
		if (txnMsgAuth.contains("Txn Complete")) {
			Re.addStepLog("<b>Transaction is completed and the Transaction Id is generated<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} else {
			Re.addStepLog( "<b>Transaction is not completed<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
		}
	    
	}
	
	@Then("^user authorizes the order \"([^\"]*)\"$")
	public void user_authorizes_the_order(String testCaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Home_Page.Homepage("SEC.OPEN.ORDER,BUY.STP S " + OrderExecutionID);
		Keyword.pageHandleOnlySelectedPage("SECURITY.OPEN.ORDER", driver);
		driver.manage().window().maximize();
		Re.addStepLog( "<b>Completion of Security Trade- View the executed order to get the trade id<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		driver.manage().window().maximize();

		Keyword.clickElement("SQ_ExecutionTab", driver);
		Re.addStepLog("<b>Clicked on the 'Execution Details' tab.<b>");
		tradeReference = Keyword.getTextFromElement("SQ_TradeReference1", driver);
		//outputExcelOperation("Securities", testCaseId, tradeReference);
		//writeAccountNoOperation("Securities",testCaseId,tradeReference);
		writeOutputinDB("Security_BuyOrder", testCaseId, "transactionId", tradeReference);
		
	  
	}



	@Then("^user completes the trade$")
	public void user_completes_the_trade() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Home_Page.Homepage("SEC.TRADE,COMPLETE I " + tradeReference);
		Keyword.pageHandleOnlySelectedPage("SEC.TRADE", driver);
		driver.manage().window().maximize();

		if (Keyword.isElementVisible("SQ_TradeCompletionAuthorisation", driver)) {
			Re.addStepLog("<b>Trade Completion and Authorisation<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Trade Completion and Authorisation"));
		} else {
			Re.addStepLog( "<b>Unable to get Trade Completion and Authorisation<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Trade Completion and Authorisation"));
		}

		Keyword.clickElement("SQ_CommitButton", driver);
		Re.addStepLog( "<b>Clicked on the Commit Button.<b>");

		Keyword.acceptOverRideClick("SQ_acceptOverridesOrderExecution", driver);

		String tradeCompletion = Keyword.getTextFromElement("transactionText", driver);
		tradeCompletionID = tradeCompletion.substring(14, 30);
		if (tradeCompletion.contains("Txn Complete")) {
			Re.addStepLog( "<b>The trade is completed.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "The trade is completed"));
		} else {
			Re.addStepLog( "<b>The trade does not completed.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "The trade is completed"));
		}
	   
	}


}
