package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;

import LoginandHome_Page.LoginAndLogOut_Page;
import TellerModule_Page.Teller_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class TellerCreationStepDefinition extends BaseClass {
	String className="";
	LoginAndLogOut_Page loginPage;
	String Transaction_id;
	@Given("^Input data for Teller Creation \"([^\"]*)\"$")
	public void input_data_for_Teller_Creation(String TestcaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		className = this.getClass().getName();
		createDirectory(className);	
		BaseClass.ReadOR("Common_OR");
		data =BaseClass.ReadData( "TellerCreation",TestcaseId);
	    
	}

	@When("^title of login page is TELLER ID$")
	public void title_of_login_page_is_TELLER_ID() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		
		if(homepagepagetitle.equalsIgnoreCase("TELLER ID"))
		{
			Re.addStepLog( "<b>Successfully verified the Teller page. <b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Teller page verification"));
		} 
	else {
		Re.addStepLog( "<b>Unable to verify the Teller page.<b>");
		 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Teller page verification"));
		}
	
	}

	@Then("^user inputs the value in the Teller fields \"([^\"]*)\"$")
	public void user_inputs_the_value_in_the_Teller_fields(String testCaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Teller_Page.Tellercreation();
		Keyword.clickElement("TT_Commit",driver);
		//Thread.sleep(30000);
		Re.addStepLog( "<b>Committed the record using the option highlighted.<b>");
		
		String txnMsg = Keyword.getTextFromElement("transactionText", driver);
		Transaction_id = txnMsg.substring(14,19);
		
		writeOutputinDB("TellerCreation", testCaseId, "Transaction id", Transaction_id);
		if (txnMsg.contains("Txn Complete")||txnMsg.contains("LIVE RECORD NOT CHANGED")) {
			Re.addStepLog( "<b>Transaction is completed<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} else {
			Re.addStepLog( "<b>Transaction is not completed<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
		}
	    
}
}
