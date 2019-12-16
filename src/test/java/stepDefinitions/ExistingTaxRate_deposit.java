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


public class ExistingTaxRate_deposit extends BaseClass{

		@Given("^Input data for Existing Dep Customer \"([^\"]*)\"$")
		public void prerequisite_for_Existing_Dep_customer(String TestcaseId) throws Throwable {
		className = this.getClass().getName();
		createDirectory(className);	
		BaseClass.ReadOR("Common_OR");
		data =BaseClass.ReadData( "Deposit",TestcaseId);	
		}
	
		 
		 @Then("^user Enter Arrangement id click on Find button and Click on overview \"([^\"]*)\"$")
		 public void user_Enter_Arrangement_id_and_click_on_Overvirew(String TestcaseId) throws Throwable{
			 		 
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
		    Keyword.clickElement("findButton", driver);	
			Keyword.clickElement("DE_Overview", driver);
		  }		 	 
//		#Then user Click on Arrangement Conditions	 
//		 @Then("^user Click on Arrangement Conditions$")		 
//		 public void Arrangement_Conditions throws Throwable{
//			 
//			 //Keyword.pageHandleMainPage("Arrangement Overview (Deposits) - Model Bank", driver);
//			 Keyword.pageHandle(driver);
//			 driver.manage().window().maximize();
//		 }	
					  
				
		 @Then("^user click on Additional link$")			
		 public void Click_on_Additional_link() throws Throwable{
			 
			 Keyword.clickElement("DE_Additional", driver);	 
			 Thread.sleep(3000);
		 }	
			
		 @Then("^user Verify Tax Rate is displayed$")			
		 public void Verify_Tax_Rate() throws Throwable{
		 // String InterestAccrual =  Keyword.getTextFromElement("DE_MaturityDate", driver);
			 if(Keyword.isElementVisible("DE_Taxrate",driver)){
				//Keyword.acceptOverRideClick("acceptOverrideText", driver);
				 System.out.println("Interest Accrual amount "+Keyword.getTextFromElement("DE_Taxrate", driver));
				 	Re.addStepLog("Successfully verified the Existing tax rate for deposit. <b>");
					Re.addScreenCaptureFromPath(captureScreenShot(driver, "Tax rate enquiry page verification"));
					}
			 else	 {				 
				 	Re.addStepLog("Unable to verify the Existing tax rate for deposit.<br>");
					Re.addScreenCaptureFromPath(captureScreenShot(driver, "Arrangement enquiry page verification"));
					System.out.println("Interest Accrual not found");
			 }
		 }					 
			
	}	
	
