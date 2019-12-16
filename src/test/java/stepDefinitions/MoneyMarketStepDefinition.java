package stepDefinitions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import LoginandHome_Page.Home_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import MoneyMarketModule_Page.MoneyMarket_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class MoneyMarketStepDefinition extends BaseClass{

	
	static Logger log = Logger.getLogger(MoneyMarketStepDefinition.class);
	String className="";
	LoginAndLogOut_Page loginPage;
	String Transaction_id;

	@Given("^pre_req_for_moneymarket_application \"([^\"]*)\"$")
	public void pre_req_for_moneymarket_application(String TestcaseId) throws Throwable {
		
		className = this.getClass().getName();
		createDirectory(className);	
		//getBrowser();
		BaseClass.ReadOR("Common_OR");
		
		data =ReadData("MM_Call",TestcaseId);
		
		/*loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
		Re.addStepLog("Browser Opened & Login Page Loaded<br>");*/
		
	}
	
	/*@Then("^user input the command in the texttbox$")
	public void user_input_the_command_in_the_texttbox() throws Throwable {
		
		Home_Page.Homepage("MM.MONEY.MARKET,PLACE.CALL I F3");
	}*/

	
	@When("^title of login page is MONEY MARKET$")
	public void title_of_login_page_is_MONEY_MARKET() throws Throwable {
	   
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		if (homepagepagetitle.equalsIgnoreCase("MONEY MARKET")) {
			 Re.addStepLog("Successfully verified the customer page. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Find Deposit Arrangements page verification"));
		} else {
			 Re.addStepLog("Unable to verify the customer page.<br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Find Deposit Arrangements page verification"));
		}
		
	}
	
	@Then("^user inputs the moneymarket value in the req field \"([^\"]*)\"$")
	public void user_inputs_the_moneymarket_value_in_the_req_field(String testcaseid) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		MoneyMarket_Page.MoneyMarket(testcaseid,Transaction_id);
	//	System.out.println("user inputs the value "+Transaction_id);
		/*Keyword.pageHandleOnlySelectedPage("T24 - Model Bank", driver);
		System.out.println("Handled the frame nd found model bank page");*/
		driver.close();
	}
	
	@Then("^user authorisez moneymarket deal$")
	public void user_authorisez_moneymarket_deal() throws Throwable {
		
		/*Keyword.pageHandleOnlySelectedPage("T24 - Model Bank", driver);
		System.out.println("Handled the frame nd found model bank page");*/
		/*Keyword.pageHandleMainPage(driver);
		Keyword.handleFrame(0, driver);
		Keyword.clickElement("signOffButton", driver);*/
		getBrowser();
BaseClass.ReadOR("Common_OR");
		
		//data =ReadData("MM_Call",TestcaseId);
loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(username,password);
		Home_Page.Homepage("ENQ MM.AUTH.NAU");
		Keyword.pageHandle(driver);
		
		MoneyMarket_Page.AuthoriseMM(getOutputFromDB("MoneyMarket_Call","TC_01", "Transaction_id"));
		
		Keyword.pageHandleOnlySelectedPage("T24 - Model Bank", driver);
		Keyword.handleFrame(0, driver);
		Keyword.clickElement("signOffButton", driver);
		
		log.info("Money Market data Test");
		loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
		/*Home_Page.Homepage("ENQ TXN.ENTRY.MB");
		Keyword.pageHandle(driver);*/
		
	}
	
	@Then("^user check moneymarket accounting entries$")
	public void user_check_moneymarket_accounting_entries() throws Throwable {
		Home_Page.Homepage("ENQ TXN.ENTRY.MB");
		Keyword.pageHandle(driver);
		
		MoneyMarket_Page.AccountEntries(getOutputFromDB("MoneyMarket_Call","TC_01", "Transaction_id"));
		
	}
}
