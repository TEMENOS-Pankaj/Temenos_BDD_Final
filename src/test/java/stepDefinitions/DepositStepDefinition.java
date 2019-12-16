package stepDefinitions;

import org.apache.log4j.Logger;

import DepositModule_Page.Deposit_Page;
import LoginandHome_Page.Home_Page;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class DepositStepDefinition extends BaseClass {

	//static Logger log = Logger.getLogger(DepositStepDefinition.class);
	String className="";
	String custId;
	String activityId;
	String activity;
	
	@Given("^Input data for Deposit \"([^\"]*)\"$")
	public void prerequisite_for_deposit(String TestcaseId) throws Throwable {
	className = this.getClass().getName();
	createDirectory(className);	
	BaseClass.ReadOR("Common_OR");
	data =BaseClass.ReadData( "Deposit",TestcaseId);	
	}

@Then("^user navigates to Deposit page$")
public void user_navigates_to_Deposit_page() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	Deposit_Page.goToDepositPage();
}

@When("^title of page is AA ARRANGEMENT ACTIVITY$")
public void title_of_page_is_AA_ARRANGEMENT_ACTIVITY() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	Deposit_Page.verifyDepositPage();
}

@Then("^user inputs Deposit values in fields \"([^\"]*)\"$")
public void user_inputs_Deposit_values_in_fields(String testCaseId) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	Deposit_Page.createDeposit(testCaseId);
	String txnMsg = Keyword.getTextFromElement("transactionMsg", driver);
	activityId = txnMsg.substring(14, 32);
	System.out.println(activityId);
	if (txnMsg.contains("Txn Complete")) {
		Re.addStepLog( "<b>Transaction is completed and deposit is created.<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Deposit created successfully"));
	} else {
		Re.addStepLog( "<b>Transaction is not completed<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Deposit creation failed."));
	}
	
}

@Then("^user authorises the deposit created \"([^\"]*)\"$")
public void user_authorises_the_deposit_created(String testCaseId) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	 activity= Deposit_Page.authoriseDeposit(testCaseId, activityId);
}

@Then("^user validates the deposit \"([^\"]*)\"$")
public void user_validates_the_deposit(String testCaseId) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	Deposit_Page.validateDeposit( activityId,testCaseId);
		 
}

@Then("^user verifies transaction entries \"([^\"]*)\"$")
public void user_verifies_transaction_entries(String testCaseId) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	 Re.addStepLog( "<b>Entered the command in the text box and clicked on Go button.<b>"); 
	  Re.addScreenCaptureFromPath(captureScreenShot(driver, "Command screen"));
	  
	  Keyword.pageHandle(driver); driver.manage().window().maximize();
	  Keyword.clearElement("LD_TransactionRefTextBox", driver);
	  Keyword.sendText("LD_TransactionRefTextBox", activity, driver);
	  Re.addStepLog( "<b>Entered the Transaction RefID:<b>"+activityId);
	  Re.addScreenCaptureFromPath(captureScreenShot(driver, "authorize record"));
	  
	  Keyword.clickElement("LD_TransactionRefFindButton", driver);
	  Re.addStepLog( "<b>Clicked on the Find button.<b>");
	  
	  Keyword.pageHandleOnlySelectedPage("List Of Entries", driver);
	  String amount=data.get("Amount");
	 custId=getOutputFromDB("CU_Individual_Customer_Creation", "TC_01","CustomerId");
	if( Deposit_Page.transactionEntryVerification(custId, amount))
	  {
		 Re.addStepLog( "<b>Entries verified successfully.<b>" );
		 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		  }
	  else {
		  Re.addStepLog( "<b>Entries not verified.<b>");
		  Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
	  
	  }
}

}
