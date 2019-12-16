package stepDefinitions;

import DepositModule_Page.Deposit_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utility.BaseClass;
import utility.Keyword;

public class AmendmentofThreeMonthsDepositForInd extends BaseClass{
	String ArrangementId;
	@Given("^Input data for Amendment of Three Months Deposit  \"([^\"]*)\"$")
	public void input_data_for_Amendment_of_Short_Term_Deposit(String TestcaseId) throws Throwable {
		className = this.getClass().getName();
		createDirectory(className);	
		BaseClass.ReadOR("Common_OR");
		data =BaseClass.ReadData( "Amendment_Of_Three_Month_Deposit",TestcaseId);	
	}
	

	@Then("^user navigates into the Amend Three Month Deposit Page \"([^\"]*)\"$")
	public void user_navigates_into_the_Amend_Three_Month_Deposit_Page(String TestCase_id) throws Throwable {
		ArrangementId=getOutputFromDB("Deposit", TestCase_id, "Arrangement_id");
		Deposit_Page.goToAmendDepositPage(ArrangementId);
	
	    
	}

	@Then("^Input the data to Amend Deposit for Three Months\"([^\"]*)\"$")
	public void input_the_data_to_Amend_Deposit_for_Three_Months(String arg1) throws Throwable {
		Deposit_Page.AmendThreeMonthsDepositPageOperation();
	   
	}

	@Then("^user Authorises the Three Month deposit creation$")
	public void user_Authorises_the_Three_Month_deposit_creation() throws Throwable {
		Keyword.handleFrame(1, driver);
		Keyword.clickElement("AA_Dep_UserMenuLink", driver);
		Keyword.clickElement("AA_Dep_RetailOperationLink", driver);
		Keyword.clickElement("AA_Dep_unauthAAARecords", driver);
		Keyword.pageHandle(driver);
		Deposit_Page.AmendThreeMonthsDepositAuthorization(ArrangementId);
	    
	}
	}

