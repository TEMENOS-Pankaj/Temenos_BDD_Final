package stepDefinitions;


import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class Verificationof9MonthsDepositStepDefinition extends BaseClass {
	String className="";
	LoginAndLogOut_Page loginPage;

	@Given("^Input data for Verification of Nine Months Deposit \"([^\"]*)\"$")
	public void input_data_for_Verification_of_Nine_Months_Deposit(String TestcaseId) throws Throwable {
		className = this.getClass().getName();
		createDirectory(className);	
		BaseClass.ReadOR("Common_OR");
		data =BaseClass.ReadData( "Deposit",TestcaseId);
	    
	}

	@Then("^user navigates to the Arrangement Overview Page \"([^\"]*)\"$")
	public void user_navigates_to_the_Arrangement_Overview_Page(String testCaseId) throws Throwable {
		Keyword.handleFrame(1, driver);
		Keyword.clickElement("userMenu", driver);
		Keyword.clickElement("DEP_retailOperationsMenu", driver);
		Keyword.clickElement("DEP_findDepositLink", driver);
		Keyword.pageHandle(driver);
		String arrangementId=getOutputFromDB("Deposit", testCaseId, "Arrangement_id");
		Keyword.clearElement("DEP_arrangementTextBox", driver);
		Keyword.sendText("DEP_arrangementTextBox", arrangementId, driver);
		Keyword.clickElement("DEP_findButton", driver);
	  
	}

	@When("^title of login page is Arrangement Overview Deposits Model Bank$")
	public void title_of_login_page_is_Arrangement_Overview_Deposits_Model_Bank() throws Throwable {
		driver.manage().window().maximize();
		String pagetitle = driver.getTitle();
		
		if(pagetitle.equalsIgnoreCase("AA Arrangement - Model Bank"))
		{
			Re.addStepLog("<b>Successfully verified the Arrangement Overview page. <b>");
			Re.addScreenCaptureFromPath((captureScreenShot(driver, "Teller page verification")));
		} 
	else {
		Re.addStepLog( "<b>Unable to verify the Arrangement Overview page.<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Teller page verification"));
		}
	    
	}

	@Then("^user verifies the status of the deposit \"([^\"]*)\"$")
	public void user_verifies_the_status_of_the_deposit(String arg1) throws Throwable {
		String status= Keyword.getTextFromElement("DEP_status", driver);
		if(status.equalsIgnoreCase("Current")) {
			Re.addStepLog( "<b>Status verified successfully.Staus is :<b>" +status);
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Satus verified."));
			
		}else {
			Re.addStepLog(  "<b>Status not verified.Staus is:<b>" +status);
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Status not verified."));
		}
	   
	}


}
