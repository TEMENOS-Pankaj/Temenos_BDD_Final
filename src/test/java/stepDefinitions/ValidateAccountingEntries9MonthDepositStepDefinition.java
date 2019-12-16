package stepDefinitions;


import DepositModule_Page.Deposit_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utility.BaseClass;
import utility.Keyword;

public class ValidateAccountingEntries9MonthDepositStepDefinition extends BaseClass{
	String className="";
	LoginAndLogOut_Page loginPage;

@Given("^Input data for Validation of Accounting Entries for Nine Months Deposit \"([^\"]*)\"$")
public void input_data_for_Validation_of_Accounting_Entries_for_Nine_Months_Deposit(String TestcaseId) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	className = this.getClass().getName();
	createDirectory(className);	
	BaseClass.ReadOR("Common_OR");
	data =BaseClass.ReadData( "Deposit","TC_07");
   
}

@Then("^user verifies accounting entries for Nine Months Deposit \"([^\"]*)\"$")
public void user_verifies_accounting_entries_for_Nine_Months_Deposit(String testCaseId) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	Keyword.pageHandle(driver);
	Keyword.clearElement("DEP_transactionRefBox", driver);
	String arrangementId=getOutputFromDB("Deposit", testCaseId, "Arrangement_id");
	Keyword.sendText("DEP_transactionRefBox", arrangementId, driver);
	Keyword.clickElement("FTOR_findButton", driver);
	driver.manage().window().maximize();
	Re.addStepLog(  "<b>Account Entries.<b>" );
	Re.addScreenCaptureFromPath(captureScreenShot(driver, "Amount verified."));
	//String custID=getOutputFromDB("CU_Individual_Customer_Creation", "TC_01", "CustomerId");
	String custID=getOutputFromDB("CU_Individual_Customer_Creation", "TC_01", "CustomerId");
	String lcyAmount="-"+data.get("Amount");
	if(Deposit_Page.transactionEntryVerification(custID, lcyAmount)) {
		Re.addStepLog( "<b>Amount verified successfully.Amount Expected:<b>" +lcyAmount);
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Amount verified."));
	}else
	{
		Re.addStepLog("<b>Amount not verified.Amount Expected:<b>" +lcyAmount);
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Amount not verified."));
	}
    
}


}
