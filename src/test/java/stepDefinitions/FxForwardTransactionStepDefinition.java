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

public class FxForwardTransactionStepDefinition extends BaseClass {
	String className="";
	LoginAndLogOut_Page loginPage;
	String transaction_id ;
	
	@Given("^Input data for FxForward \"([^\"]*)\"$")
	public void input_data_for_FxForward(String TestcaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		className = this.getClass().getName();
		createDirectory(className);	
		BaseClass.ReadOR("Common_OR");
		data =BaseClass.ReadData( "ForexForward",TestcaseId);
	    
	}

	@When("^title of login page is FOREX$")
	public void title_of_login_page_is_FOREX() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		
		if(homepagepagetitle.equalsIgnoreCase("FOREX"))
		{
			 Re.addStepLog( "<b>Successfully verified the Forex Forward page. <b>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Forex Forward verification"));
		} 
	else {
		Re.addStepLog( "<b>Unable to verify the Forex Forward page.<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Forex Forward page verification"));
		}
	    
	}

	@Then("^user inputs the value in the forex fields \"([^\"]*)\"$")
	public void user_inputs_the_value_in_the_forex_fields(String testCaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
Keyword.sendText("FX_Counterparty",data.get("CounterParty"), driver);
		
		Keyword.sendText("FX_Dealdate",data.get("DealDate"), driver);
		Keyword.sendText("FX_ValueDate",data.get("ValueDate"), driver);
		Keyword.sendText("FX_CurrencyBought",data.get("CurrencyBought"), driver);
		Keyword.sendText("FX_CurrencySold",data.get("CurrencySold"), driver);
		Keyword.sendText("FX_AmountBought",data.get("AmountBought"), driver);
		
		Keyword.sendText("FX_ForwardDate",data.get("ForwardRate"), driver);
		Keyword.clickElement("validatebutton",driver);
		Keyword.clickElement("commitButton",driver);
		
		if(Keyword.isElementVisible("acceptOverrideText",driver))
		{
		Keyword.acceptOverRideClick("acceptOverrideText", driver);
		}
		
		Re.addStepLog( "<b>Committed the record using the option highlighted.<b>");
		

		String txnMsg = Keyword.getTextFromElement("transactionText", driver);
		transaction_id = txnMsg.substring(14,26);
		
		writeOutputinDB("ForexForward", testCaseId, "Transaction_id", transaction_id);
		if (txnMsg.contains("Txn Complete")) {
			Re.addStepLog( "<b>Transaction is completed and the Customer Id is generated<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} else {
			Re.addStepLog(  "<b>Transaction is not completed<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
		}
		
	   
	}
	@Then("^user authorises the deal for FxForward$")
	public void user_authorizes_deal() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Home_Page.Homepage("FOREX A " + transaction_id);
		Keyword.pageHandle(driver);
		
		driver.manage().window().maximize();
		Re.addStepLog( "<b>authorize the record.<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "authorize record"));
		Keyword.clickElement("authoriseButton", driver);
		
		String txnMsgAuth = Keyword.getTextFromElement("transactionText", driver);
		if (txnMsgAuth.contains("Txn Complete")) {
			Re.addStepLog( "<b>Transaction is authorized and the Customer Id is generated<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} else {
			Re.addStepLog("<b>Transaction is not completed<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
		}
		
	 
	}

	@Then("^user verifies accouting entries for FxForward \"([^\"]*)\"$")
	public void user_verifies_accouting_entries_for_FxForward(String testcaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Re.addStepLog("Forex data Test");
		Keyword.pageHandle(driver);	
		String transaction_id=getOutputFromDB("ForexForward", testcaseId,"Transaction_id");
		Forex_Page.AccountEntries(transaction_id);
	}

}
