package stepDefinitions;


import com.relevantcodes.extentreports.LogStatus;

import CustomerModule_Page.Customer_Page;
import LoginandHome_Page.Home_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class IndCustomerStepDefinition extends BaseClass
{
	String className="";
	LoginAndLogOut_Page loginPage;
	String custId;
	
	@Given("^Input data for Ind Customer \"([^\"]*)\"$")
	public void prerequisite_for_ind_customer(String TestcaseId) throws Throwable {
	className = this.getClass().getName();
	createDirectory(className);	
	BaseClass.ReadOR("Common_OR");
	data =BaseClass.ReadData( "CU_Individual_Customer_Creation",TestcaseId);	
	}
	
	@Then("^user input the command in the text box \"([^\"]*)\"$")
	public void user_input_the_command_in_the_text_box(String Commands ) throws Throwable {
		Home_Page.Homepage(Commands);
	}

	@When("^title of login page is CUSTOMER$")
	public void title_of_login_page_is_CUSTOMER() throws Throwable {
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		if (homepagepagetitle.equalsIgnoreCase("CUSTOMER1")) {
			 Re.addStepLog("Successfully verified the customer page. <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Customer page verification"));
		} else {
			 Re.addStepLog("Unable to verify the customer page.<br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Customer page verification"));
		}
	}

	@Then("^user inputs the value in the field \"([^\"]*)\"$")
	public void user_inputs_the_value_in_the_field(String testcaseid) throws Throwable {
		Customer_Page.customerDataMandatory();
		Customer_Page.physicaladdress();
		Customer_Page.iddoc();
		
		Keyword.clickElement("commitButton", driver);
		if(data.get("Sector").equalsIgnoreCase("1000")){
			Keyword.selectFromDropDown("introductoryDocumentDropdown", "RECEIVED", driver);
		}
		Keyword.clickElement("commitButton", driver);
		 Re.addStepLog("Committed the record using the option highlighted <br>");
		Keyword.acceptOverRideClick("acceptOverrideText", driver);

		String txnMsg = Keyword.getTextFromElement("transactionText", driver);
		custId = txnMsg.substring(14,20);

		String custName=data.get("GB Short Name");
		writeOutputinDB("CU_Individual_Customer_Creation", testcaseid, "CustomerId", custId);
		writeOutputinDB("CU_Individual_Customer_Creation", testcaseid, "CustomerName", custName);
		if (txnMsg.contains("Txn Complete")) {
			 Re.addStepLog("Transaction is completed and the Customer Id is generated <br>");
			
		} else {
			test.log(LogStatus.FAIL, "<b>Transaction is not completed<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "Transaction creation failed")));
			
			 Re.addStepLog("Transaction is not completed <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
		}
	}

	@Then("^user authorises the deal$")
	public void user_authorises_the_deal() throws Throwable {
		Home_Page.Homepage("CUSTOMER,NAU A " + custId);
		Keyword.pageHandle(driver);
		
		driver.manage().window().maximize();
		 Re.addStepLog("authorize the record. <br>");
		Keyword.clickElement("authoriseButton", driver);
		
		String txnMsgAuth = Keyword.getTextFromElement("transactionText", driver);
		if (txnMsgAuth.contains("Txn Complete")) {
			 Re.addStepLog("Transaction is authorized and the Customer Id is generated <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} else {
			 Re.addStepLog("Transaction is not completed <br>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
		}
	}
}
