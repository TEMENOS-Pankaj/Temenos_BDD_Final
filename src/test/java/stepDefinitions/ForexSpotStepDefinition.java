package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;

import ForexModule_Page.Forex_Page;
import LoginandHome_Page.Home_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class ForexSpotStepDefinition extends BaseClass{

	LoginAndLogOut_Page loginPage;
	String Transaction_id;
	
	
	@Given("^pre_req_for_forexspot_application \"([^\"]*)\"$")
	public void pre_req_for_forexspot_application(String Testcase_id) throws Throwable {
		className = this.getClass().getName();
		createDirectory(className);	
		//getBrowser();

		BaseClass.ReadOR("Common_OR");
		data =ReadData("ForexSpot",Testcase_id);
		
		/*loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
		Re.addStepLog("Browser Opened & Login Page Loaded<br>");*/
		
	}
	
	/*@Then("^user input the command in the text box \"([^\"]*)\"$")
	public void user_input_the_command_in_the_text_box(String command) throws Throwable {
	    
		Home_Page.Homepage(command);
		 Keyword.pageHandle(driver);
	}*/
	
	
	@When("^title of login page is Forex$")
	public void title_of_login_page_is_Forex() throws Throwable {
	    
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		if (homepagepagetitle.equalsIgnoreCase("FOREX")) {
			 Re.addStepLog("Successfully verified the customer page. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Find Deposit Arrangements page verification"));
		} else {
			 Re.addStepLog("Unable to verify the customer page.<br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Find Deposit Arrangements page verification"));
		}
		
	}
	
	@Then("^user inputs the Spot value in the req field\"([^\"]*)\"$")
	public void user_inputs_the_Spot_value_in_the_req_field(String testcase_id) throws Throwable {
			Forex_Page.ForexSpot();
		
		
		Keyword.clickElement("validatebutton",driver);
		Keyword.clickElement("commitButton",driver);
		
		if(Keyword.isElementVisible("acceptOverrideText",driver))
		{
		Keyword.acceptOverRideClick("acceptOverrideText", driver);
		}
		
	//	test.log(LogStatus.PASS, "<b>Committed the record using the option highlighted.<b>");
		
		String txnMsg = Keyword.getTextFromElement("transactionText", driver);
		Transaction_id = txnMsg.substring(14,26);
		
		//outputExcelOperation("Forex_Spot",testcase_id,Transaction_id);
		writeOutputinDB("Forex_Spot", testcase_id,"Transaction_id", Transaction_id);
		if (txnMsg.contains("Txn Complete")) {
			//test.log(LogStatus.PASS, "<b>Transaction is completed and the Forex Spot is generated<b>");
					//+ test.addScreenCapture(captureScreenShot(driver, "Transaction created")));
			 Re.addStepLog("Transaction is completed and the Forex Spot is generated <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction is completed and the Forex Spot is generated"));
		} else {
			/*test.log(LogStatus.FAIL, "<b>Transaction is not completed<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Transaction creation failed")));*/
			 Re.addStepLog("Transaction is not completed <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
		}
		
		Keyword.pageHandleOnlySelectedPage("T24 - Model Bank", driver);
		
		Keyword.handleFrame(0, driver);
		Keyword.clickElement("signOffButton", driver);
		
		
	}
	
	@Then("^user authorisez the Spot deal\"([^\"]*)\"$")
	public void user_authorisez_the_Spot_deal(String testcaseid) throws Throwable {
		loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(username,password);
		Home_Page.Homepage("FOREX A " + getOutputFromDB("Forex_Spot", testcaseid,"Transaction_id"));
		Keyword.pageHandle(driver);
		
		driver.manage().window().maximize();
		/*test.log(LogStatus.PASS,
			"<b>authorize the record.<b>" + test.addScreenCapture(captureScreenShot(driver, "authorize record")));*/
		
		Re.addStepLog("authorize the record. <br>");
		 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Record Authorised"));
		 
		Keyword.clickElement("authoriseButton", driver);
		
		String txnMsgAuth = Keyword.getTextFromElement("transactionText", driver);
		if (txnMsgAuth.contains("Txn Complete")) {
			/*test.log(LogStatus.PASS, "<b>Authorisation completed <b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Transaction created")));*/
			Re.addStepLog("authorize the record. <br>");
			 Re.addStepLog("Authorisation completed <br>");
		} else {
/*			test.log(LogStatus.FAIL, "<b>Transaction is not completed<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Transaction creation failed")));*/
			Re.addStepLog("Transaction is not completed <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
		}
	
		Keyword.pageHandleOnlySelectedPage("T24 - Model Bank", driver);
		Keyword.handleFrame(0, driver);
		Keyword.clickElement("signOffButton", driver);
	}
	
	@Then("^user check Spot accounting entries\"([^\"]*)\"$")
	public void user_check_Spot_accounting_entries(String testcaseid) throws Throwable {
		//log.info("Forex data Test");
		loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
		Home_Page.Homepage("ENQ TXN.ENTRY.MB");
		Keyword.pageHandle(driver);
		
		Forex_Page.AccountEntries(testcaseid);
	}
	
}
