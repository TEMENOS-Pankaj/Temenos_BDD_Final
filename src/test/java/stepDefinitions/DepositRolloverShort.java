package stepDefinitions;

import org.apache.log4j.Logger;

import DepositModule_Page.Deposit_Page;
import LoginandHome_Page.Home_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class DepositRolloverShort extends BaseClass{

	static Logger log = Logger.getLogger(IndCustomerStepDefinition.class);
	String className="";
	LoginAndLogOut_Page loginPage;
	
	@Given("^pre req for shortrollover application\"([^\"]*)\"$")
	public void pre_req_for_shortrollover_application(String TestcaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		className = this.getClass().getName();
		createDirectory(className);	
		//getBrowser();
		BaseClass.ReadOR("Common_OR");
		data =ReadData("Deposit",TestcaseId);
		
		/*loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
	//	driver.switchTo().alert().accept();
		Re.addStepLog("Browser Opened & Login Page Loaded<br>");*/
	}

	@Then("^user inputs the ROshort value in the req field\"([^\"]*)\"$")
	public void user_inputs_the_ROshort_value_in_the_req_field(String Testcase_id) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  Deposit_Page.DepositRshort(Testcase_id);
		
	}

	@Then("^user authorisez the ROshort deal\"([^\"]*)\"$")
	public void user_authorisez_the_ROshort_deal(String Testcase_id) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		getBrowser();
		loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
		Thread.sleep(3000);
		Deposit_Page.RshortAuthorise(Testcase_id);
	    
	}

	@Then("^user check ROshort accounting entries\"([^\"]*)\"$")
	public void user_check_ROshort_accounting_entries(String Testcase_id) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		getBrowser();
		loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(username,password);
		loginPage.login(username,password);
		Deposit_Page.RshortCheckingentries(Testcase_id);
	    
	}


	
}
