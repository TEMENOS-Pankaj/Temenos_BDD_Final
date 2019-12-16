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

public class DepositRolloverS extends BaseClass{
	
	
	static Logger log = Logger.getLogger(IndCustomerStepDefinition.class);
	String className="";
	LoginAndLogOut_Page loginPage;
	
	
	@Given("^pre req for sixrollover application\"([^\"]*)\"$")
	public void pre_req_for_sixrollover_application(String TestcaseId) throws Throwable {
	//	test= getClassName("IndCustomerCreation");
		className = this.getClass().getName();
		createDirectory(className);	
		//getBrowser();
		
	
		BaseClass.ReadOR("Common_OR");
		//System.out.println("OR called once");
		//data =BaseClass.ReadData("Input_DataSheet", "CU_Individual_Customer_Creation",TestcaseId);
		data =ReadData("Deposit",TestcaseId);
		//test= getClassName("Roll over six");
		/*loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
	
		Re.addStepLog("Browser Opened & Login Page Loaded<br>");*/
		
	}
	
	
	/*@Then("^user input the Rolloversix command in the texttbox$")
	public void user_input_the_Rolloversix_command_in_the_texttbox(String Commands) throws Throwable {
		

		Home_Page.Homepage(Commands);
		 Keyword.pageHandle(driver);
	}*/

	
	/*@When("^title of login page is DEPOSIT$")
	public void title_of_login_page_is_DEPOSIT() throws Throwable {
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		if (homepagepagetitle.equalsIgnoreCase("Find Deposit Arrangements")) {
			 Re.addStepLog("Successfully verified the customer page. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Find Deposit Arrangements page verification"));
		} else {
			 Re.addStepLog("Unable to verify the customer page.<br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Find Deposit Arrangements page verification"));
		}
	}*/
	
	@Then("^user inputs the ROsix value in the req field\"([^\"]*)\"$")
	public void user_inputs_the_ROsix_value_in_the_req_field(String Testcase_id) throws Throwable {
	
	    Deposit_Page.DepositRSix(Testcase_id);
	}

	@Then("^user authorisez the ROsix deal\"([^\"]*)\"$")
	public void user_authorisez_the_ROsix_deal(String Testcase_id) throws Throwable {
		getBrowser();
		loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
		Thread.sleep(3000);
		Deposit_Page.RsixAuthorise(Testcase_id);
	}

	@Then("^user check ROsix accounting entries\"([^\"]*)\"$")
	public void user_check_ROsix_accounting_entries(String Testcase_id) throws Throwable {
		getBrowser();
		loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(username,password);
		Deposit_Page.RsixCheckingentries(Testcase_id);
	}


	
}
