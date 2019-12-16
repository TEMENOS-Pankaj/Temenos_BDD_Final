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

public class DepositMaturity extends BaseClass{

	static Logger log = Logger.getLogger(IndCustomerStepDefinition.class);
	String className="";
	LoginAndLogOut_Page loginPage;
	
	@Given("^pre req for deposit maturity \"([^\"]*)\"$")
	public void pre_req_for_deposit_maturity(String TestcaseId) throws Throwable {
	   
		className = this.getClass().getName();
		createDirectory(className);	
	//	getBrowser();
		
	
		BaseClass.ReadOR("Common_OR");
		
		data =ReadData("Deposit",TestcaseId);
		
		/*loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
		Re.addStepLog("Browser Opened & Login Page Loaded<br>");*/
	}
	
	
	@Then("^user input the command in the Reversal text box \"([^\"]*)\"$")
	public void user_input_the_command_in_the_Reversal_text_box(String Command) throws Throwable {
	   
		Home_Page.Homepage(Command);
		 Keyword.pageHandle(driver);
		
	}
	
	@When("^title of login page is Find DEPOSIT Arrangements$")
	public void title_of_login_page_is_Find_DEPOSIT_Arrangements() throws Throwable {
	  
		
		
			driver.manage().window().maximize();
			String homepagepagetitle = driver.getTitle();
			if (homepagepagetitle.equalsIgnoreCase("Find Deposit Arrangements")) {
				 Re.addStepLog("Successfully verified the Deposit page. <br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Find Deposit Arrangements page verification"));
			} else {
				 Re.addStepLog("Unable to verify the Deposit page.<br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Find Deposit Arrangements page verification"));
			}
		
	}
	
	@Then("^user verifies maturity activity \"([^\"]*)\"$")
	public void user_verifies_maturity_activity(String TestcaseId) throws Throwable {
	    Deposit_Page.VerifyMaturity(loginPage, TestcaseId);
	    
	    //getBrowser();
		Re.addStepLog("Browser Opened & Login Page Loaded<br>");
		loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
		  loginPage.login(adminUsername,adminPassword);
		//  Home_Page.Homepage("AAA A "+getOutputFromDB("DepositMaturity", TestcaseId,"DepositMatid"));
  
			Home_Page.Homepage("AAA A "+getOutputFromDB("DepositMaturity",TestcaseId,"DepositMatid"));
  
  Keyword.pageHandle(driver); 
  driver.manage().window().maximize(); 

	Keyword.clickElement("DE_Authorise", driver);
	 String txnMsg1 = Keyword.getTextFromElement("transactionText", driver);
	if (txnMsg1.contains("Txn Complete")) 
	{
		/*test.log(LogStatus.PASS, "<b>Transaction is completed and the transfer Id is generated<b>"
				+ test.addScreenCapture(captureScreenShot(driver, "Transaction created")));*/
		 Re.addStepLog("Transaction is completed and the transfer Id is generated<br>");
		 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction is completed and the transfer Id is generated"));
		
	} 
	else
	{
		/*test.log(LogStatus.FAIL, "<b>Transaction is not completed<b>"
				+ test.addScreenCapture(captureScreenShot(driver, "Fund Transfer failed")));*/
		 Re.addStepLog("Transaction is not completed<br>");
		 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Fund Transfer failed"));
	}
	driver.close();
	getBrowser();
	Re.addStepLog("Browser Opened & Login Page Loaded<br>");
	loginPage = new LoginAndLogOut_Page(driver);
	loginPage.login(adminUsername,adminPassword);
	  loginPage.login(adminUsername,adminPassword);
	Home_Page.Homepage("ENQ AA.FIND.ARRANGEMENT.AD");
	
	 Keyword.pageHandle(driver);
	 driver.manage().window().maximize();
		String homepagepagetitle1 = driver.getTitle();
		if (homepagepagetitle1.equalsIgnoreCase("Find Deposit Arrangements")) {
			/*test.log(LogStatus.PASS, "<b>Successfully verified the arrangement enquiry page. <b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));*/
			 Re.addStepLog("Successfully verified the arrangement enquiry page.<br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification"));
		} else {
			/*test.log(LogStatus.FAIL, "<b>Unable to verify the arrangement enquiry page.<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Arrangement enquiry page verification")));*/
			 Re.addStepLog("Unable to verify the arrangement enquiry page.<br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification"));
		}
		
		 
		  Keyword.clearElement("arrangementTextBox", driver);
		  Keyword.sendText("arrangementTextBox", getOutputFromDB("Deposit", TestcaseId,"Arrangement_id"), driver);
		
		
		  Keyword.clickElement("findButton", driver);
		
		  
		  Keyword.clickElement("DE_Overview", driver);
		  
			Keyword.pageHandleOnlySelectedPage("Arrangement Overview (Deposits) - Model Bank", driver);
			 driver.manage().window().maximize();
		  
		  String Date = Keyword.getTextFromElement("DE_MaturityDate", driver);
		  
		  
		  
		  if(Date.equalsIgnoreCase(data.get("MaturityDate")))
		  {
			  
			 /* test.log(LogStatus.PASS, "<b>Successfully verified the Maturity Date. <b>"
						+ test.addScreenCapture(captureScreenShot(driver, "Maturity Date verification")));*/
			  Re.addStepLog("Successfully verified the Maturity Date.<br>");
				 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Maturity Date verification"));
			}
		  
	    
		
	}
	
}
