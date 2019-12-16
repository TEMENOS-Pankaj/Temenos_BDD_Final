package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;

import DepositModule_Page.Deposit_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utility.BaseClass;

public class AmendmentOfShortTermDepositForIndCustomer extends BaseClass {
	String activityId;
	@Given("^Input data for Amendment of Short Term Deposit  \"([^\"]*)\"$")
	public void input_data_for_Amendment_of_Short_Term_Deposit(String TestcaseId) throws Throwable {
		className = this.getClass().getName();
		createDirectory(className);	
		BaseClass.ReadOR("Common_OR");
		data =BaseClass.ReadData("Amendment_Of_Short_Term_Deposit",TestcaseId);	
	}


	@Then("^user navigates into the Amend DepositPage \"([^\"]*)\"$")
	public void user_navigates_into_the_Amend_DepositPage(String TestCase_id) throws Throwable {
		String ArrangementId=getOutputFromDB("Deposit", TestCase_id, "Arrangement_id");
		Deposit_Page.goToAmendDepositPage(ArrangementId);
	}

	@Then("^Input the data to Amend Deposit for short term\"([^\"]*)\"$")
	public void input_the_data_to_Amend_Deposit_for_short_term(String arg1) throws Throwable {
		activityId =Deposit_Page.AmendDepositPageOperation();
	}



	@Then("^user Authorises the Short Term deposit creation$")
	public void user_Authorises_the_Short_Term_deposit_creation() throws Throwable {
		Deposit_Page.authorizeAmendementRecord(activityId);
	}


}
