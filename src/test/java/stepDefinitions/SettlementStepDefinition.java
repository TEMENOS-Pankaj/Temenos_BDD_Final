package stepDefinitions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utility.BaseClass;
import utility.Keyword;

public class SettlementStepDefinition extends BaseClass {
	String className="";
	LoginAndLogOut_Page loginPage;
	String transId;
	
	@Given("^Input data for Settlement \"([^\"]*)\"$")
	public void input_data_for_Settlement(String TestcaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		className = this.getClass().getName();
		createDirectory(className);	
		BaseClass.ReadOR("Common_OR");
		data =BaseClass.ReadData( "SettlementOfTrade",TestcaseId);
	    
	}

	@Then("^user navigates to Settlement page$")
	public void user_navigates_to_Settlement_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Keyword.handleFrame(1, driver);
		Keyword.clickElement("ST_userMenu", driver);
		Keyword.clickElement("ST_privateOperationsMenu", driver);
		Keyword.clickElement("ST_securitiesMenu", driver);
		Keyword.clickElement("ST_backOfficeMenu", driver);
		Keyword.clickElement("ST_securitySettlementMenu", driver);
		Keyword.clickElement("ST_outstandingSettlementMenu", driver);
	    
	}

	@Then("^user inputs Settlement values in fields \"([^\"]*)\"$")
	public void user_inputs_Settlement_values_in_fields(String testCaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		Keyword.handleFrameByXpath("ST_frame", driver);
		Keyword.handleFrameByXpath("ST_frame2", driver);
		Keyword.clickElement("ST_searchIcon", driver);
		transId= getOutputFromDB("Security_BuyOrder", testCaseId, "transactionId");
		Keyword.sendText("ST_transRefSearchBox", transId, driver);
		Keyword.clickElement("ST_findButton", driver);
		Keyword.clickElement("ST_securityTrade", driver);
		Keyword.clickElement("ST_settleFullButton", driver);
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String nominalBroker=Keyword.getValueOfAttribute("ST_nominalBrokerTextBox", "value", driver);
		String nominalCust=Keyword.getValueOfAttribute("ST_nominalCustomerTextBox", "value", driver);
		if(!nominalBroker.equalsIgnoreCase(data.get("Nominal.1"))){
			Keyword.sendText("ST_nominalBrokerTextBox", data.get("Nominal.1"), driver);
		}
		if(!nominalCust.equalsIgnoreCase(data.get("Nominal.1"))){
			Keyword.sendText("ST_nominalBrokerTextBox", data.get("Nominal.1"), driver);
		}
		String valueDate=Keyword.getValueOfAttribute("ST_valueDateBrokerTextBox1", "value", driver);
		String valDate= data.get("Value Date");
	    Date date1=new SimpleDateFormat("yyyyMMdd").parse(valDate);  
	    String dateverify = DateFormat.getDateInstance(DateFormat.MEDIUM).format(date1).replace(",", "");
	    if(!valueDate.equalsIgnoreCase(dateverify)) {
	    	Keyword.sendText("ST_valueDateBrokerTextBox1", data.get("Value Date"), driver);
	    }
	    Re.addStepLog( "<b>Verified all the values.<b>");
	    Re.addScreenCaptureFromPath(captureScreenShot(driver, "The trade is completed"));
	    Keyword.clickElement("commitButton", driver);
	    Keyword.acceptOverRideClick("acceptOverride", driver);
	    String txnMsgAuth = Keyword.getTextFromElement("transactionText", driver);
	    if (txnMsgAuth.contains("Txn Complete")) {
	    	Re.addStepLog(  "<b>The trade is settled successfully.<b>");
	    	 Re.addScreenCaptureFromPath(captureScreenShot(driver, "The trade is completed"));
		} else {
			Re.addStepLog( "<b>The trade is not settled.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "The trade is completed"));
		}
	   
	}
	
	@Then("^user checks position \"([^\"]*)\"$")
	public void user_checks_position(String testCaseId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Keyword.pageHandle(driver);
		String custId=getOutputFromDB("CU_Individual_Customer_Creation", testCaseId, "CustomerId");
		Keyword.clearElement("POS_portfolioTextBox", driver);
		Keyword.sendText("POS_portfolioTextBox", custId+"-2", driver);
		Keyword.clickElement("POS_findButton", driver);
	//Error case---	String nominal=data.get("Nominal.1");
		String nominal=data.get("Nominal.1");
		String position=Keyword.getTextFromElement("POS_positionValueVerify", driver);
		if(position.equalsIgnoreCase(nominal)) {
			Re.addStepLog("<b>Positions updated successfully.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		} 
		else
		{
			Re.addStepLog( "<b>Positions not updated.Expected value:100 , Actual value:"+data.get("Nominal.1")+"<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Fund Transfer failed"));
		}
	}


}
