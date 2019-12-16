package stepDefinitions;

import DepositModule_Page.Deposit_Page;
import LoginandHome_Page.Home_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class DepositRolloverT extends BaseClass{
	LoginAndLogOut_Page loginPage;
	
	@Given("^pre_req_for_threerollover_application \"([^\"]*)\"$")
	public void pre_req_for_threerollover_application(String TestcaseId) throws Throwable {
	//	test= getClassName("IndCustomerCreation");
		className = this.getClass().getName();
		createDirectory(className);	
		//getBrowser();
		
	
		BaseClass.ReadOR("Common_OR");
		//System.out.println("OR called once");
		//data =BaseClass.ReadData("Input_DataSheet", "CU_Individual_Customer_Creation",TestcaseId);
		data =ReadData("Deposit",TestcaseId);
	//	test= getClassName("Roll over three");
		/*loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
		Re.addStepLog("Browser Opened & Login Page Loaded<br>");*/
		
	}
	
	/*@Then("^user input the Rolloversix command in the texttbox$")
	public void user_input_the_Rolloversix_command_in_the_texttbox(String Commands) throws Throwable {
		

		Home_Page.Homepage(Commands);
		 Keyword.pageHandle(driver);
	}*/

	/*@Then("^user input the command in the text box \"([^\"]*)\"$")
	public void user_input_the_command_in_the_text_box(String Commands ) throws Throwable {
		Home_Page.Homepage(Commands);
	}*/
	
	@When("^title of login page is Find Deposit Arrangements$")
	public void title_of_login_page_is_Find_Deposit_Arrangements() throws Throwable {
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
	}

	@Then("^user inputs the ROThree value in the req field \"([^\"]*)\"$")
	public void user_inputs_the_ROThree_value_in_the_req_field(String Testcase_id) throws Throwable {
		System.out.println("Inside required field method");
	//	data =ReadData("Deposit",Testcase_id);
	    Deposit_Page.DepositRThree(Testcase_id);
	}

	@Then("^user authorisez the ROThree deal \"([^\"]*)\"$")
	public void user_authorisez_the_ROThree_deal(String Testcase_id) throws Throwable {
		getBrowser();
		loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
		
		//Thread.sleep(3000);
		Deposit_Page.RthreeAuthorise(Testcase_id);
		
	}

	@Then("^user check ROThree accounting entries \"([^\"]*)\"$")
	public void user_check_ROThree_accounting_entries(String testcase_id) throws Throwable {
	    
		getBrowser();
		loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(username,password);
		Deposit_Page.RthreeCheckingentries(testcase_id);
		
	}


	
}
