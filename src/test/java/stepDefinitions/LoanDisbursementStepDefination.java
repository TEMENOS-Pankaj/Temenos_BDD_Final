package stepDefinitions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.relevantcodes.extentreports.LogStatus;

import LoanModule_Page.Loan_Page;
import LoginandHome_Page.Home_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;
import utility.Keyword;

public class LoanDisbursementStepDefination extends BaseClass{
	String className="";
	String accId;
	String custid;
	String activityId;
	String res;
	String transactionId;
	
	@Given("^Input data for Loan Disbursement \"([^\"]*)\"$")
	public void input_data_for_Loan_Disbursement(String TestcaseId) throws Throwable {
		className = this.getClass().getName();
		createDirectory(className);	
		BaseClass.ReadOR("Common_OR");
		data =BaseClass.ReadData( "Loan_Disbursement",TestcaseId);
	  
	}

	@Then("^user inputs the mandatory value for Loan disbursement \"([^\"]*)\"$")
	public void user_inputs_the_mandatory_value_for_Loan_disbursement(String TestCase_id) throws Throwable {
		String arrangementId=getOutputFromDB("TakeLoan", TestCase_id, "LoanID");
		 res= Loan_Page.disburse(arrangementId);
	}

	@Then("^user verifys the loan is disbursed sucessfully completed$")
	public void user_verifys_the_loan_is_disbursed_sucessfully_completed() throws Throwable {
		if(res.equalsIgnoreCase("pass")) {
			String txnMsg = Keyword.getTextFromElement("transactionMsg", driver);
			transactionId = txnMsg.substring(14, 26);
			System.out.println(transactionId);
			if (txnMsg.contains("Txn Complete")) {
				Re.addStepLog("<b>Transaction is completed and deposit is created.<b>");
				Re.addScreenCaptureFromPath(captureScreenShot(driver, "Loan created"));
			} else {
				Re.addStepLog("<b>Transaction is not completed.<b>");
				Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
			}
	}
	}
	@Then("^user Authorises the  Loan Disbursement \"([^\"]*)\"$")
	public void user_Authorises_the_Loan_Disbursement(String TestCase_id) throws Throwable {
		 Home_Page.Homepage("FUNDS.TRANSFER A "+transactionId);
		  Keyword.pageHandle(driver); 
		  driver.manage().window().maximize();
		  String debitAcc= Keyword.getTextFromElement("LD_debitAccNo", driver);
		  Keyword.writeOutputinDB("LoanDisbursement", TestCase_id, "debitAcc", debitAcc);
			System.out.println("Acccccccccccccccounttt"+debitAcc);
		  HashMap<String, String> result=Loan_Page.verifyLoanDetails();
			if(!result.isEmpty()) {
				Iterator<Entry<String, String>> it = result.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
					Re.addStepLog("<b>Value Mismatch.Field name:<b>"+pair.getKey()+"<b>Wrong Value:<b>"+pair.getValue());
				}
				Keyword.clickElement("authoriseButton", driver);
			}
			else {
			Re.addStepLog("<b>authorize the record.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "authorize record"));
			Keyword.clickElement("authoriseButton", driver);
			}
		  String txnMsg2= Keyword.getTextFromElement("transactionMsg", driver);
		  if (txnMsg2.contains("Txn Complete")) {
			Re.addStepLog("<b>Transaction is completed and deposit is authorised.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
			} else {
				Re.addStepLog("<b>Transaction is not completed.<b>");
				Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation Failed"));
			}
	    
	}

}
