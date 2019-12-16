package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;

import ForexModule_Page.Forex_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class ReverseForwardTransactionStepDefinition extends BaseClass {
	String className="";
	LoginAndLogOut_Page loginPage;
	String transaction_id ;
	
	@Given("^Input data for ReverseForward \"([^\"]*)\"$")
	public void input_data_for_ReverseForward(String TestcaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		className = this.getClass().getName();
		createDirectory(className);	
		BaseClass.ReadOR("Common_OR");
		data =BaseClass.ReadData( "ForexForward",TestcaseId);
	}

	
	@When("^title of login page is Reverse Forex Deals$")
	public void title_of_login_page_is_Reverse_Forex_Deals() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		
		if(homepagepagetitle.equalsIgnoreCase("Reverse Reverse Forward Deals"))
		{
			Re.addStepLog("<b>Successfully verified the Reverse Forward page. <b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Reverse Forex"));
		} 
	else {
		Re.addStepLog("<b>Unable to verify the Reverse Forward page.<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Reverse Forex"));
		}
	    
	}

	@Then("^user inputs the value in the reverse forex fields \"([^\"]*)\"$")
	public void user_inputs_the_value_in_the_reverse_forex_fields(String testCaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Forex_Page.RevForwardEntries(testCaseId);
		Keyword.clickElement("FX_Reverse",driver);
		
		Keyword.clickElement("FX_SecondReverse",driver);
		transaction_id=getOutputFromDB("ForexForward", testCaseId, "Transaction_id");
	   
	}

	@Then("^user verifies accouting entries for FxReverse$")
	public void user_verifies_accouting_entries_for_FxReverse() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Re.addStepLog("Forex data Test");
		Keyword.pageHandle(driver);		
		Forex_Page.AccountEntries(transaction_id);
	    
	}


}
