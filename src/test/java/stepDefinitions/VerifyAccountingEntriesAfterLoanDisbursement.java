package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;

import LoanModule_Page.Loan_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utility.BaseClass;
import utility.Keyword;

public class VerifyAccountingEntriesAfterLoanDisbursement extends BaseClass{
	String className="";
	
	@Given("^Input data for Verifying Account Entries After Loan Disbursement  \"([^\"]*)\"$")
	public void input_data_for_Verifying_Account_Entries_After_Loan_Disbursement(String TestcaseId) throws Throwable {
		className = this.getClass().getName();
		createDirectory(className);	
		BaseClass.ReadOR("Common_OR");
		data =BaseClass.ReadData( "Loan_Disbursement",TestcaseId);
	}
	
	@Then("^user Verify the Account Entries  Loan disbursement \"([^\"]*)\"$")
	public void user_Verify_the_Account_Entries_Loan_disbursement(String TestCase_id) throws Throwable {
	
		String arrangementId=getOutputFromDB("TakeLoan", TestCase_id, "LoanID");
		 Keyword.pageHandle(driver); 
		  driver.manage().window().maximize();
		  Keyword.clearElement("LD_TransactionRefTextBox", driver);
		  Keyword.sendText("LD_TransactionRefTextBox", arrangementId, driver);
			Re.addStepLog("<b>Entered the Transaction RefID:<b>"+ arrangementId );
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "authorize record"));
		  Keyword.clickElement("LD_TransactionRefFindButton", driver);
		  Re.addStepLog("<b>Clicked on the Find button.<b>");
		  Keyword.pageHandleOnlySelectedPage("List Of Entries",driver);
		String amount="-"+data.get("Debit Amount");
		String debitacc=Keyword.getOutputFromDB("LoanDisbursement", TestCase_id, "debitAcc");
		 if( Loan_Page.transactionEntryVerification(debitacc, amount)) 
		 {
			 Re.addStepLog("<b>Entries verified successfully.</b>" );
				Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));

		} else {
			 Re.addStepLog("<b>Entries not verified.Mismatch in LCY amount.Expected value:-6000.00 , Actual value:"+data.get("Debit Amount")+"<b>");
				Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
					
			 
		 }
		  
		}
		
	
}
