package stepDefinitions;

import com.relevantcodes.extentreports.LogStatus;

import FundTransferModule_Page.FundTransferOutwardRemittance_Page;
import LoginandHome_Page.Home_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utility.BaseClass;
import utility.Keyword;

public class CreateOutwardRemittancewithMT103StepDefination extends BaseClass{
	String FundTransferID="";
	String debitAccno;
	
	@Given("^Input data for Create Outward Remittance with MTonezerothree  \"([^\"]*)\"$")
	public void input_data_for_Create_Outward_Remittance_with_MTonezerothree(String TestcaseId) throws Throwable {
		className = this.getClass().getName();
		createDirectory(className);	
		BaseClass.ReadOR("Common_OR");
		data =BaseClass.ReadData( "Fund_Transfer_Outward",TestcaseId);	
	}
	
	@Then("^user inputs the mandatory value for Creation of Outward Remittance with MTonezerothree$")
	public void user_inputs_the_mandatory_value_for_Creation_of_Outward_Remittance_with_MTonezerothree() throws Throwable {
		debitAccno=data.get("Debit Account");
		FundTransferOutwardRemittance_Page.transferOutward(debitAccno);
		String txnMsg = Keyword.getTextFromElement("transactionText", driver);
		FundTransferID = txnMsg.substring(14,26);
		if (txnMsg.contains("Txn Complete")) 
		{
			Re.addStepLog("<b>Transaction is completed and the transfer Id is generated. <b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} 
		else
		{
			Re.addStepLog("<b>Transaction is not completed. <b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Fund Transfer failed"));

		}
	}
	
	@Then("^user Authorises the created Created Outward Remittance with MTonezerothree$")
	public void user_Authorises_the_created_Created_Outward_Remittance_with_MTonezerothree() throws Throwable {
		Home_Page.Homepage("FUNDS.TRANSFER,OT102 A " + FundTransferID);
		 Keyword.pageHandle(driver);
		 driver.manage().window().maximize();
		 	Re.addStepLog("<b>authorize the record. <b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "authorize record"));
			Keyword.clickElement("authoriseButton", driver);
			
			String txnMsgAuth = Keyword.getTextFromElement("transactionText", driver);
			if (txnMsgAuth.contains("Txn Complete")) {
				Re.addStepLog("<b>Transaction is authorized fund is transferred.<b>");
				Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));

			} else {
				Re.addStepLog("<b>Transaction is not completed.<b>");
				Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
				
			}
		 Keyword.selectFromDropDown("FTOR_moreActionsDropDown", "List of entries (authorised)", driver);
		 Keyword.clickElement("FTOR_goButton", driver);
		 Keyword.pageHandle(driver);
		 Keyword.clearElement("FTOR_transactionRefTextBox", driver);
		 Keyword.sendText("FTOR_transactionRefTextBox", FundTransferID, driver);
		 Keyword.clickElement("FTOR_findButton", driver);
		 driver.manage().window().maximize();
		// boolean verified=FundTransferOutwardRemittance_Page.debitedAmountVerification(debitAccno, data.get("Credit Amount"));
		 String transferAmount="-"+data.get("Credit Amount");
		 if(FundTransferOutwardRemittance_Page.debitedAmountVerification(debitAccno,transferAmount ))
			{
			 Re.addStepLog("<b>Account details displayed with requested debited amount <b>.");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));

			}
			
			else
			{
				 Re.addStepLog("<b>Unable to find the Account details with requested debited amount</b>.");
					Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction failed"));

			}
		 Keyword.clickElement("FTOR_viewEntriesButton", driver);
		 driver.manage().window().maximize();
		 String accountVerify=Keyword.getTextFromElement("FTOR_accountVerify", driver);
		 String amountVerify=Keyword.getTextFromElement("FTOR_amountVerify", driver).split("\\.")[0].replace(",", "");
		 if(accountVerify.equalsIgnoreCase(debitAccno)&&amountVerify.equalsIgnoreCase(transferAmount))
			{
			 Re.addStepLog("<b>Account details displayed and verified. <b>.");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
			
			}
			
			else
			{
				 Re.addStepLog("<b>Unable to find the correct Account details. <b>.");
					Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction failed"));
				
			}
	}
	}
	