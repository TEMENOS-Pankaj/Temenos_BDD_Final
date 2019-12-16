package stepDefinitions;

import java.math.BigDecimal;
import FundTransferModule_Page.FundTransfer_Page;
import LoginandHome_Page.Home_Page;
import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utility.BaseClass;
import utility.Keyword;

public class FundTransferAccToAcc extends BaseClass
{
	String className="";
	LoginAndLogOut_Page loginPage;
	String custId="";
	String creditBalanceInitial="";
	String debitBalanceInitial="";
	String creditBalanceFinal="";
	String debitBalanceFinal="";
	Double expectedDebitAmount;
	Double creditBalanceInitialValue=0.00;
	String accno="";
	String debitaccno="";
	String FundTransferID="";
	
	@Given("^Input data for Fund Transfer Acc To Acc \"([^\"]*)\"$")
	public void prerequisite_for_ind_customer(String TestcaseId) throws Throwable {
	className = this.getClass().getName();
	createDirectory(className);	
	BaseClass.ReadOR("FundTransfer_OR");
	data =BaseClass.ReadData( "Fund_Transfer_AccToAcc",TestcaseId);	
	}
	
	@And("^Initial Balance of Credit and Debit Account \"([^\"]*)\"$")
	public void initialBalanceOfCreditAccount(String TestCase_id) throws Throwable {
		
		accno=getOutputFromDB("Saving_Account_Creation", TestCase_id, "Account_No");
		debitaccno=data.get("Debit Account");
		Home_Page.Homepage("ENQ STMT.ENT.TODAY");
		Keyword.pageHandleOnlySelectedPage("Statement Entries Today", driver);
		
		 Re.addStepLog("Move to the Entries for Today. <br>");
		 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Customer page verification"));
		
		/* creditBalanceInitial=FundTransfer_Page.checkinitialbalance(accno);
		{
			Re.addStepLog("<b>Initial Balance of Credit Account<b>"+creditBalanceInitial);
		}*/
		
		Keyword.pageHandleOnlySelectedPage("T24 - Model Bank", driver);
		Home_Page.Homepage("ENQ STMT.ENT.TODAY");
		debitBalanceInitial=FundTransfer_Page.checkinitialbalance(debitaccno);
		{
			Re.addStepLog("<b>Initial Balance of Debit Account<b>"+debitBalanceInitial);
		}
	}

	@Then("^fund transfer Between Accounts \"([^\"]*)\"$")
	public void fundTransferBetweenAccounts(String TestCase_id) throws Throwable {
		
		
		Keyword.pageHandleOnlySelectedPage("T24 - Model Bank", driver);
		Home_Page.Homepage("FT,ACTR.FTHP I F3");
		FundTransfer_Page.transferBetweenAccounts(accno);
		String txnMsg = Keyword.getTextFromElement("transactionText", driver);
		FundTransferID = txnMsg.substring(14,26);
		writeOutputinDB("Fund_Transfer_AccToAcc", TestCase_id, "FundTransferID", FundTransferID);
		
		if (txnMsg.contains("Txn Complete")) 
		{
			 Re.addStepLog("<b>Transaction is completed and the transfer Id is generated<b>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} 
		else
		{
			Re.addStepLog("<b>Transaction is not completed<b>");
			 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Fund Transfer failed"));
		}
		
	}

	@And("^Authorize the transaction by Admin$")
	public void authorizeTransactionByAdmin() throws Throwable {
		
		Home_Page.Homepage("FUNDS.TRANSFER,ACTR.NOST A " + FundTransferID);
		Keyword.pageHandleOnlySelectedPage("FUNDS.TRANSFER", driver);
		
		
		driver.manage().window().maximize();
		
		Re.addStepLog("<b>authorize the record.<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "authorize record"));
		
		Keyword.clickElement("authoriseButton", driver);
		
		String txnMsgAuth = Keyword.getTextFromElement("transactionText", driver);
		if (txnMsgAuth.contains("Txn Complete")) 
		{
			Re.addStepLog("<b>Transaction is authorized and the Transaction  Id is generated<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} 
		else
		{
			Re.addStepLog("<b>Transaction is not completed<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction creation failed"));
		}
		
	}
	
	@And("^Final Balance of Credit Account and Debit Account$")
	public void finalBalanceOfCreditAccountDebitAccount() throws Throwable {

		Keyword.pageHandleOnlySelectedPage("Statement Entries Today", driver);
		Re.addStepLog("<b>Move to the Entries for Today<b>");
		Keyword.clearElement("AccountNoFindTextBox", driver);
		Keyword.sendText("AccountNoFindTextBox", accno, driver);
		Keyword.clickElement("AccountNoFindButton", driver);
		
		driver.manage().window().maximize();
		
		if(FundTransfer_Page.checkentries(FundTransferID,data.get("Debit AmountExpected")))
		{
			Re.addStepLog("<b>Account details displayed with requested debited amount <b>"+data.get("Debit Amount"));
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		}
		
		else
		{
			Re.addStepLog("<b>Unable to find the Account details with requested debited amount <b>"+data.get("Debit AmountExpected"));
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		}
		Keyword.pageHandleOnlySelectedPage("T24 - Model Bank", driver);
		Home_Page.Homepage("ENQ STMT.ENT.TODAY");
		 creditBalanceFinal=FundTransfer_Page.checkinitialbalance(accno);
		{
			Re.addStepLog("<b>Final Balance of Credit Account: <b>"+creditBalanceFinal);
			
		}
		
		Keyword.pageHandleOnlySelectedPage("T24 - Model Bank", driver);
		Home_Page.Homepage("ENQ STMT.ENT.TODAY");
		 debitBalanceFinal=FundTransfer_Page.checkinitialbalance(debitaccno);
		{
			Re.addStepLog("<b>Final Balance of Debit Account: <b>"+debitBalanceFinal);
		} 
		
		/*if(creditBalanceInitial.equals("")) {
			 creditBalanceInitialValue=0.00;
		}
		else {
			creditBalanceInitialValue=Double.parseDouble(creditBalanceInitial.replaceAll(",", ""));
		}*/
		
		Double debitBalanceInitialValue=Double.parseDouble(debitBalanceInitial.replaceAll(",", ""));
	    BigDecimal finalDebitBalanceInitialValue = new BigDecimal(debitBalanceInitialValue);
		Double creditBalanceFinalValue=Double.parseDouble(creditBalanceFinal.replaceAll(",", ""));
		Double debitBalanceFinalValue=Double.parseDouble(debitBalanceFinal.replaceAll(",", ""));
		BigDecimal finalDebitBalanceFinalValue=new BigDecimal(debitBalanceFinalValue);
		expectedDebitAmount=Double.parseDouble(data.get("Debit Amount").replaceAll(",", ""));
		BigDecimal expectedDebitAmountBigdecimal=new BigDecimal(expectedDebitAmount);
		System.out.println(expectedDebitAmount);
		Double finalCredit=creditBalanceInitialValue+expectedDebitAmount;
		BigDecimal finalDebit=finalDebitBalanceInitialValue.subtract(expectedDebitAmountBigdecimal);
		String FinalDebitString=finalDebit.toString();
		String[] FinalDebitString1=FinalDebitString.split("\\.");
		String firstString = FinalDebitString1[0];
		String secondString = FinalDebitString1[1];
		String lastString = secondString.substring(0, 2);
		String finalAmnt= firstString+"."+lastString;
		System.out.println(finalAmnt);
		
		if(Double.compare(finalCredit, creditBalanceFinalValue)==0)
		{
	
			Re.addStepLog("<b>Account no.</b>"+accno+ "<b>is credited with</b>"+data.get("Debit Amount")+".<b>Balance in account is</b>"+finalCredit+".<b>Balance in account should be</b>"+creditBalanceFinal);
		}
		else
		{
			Re.addStepLog("<b>Account no.</b>"+accno+ "<b>is credited with</b>"+data.get("Debit Amount")+".<b>Balance in account is</b>"+finalCredit+".<b>Balance in account should be</b>"+creditBalanceFinal);
		}
		
		if(finalDebit.compareTo(finalDebitBalanceFinalValue)==0) 
		{
			Re.addStepLog("<b>Account no.</b>"+debitaccno+"<b>is debited with</b>"+data.get("Debit Amount")+".<b>Balance in account is</b>"+finalAmnt+".<b>Balance in account should be</b>"+debitBalanceFinal);
		}
		else
		{
			Re.addStepLog("<b>Account no.</b>"+debitaccno+"<b>is debited with</b>"+data.get("Debit Amount")+".<b>Balance in account is</b>"+finalAmnt+".<b>Balance in account should be</b>"+debitBalanceFinal);
			
		}
	}
}
