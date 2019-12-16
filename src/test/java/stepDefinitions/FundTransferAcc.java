package stepDefinitions;

import org.apache.log4j.Logger;

import FundTransferModule_Page.FundTransfer_Page;
import LoginandHome_Page.Home_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class FundTransferAcc extends BaseClass{
	
	static Logger log = Logger.getLogger(IndCustomerStepDefinition.class);
	String className="";
	LoginAndLogOut_Page loginPage;
	String FundTransferID;
	
	@Given("^pre req for FundTransfer \"([^\"]*)\"$")
public void pre_req_for_FundTransfer(String TestCaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		className = this.getClass().getName();
		createDirectory(className);	
	//	getBrowser();
		
	
		BaseClass.ReadOR("FundTransfer_OR");
		
		data =ReadData("Fund_Transfer_AccToAcc",TestCaseId);
		
		/*loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
		Re.addStepLog("Browser Opened & Login Page Loaded<br>");*/
	   
	}

	@Then("^user input the command in the fund transfer text box \"([^\"]*)\"$")
	public void user_input_the_command_in_the_fund_transfer_text_box(String Command) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Home_Page.Homepage(Command);
	   
	}

	@When("^title of login page is FUNDS\\.TRANSFER$")
	public void title_of_login_page_is_FUNDS_TRANSFER() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		if (homepagepagetitle.equalsIgnoreCase("FUNDS.TRANSFER")) {
			 Re.addStepLog("Successfully verified the Fund transfer page. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Fund transfer page verification"));
		} else {
			 Re.addStepLog("Unable to verify the Fund transfer page.<br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Fund transfer page verification"));
		}
	   
	}

	@Then("^user input FUND TRANSFER detail \"([^\"]*)\"$")
public void user_input_FUND_TRANSFER_detail(String TestCaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		FundTransfer_Page.transferBetweenAccounts( getOutputFromDB("Deposit",TestCaseId,"Arrangement_id"));
	  
		String txnMsg = Keyword.getTextFromElement("transactionText", driver);
		FundTransferID = txnMsg.substring(14,26);
		writeOutputinDB("FundTransfer", TestCaseId, "FundTransferID", FundTransferID);
	//	outputExcelOperation("FundTransfer", TestCaseId, FundTransferID);
		
		if (txnMsg.contains("Txn Complete")) 
		{
		/*	test.log(LogStatus.PASS, "<b>Transaction is completed and the transfer Id is generated<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Transaction created")));*/
			 Re.addStepLog("Transaction is completed and the transfer Id is generated <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} 
		else
		{
			/*test.log(LogStatus.FAIL, "<b>Transaction is not completed<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Fund Transfer failed")));*/
			 Re.addStepLog("Transaction is not completed <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Fund transfer page verification"));
		}
		
	}

	@Then("^user authorises Fund Transfer$")
	public void user_authorises_Fund_Transfer() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		
		/*driver.close();
		Keyword.pageHandleMainPage(driver);
		Keyword.handleFrame(0, driver);
		Keyword.clickElement("signOffButton", driver);*/
		
		getBrowser();

		loginPage = new LoginAndLogOut_Page(driver);
		
		loginPage.login(adminUsername,adminPassword);
		Home_Page.Homepage("FUNDS.TRANSFER,ACTR.NOST A " + FundTransferID);
		Keyword.pageHandleOnlySelectedPage("FUNDS.TRANSFER", driver);
		
		
		driver.manage().window().maximize();
		/*test.log(LogStatus.PASS,
			"<b>authorize the record.<b>" + test.addScreenCapture(captureScreenShot(driver, "authorize record")));*/
		Keyword.clickElement("authoriseButton", driver);
		
		String txnMsgAuth = Keyword.getTextFromElement("transactionText", driver);
		if (txnMsgAuth.contains("Txn Complete")) 
		{
			/*test.log(LogStatus.PASS, "<b>Transaction is authorized and the Transaction  Id is generated<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Transaction created")));*/
			 Re.addStepLog("Transaction is authorized and the Transaction  Id is generated<br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} 
		else
		{
			/*test.log(LogStatus.FAIL, "<b>Transaction is not completed<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Transaction creation failed")));*/
			 Re.addStepLog("Transaction is not completed <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		}
		
	   
	}

	

}
