package LoanModule_Page;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

import utility.BaseClass;
import utility.ErrorHandler;
import utility.Keyword;
import utility.fluWaits;

public class Loan_Page extends  BaseClass {
	static String arrangementId;
	public static String debitAcc;
	public static void verifyLoanPage() throws Throwable{
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		if (homepagepagetitle.equalsIgnoreCase("AA ARRANGEMENT ACTIVITY")) {
			Re.addStepLog("<b>Successfully verified the createloan page.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "CREATELOAN page verification"));
		} else {
			Re.addStepLog("<b>Unable to verify the createloan page.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "CREATELOAN page verification"));
		}
	}
	public static void loanCommonData() throws Throwable{
		Keyword.sendText("LL_CurrencyTextbox", data.get("Currency"), driver);
		Keyword.clickElement("LL_validateDeal", driver);
		Keyword.pageHandleOnlySelectedPage("AA ARRANGEMENT ACTIVITY", driver);
		arrangementId=Keyword.getTextFromElement("LL_arrangementIdTextbox", driver);
		Keyword.clickElement("LL_commitmentTab", driver);
		Keyword.clearElement("LL_amountTextboxComit", driver);
		Keyword.sendText("LL_amountTextboxComit", data.get("AmountCommitment"), driver);
		Keyword.clearElement("LL_termTextbox", driver);
		Keyword.sendText("LL_termTextbox", data.get("Term"), driver);
	}
	
	public static String enterLoanData(String custid) throws Throwable{
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		Keyword.sendText("LL_arrangementTextbox", data.get("Arrangement"), driver);
		Keyword.sendText("LL_activityDropdown", data.get("Activity"), driver);
		Keyword.selectFromDropDown("LL_activityDropdown", data.get("Activity"), driver);
		Keyword.sendText("LL_customerTextbox", custid, driver);
		Keyword.clickElement("LL_productDropdown", driver);
		if(data.get("Product").equalsIgnoreCase("SMALL .BUSINESS.LOAN")){
		Keyword.clickElement("LL_productTableBussinessLoan", driver);
		loanCommonData();
		Keyword.clickElement("LL_scheduleTab", driver);
		Keyword.sendText("LL_amountTextboxSchedule", data.get("AmountSchedule"), driver);
		}
		if(data.get("Product").equalsIgnoreCase("HOME.EQUITY.LOAN")){
		Keyword.clickElement("LL_productTableHomeLoan", driver);
		loanCommonData();
		}
		if(data.get("Product").equalsIgnoreCase("PERSONAL.LOAN")){
			Keyword.clickElement("LL_productTablePersonalLoan", driver);
			loanCommonData();
			}
		if(data.get("Product").equalsIgnoreCase("VEHICLE.LOAN")){
			Keyword.clickElement("LL_productTablevehicleLoan", driver);
			loanCommonData();
			}
		if(data.get("Product").equalsIgnoreCase("STUDENT.LOAN1")||data.get("Product").equalsIgnoreCase("STUDENT.LOAN2")||data.get("Product").equalsIgnoreCase("STUDENT.LOAN3")){
			Keyword.clickElement("LL_productTableStudentLoan", driver);
			loanCommonData();
			Keyword.clearElement("LL_maturityDateTextbox", driver);
			Keyword.sendText("LL_maturityDateTextbox", data.get("MaturityDate"), driver);
			Keyword.clickElement("LL_tranchesTab", driver);
			Keyword.selectFromDropDown("LL_tranchesDropdown", data.get("Tranchestab"), driver);
			Keyword.sendText("LL_startDate", data.get("Start Date"), driver);
			Keyword.sendText("LL_endDate", data.get("End Date"), driver);
			Keyword.sendText("LL_amount", data.get("Amount"), driver);
			Keyword.clickElement("LL_plusButton", driver);
			Keyword.sendText("LL_startDate1", data.get("Start Date1"), driver);
			Keyword.sendText("LL_endDate1", data.get("End Date1"), driver);
			Keyword.sendText("LL_amount1", data.get("Amount1"), driver);
			Keyword.clickElement("LL_settlementInstructionTab", driver);
			Keyword.selectFromDropDown("LL_repaymentActiveDropdown", data.get("repaymentActiveDropdown"), driver);
			Keyword.sendText("LL_repaymentsettlementaccount", data.get("repaymentsettlementaccount"), driver);
			Keyword.selectFromDropDown("LL_disbursementDropdown", data.get("disbursementDropdown"), driver);
			Keyword.sendText("LL_disbursementsettlementaccount", data.get("disbursementsettlementaccount"), driver);
			}
		if(data.get("Product").equalsIgnoreCase("PERSONAL.LOAN.2W")){
			Keyword.clickElement("LL_productTablePersonalLoan2W", driver);
			loanCommonData();
			Keyword.clickElement("LL_scheduleTab", driver);
			Keyword.sendText("LL_amountTextboxSchedule", data.get("AmountSchedule"), driver);
			}
		Re.addStepLog("<b>Entered the data to take Loan.<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Entered data to take Loan"));
		Keyword.clickElement("LL_commitDealButton", driver);
		Keyword.selectFromDropDown("LL_receiveDropdown", "RECEIVED", driver);
		Keyword.clickElement("LL_commitDealButton", driver);
		Keyword.acceptOverRideClick("LL_acceptOverrideTab", driver);
		return arrangementId;
		
	}
	public static String disburse(String arrangemenrId)throws Throwable{
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		Keyword.sendText("LD_TransactionType", data.get("Transaction Type"), driver);
		Keyword.sendText("LD_arrangementIdTextBox", arrangemenrId, driver);
		Keyword.sendText("LD_debitCurrencyTextBox", data.get("Debit Currency"), driver);
		Keyword.sendText("LD_creditAccountTextBox", data.get("Credit Account"), driver);
		Keyword.sendText("LD_debitAmountTextBox", data.get("Debit Amount"), driver);
		Keyword.clickElement("validatebutton", driver);
		if(!Keyword.errorTextVisible("LD_errorText", driver)) {
		Keyword.clickElement("commitButton", driver);
		Keyword.acceptOverRideClick("acceptOverride", driver);
		return "pass";
		}
		else {
		 return "fail";	
		}
		
	}
	
	public static HashMap<String,String> verifyLoanDetails() throws Throwable {
		boolean flag= false;
		HashMap<String,String> result= new HashMap<String,String>();
		Iterator<Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
			if(pair.getValue()!=null) {
				switch(pair.getKey())
				{
				case "Debit Currency":
					flag=Keyword.getTextFromElement("LD_verifyDebitCurrency", driver).equalsIgnoreCase(pair.getValue());
					break;
				case "Debit Amount":
					flag=Keyword.getTextFromElement("LD_verifyDebitAmount", driver).equalsIgnoreCase(pair.getValue());
					break;
				case "Credit Account":
					flag=Keyword.getTextFromElement("LD_verifyCreditAcc", driver).equalsIgnoreCase(pair.getValue());
					break;
				
				default:
					flag=true;
					break;
				
				}
				if(!flag) {
					result.put(pair.getKey(), pair.getValue());
				}
			}
		}
		return result;
	}
	
	public static boolean transactionEntryVerification(String accountNo, String LCYAmount) throws Throwable{
		boolean elementPresenceAfterSearch = false;
	       
	       for(int i=1; i<=19; i++ ) {
	              
	              try 
	              {																   
	                     String transaction = fluWaits.getVisibleElement(By.xpath("//*[@id='r"+i+"']/td[2]"), driver).getText();
	                     												
	                     if(transaction.equals(accountNo)) {
	                    	 
	                    	 String amount = fluWaits.getVisibleElement(By.xpath("//*[@id='r"+i+"']/td[9]"), driver).getText();
	                    	 
	                    	 if(amount.equalsIgnoreCase(LCYAmount))
	                    	 {
	                    		 elementPresenceAfterSearch = true;
		                           break;
	                    	 }
	                     }
	              }
	              catch (Throwable e)
	              {
	      				 ErrorHandler.handle(e, driver);
	                     elementPresenceAfterSearch = false;
	              }
	       }
		return elementPresenceAfterSearch;
	}
		

}

