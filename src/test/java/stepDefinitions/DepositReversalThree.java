package stepDefinitions;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DepositModule_Page.Deposit_Page;
import LoginandHome_Page.Home_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class DepositReversalThree extends BaseClass{

	static Logger log = Logger.getLogger(IndCustomerStepDefinition.class);
	String className="";
	LoginAndLogOut_Page loginPage;
	
	@Given("^pre req for deposit reversal \"([^\"]*)\"$")
	public void pre_req_for_deposit_reversal(String TestcaseId) throws Throwable {
	    
		className = this.getClass().getName();
		createDirectory(className);	
		//getBrowser();
		
	
		BaseClass.ReadOR("Common_OR");
		
		data =ReadData("Deposit",TestcaseId);
		
	/*	loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
		Re.addStepLog("Browser Opened & Login Page Loaded<br>");*/
	//	driver.switchto.alert.submit();
		//Thread.sleep(3000);
		 /* Alert alert = driver.switchTo().alert();		
  		System.out.println("Alert handled");
	        // Capturing alert message.    
	        String alertMessage= driver.switchTo().alert().getText();		
	        		
	        // Displaying alert message		
	        System.out.println(alertMessage);	
	        Thread.sleep(5000);
	        		
	        // Accepting alert		
	        alert.accept();	*/	
		
		/*new WebDriverWait(driver, 60)
        .ignoring(NoAlertPresentException.class)
        .until(ExpectedConditions.alertIsPresent());

Alert al = driver.switchTo().alert();
al.accept();*/
	}

	
	/*@Then("^user input the command in the Reversal text box \"([^\"]*)\"$")
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
		
	}*/

	@Then("^user reverses financial activity \"([^\"]*)\"$")
	public void user_reverses_financial_activity(String TestcaseId) throws Throwable {
	    System.out.println(TestcaseId);
		Deposit_Page.ReversalThree(TestcaseId);
		 
		 
		
	}
	
}
