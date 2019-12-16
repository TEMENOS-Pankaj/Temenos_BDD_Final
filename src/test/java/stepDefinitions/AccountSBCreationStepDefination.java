package stepDefinitions;

import org.apache.log4j.Logger;
import com.relevantcodes.extentreports.LogStatus;

import AccountModule_Page.Account_Page;
import CustomerModule_Page.Customer_Page;
import LoginandHome_Page.Home_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class AccountSBCreationStepDefination extends BaseClass
{
	String className="";
	String accId;
	String custid;
	

	@Given("^Input data for Create Saving Account  \"([^\"]*)\"$")
	public void prerequisite_for_ind_customer(String TestcaseId) throws Throwable {
	className = this.getClass().getName();
	createDirectory(className);	
	BaseClass.ReadOR("Account_OR");
	data =BaseClass.ReadData( "AccountSBCreation",TestcaseId);	
	}
	
	@When("^title of page is ACCOUNT$")
	public void title_of_page_is_ACCOUNT() throws Throwable {
		Account_Page.verifySavingAccountPage();	
	}

	@Then("^user inputs the mandatory value for Saving Account Creation in the field \"([^\"]*)\"$")
	public void user_inputs_the_mandatory_value_for_Saving_Account_Creation_in_the_field(String TestcaseId) throws Throwable {
		custid=getOutputFromDB("CU_Individual_Customer_Creation", TestcaseId, "CustomerId");
		Account_Page.enterSavingAccountPageDetails(custid);
		Re.addStepLog("<b>Committed the record using commit button.<b>");
		String txnMsg = Keyword.getTextFromElement("transaction_Text", driver);
		accId = txnMsg.substring(14,20);
		System.out.println("Account Number"+accId);
		writeOutputinDB("Saving_Account_Creation", TestcaseId, "Account_No", accId);
		writeOutputinDB("AccountSBCreation", TestcaseId, "AccountId", accId);
		if (txnMsg.contains("Txn Complete")) {
			Re.addStepLog("<b>Account is sucessfully created<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Account created"));
		} else {
			Re.addStepLog("<b>Account creation failed<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Account created"));
		}
	}

	@Then("^user Authorises the created Saving Account$")
	public void user_Authorises_the_created_Saving_Account() throws Throwable {
		Home_Page.Homepage("ACCOUNT,AUTH A " + accId);
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		Account_Page.authorizeAccountPageDetails(custid);
		Keyword.clickElement("authoriseButton", driver);
		String txnMsgAuth = Keyword.getTextFromElement("transaction_Text", driver);
		if (txnMsgAuth.contains("Txn Complete")) {
			Re.addStepLog("<b>Saving Account is authorized and the Customer Id is generated<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Account created and authorize"));
		} else {
			Re.addStepLog("<b>Account Creation Authoriztion has failed<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Account created and authorize"));
			test.log(LogStatus.FAIL, "<b>Account Creation Authoriztion is not completed<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Transaction creation failed")));
		}
	}

}