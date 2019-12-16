package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;

import LoginandHome_Page.Home_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import TellerModule_Page.Teller_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class DepositForeignCurrencyStepDefinition extends BaseClass{
	String className="";
	LoginAndLogOut_Page loginPage;
	String Transaction_id;

@Given("^Input data for Deposit Foreign Currency \"([^\"]*)\"$")
public void input_data_for_Deposit_Foreign_Currency(String TestcaseId) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	className = this.getClass().getName();
	createDirectory(className);	
	BaseClass.ReadOR("Common_OR");
	data =BaseClass.ReadData( "DepositForeignCcy",TestcaseId);	
   
}

@When("^title of login page is TELLER$")
public void title_of_login_page_is_TELLER() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	Keyword.pageHandle(driver);
	driver.manage().window().maximize();
	String homepagepagetitle = driver.getTitle();
	
	if(homepagepagetitle.equalsIgnoreCase("TELLER"))
	{
		Re.addStepLog("<b>Successfully verified the Teller page. <b>");
		Re.addScreenCaptureFromPath((captureScreenShot(driver, "Teller page verification")));
	} 
else {
	Re.addStepLog( "<b>Unable to verify the Teller page.<b>");
	Re.addScreenCaptureFromPath(captureScreenShot(driver, "Teller page verification"));
	}
	
    
}

@Then("^user inputs the value in the Deposit Foreign Currency fields \"([^\"]*)\"$")
public void user_inputs_the_value_in_the_Deposit_Foreign_Currency_fields(String testCaseId) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	Teller_Page.DepositForeignCurrency();
	Keyword.clickElement("validatebutton",driver);
	Keyword.clickElement("commitButton",driver);
	Thread.sleep(3000);
	Re.addStepLog( "<b>Committed the record using the option highlighted.<b>");
	
	String txnMsg = Keyword.getTextFromElement("transactionText", driver);
	Transaction_id = txnMsg.substring(14,26);
	
	writeOutputinDB("DepositForeignCcy", testCaseId, "Transaction id", Transaction_id);
	if (txnMsg.contains("Txn Complete")) {
		Re.addStepLog("<b>Transaction is completed and the Customer Id is generated<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
	} else {
		Re.addStepLog("<b>Transaction is not completed<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
	}
   
}

@Then("^user authorises the deposit created$")
public void user_authorises_the_deposit_created() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	Home_Page.Homepage("TELLER A " + Transaction_id);
	Keyword.pageHandle(driver);
	
	driver.manage().window().maximize();
	Re.addStepLog("<b>authorize the record.<b>");
	Re.addScreenCaptureFromPath(captureScreenShot(driver, "authorize record"));
	Keyword.clickElement("authoriseButton", driver);
	
	String txnMsgAuth = Keyword.getTextFromElement("transactionText", driver);
	if (txnMsgAuth.contains("Txn Complete")) {
		Re.addStepLog( "<b>Transaction is authorized and the Customer Id is generated<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
	} else {
		Re.addStepLog( "<b>Transaction is not completed<b>");
				Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
	}

    
}

@Then("^user verifies accouting entries for Deposit Foreign Currency$")
public void user_verifies_accouting_entries_for_Deposit_Foreign_Currency() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	Keyword.pageHandle(driver);
	
	Teller_Page.AccountingEntry(Transaction_id);
   
}


}
