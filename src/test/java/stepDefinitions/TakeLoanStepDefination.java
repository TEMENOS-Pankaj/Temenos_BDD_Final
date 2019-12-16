package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;
import LoanModule_Page.Loan_Page;
import LoginandHome_Page.Home_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class TakeLoanStepDefination extends BaseClass{
	String className="";
	String accId;
	String custid;
	String activityId;
	@Given("^Input data for Take Loan  \"([^\"]*)\"$")
	public void input_data_for_Take_Loan(String TestcaseId) throws Throwable {
		className = this.getClass().getName();
		createDirectory(className);	
		BaseClass.ReadOR("Common_OR");
		data =BaseClass.ReadData( "TakeLoan",TestcaseId);	
	}


	@Then("^user inputs the mandatory value for Creation of Loan in the field \"([^\"]*)\"$")
	public void user_inputs_the_mandatory_value_for_Creation_of_Loan_in_the_field(String TestcaseId) throws Throwable {
		custid=getOutputFromDB("CU_Individual_Customer_Creation", TestcaseId, "CustomerId");  
		String argId=Loan_Page.enterLoanData(custid);
		String loanType=data.get("Product");
		Keyword.writeOutputinDB("TakeLoan", TestcaseId, "LoanType", loanType);
		Keyword.writeOutputinDB("TakeLoan", TestcaseId, "LoanID", argId);	
	}

	@Then("^user verifys the loan is sucessfully completed$")
	public void user_verifys_the_loan_is_sucessfully_completed() throws Throwable {
		String txnMsg = Keyword.getTextFromElement("transactionText", driver);
		activityId = txnMsg.substring(14,32);
		if (txnMsg.contains("Txn Complete")) {
			Re.addStepLog("<b>Transaction is completed and the Loan is sucessfully created<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Loan created"));
		} else {
			Re.addStepLog("<b>Transaction is not completed<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Loan created"));
		}
		

	}
	@Then("^user Authorises the  Loan Creation$")
	public void user_Authorises_the_created_Loan() throws Throwable {
		Home_Page.Homepage("AA.ARRANGEMENT.ACTIVITY A " + activityId);
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String arngauth=Keyword.getTextFromElement("LL_arrangementauth", driver);
		if(arngauth.equalsIgnoreCase("argId")){
			Re.addStepLog("<b>Autorized the data for Loan Creation.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Loan Authorize"));
			
		} else {
			Re.addStepLog("<b>Mismatch in data for Loan Creation.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Loan Authorize"));
		}
		Keyword.clickElement("LL_authorizedeal", driver);
		
		String txnMsgAuth = Keyword.getTextFromElement("LL_transactionText", driver);
	if (txnMsgAuth.contains("Txn Complete")) {
		Re.addStepLog("<b>Transaction is completed and  Loan is sucessfully Created.<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Loan Created"));
	} else {
		test.log(LogStatus.FAIL, "<b>Transaction is not completed<b>"
				+ test.addScreenCapture(captureScreenShot(driver, "Transaction creation failed")));
	}
		
	}

}
