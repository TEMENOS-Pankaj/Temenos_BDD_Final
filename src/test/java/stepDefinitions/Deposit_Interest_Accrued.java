package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;
import LoginandHome_Page.Home_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;
import org.apache.log4j.Logger;

public class Deposit_Interest_Accrued extends BaseClass{
		  // (note enter 3 dots before and after for search)
	
	@Given("^Input data for Dep Customer \"([^\"]*)\"$")
	public void prerequisite_for_Dep_customer(String TestcaseId) throws Throwable {
	className = this.getClass().getName();
	createDirectory(className);	
	BaseClass.ReadOR("Common_OR");
	data =BaseClass.ReadData( "Deposit",TestcaseId);	
	}

	 @Then("^user Enter the Arrangment Id in Search and click the find \"([^\"]*)\"$")
	 public void user_Enter_the_Arrangment_Id_in_Search_and_click_the_find(String TestcaseId) throws Throwable {
	 
		 
		 Keyword.pageHandleOnlySelectedPage("Find Deposit Arrangements", driver);
		// Keyword.pageHandle(driver);
		 driver.manage().window().maximize();
			String homepagepagetitle1 = driver.getTitle();
			if (homepagepagetitle1.equalsIgnoreCase("Find Deposit Arrangements")) {
				Re.addStepLog("Successfully verified the arrangement enquiry page. <b>");
				Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification"));
			} else {
				Re.addStepLog("Unable to verify the arrangement enquiry page.<br>");
				Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification"));

			} 
						
		Keyword.clearElement("arrangementTextBox", driver);	 
    	String Arrangementid =  getOutputFromDB("Deposit", TestcaseId, "Arrangement_id");
		System.out.println(Arrangementid);
		//Keyword.sendText("arrangementTextBox", "Test" ,driver);
		Keyword.sendText("arrangementTextBox", Arrangementid ,driver);	
	 }	 

	 @Then("^user Click on the Search icon$")
	 public void user_Click_on_the_Search_icon() throws Throwable {
		
		 Keyword.clickElement("findButton", driver);
		 
		 Keyword.clickElement("DE_Overview", driver);		 
		 Thread.sleep(9000);
		 
	 }
	 
	 @Then("^user Validates Interest Accrual for a deposit$")
	 public void user_Validates_Interest_Accrual_for_a_deposit() throws Throwable {
		 //Keyword.pageHandleMainPage(driver);
		 driver.manage().window().maximize();	 
		 // String InterestAccrual =  Keyword.getTextFromElement("DE_MaturityDate", driver);
		 if(Keyword.isElementVisible("InterestAccrual",driver))
		 {
		//Keyword.acceptOverRideClick("acceptOverrideText", driver);
		 System.out.println("Interest Accrual amount "+Keyword.getTextFromElement("DE_InterestAmount", driver));
		    Re.addStepLog("Successfully verified the Interest Accrual amount. <b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Interest Accrual amount verification"));
		} 
		 else{
			 System.out.println("Interest Accrual not found");
			 Re.addStepLog("Unable to verify the Interest Accrual amount.<br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Interest Accrual amount verification"));
				 
				}
		 }
	 }
	 
	