package SecurityModule_Page;

import com.relevantcodes.extentreports.LogStatus;

import utility.BaseClass;
import utility.Keyword;

public class Security_Page extends BaseClass 
{
	public static void securitiesBuyOrder(String custId) throws Throwable
	{
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		
		Keyword.sendText("SQ_SecurityTextBox", data.get("Security"), driver);
		Re.addStepLog( "<b>Security number:<b>"+data.get("Security"));
		
		String tradeCCY = Keyword.getValueOfAttribute("SQ_TradeCCY", "value", driver);
		if(!tradeCCY.equalsIgnoreCase(data.get(data.get("Trade CCY"))))
		{
			Keyword.clearElement("SQ_TradeCCY", driver);
			Keyword.sendText("SQ_TradeCCY", data.get("Trade CCY"), driver);
		}
		Re.addStepLog(  "<b>Trade CCY:<b>"+data.get("Trade CCY"));
		
		String activity = Keyword.getValueOfAttribute("SQ_Activity", "value", driver);
		if(!activity.equalsIgnoreCase(data.get(data.get("Activity"))))
		{
			Keyword.clearElement("SQ_Activity", driver);
			Keyword.sendText("SQ_Activity", data.get("Activity"), driver);
		}
		Re.addStepLog( "<b>Activity:<b>"+data.get("Activity"));
		
		Keyword.sendText("SQ_OrderDate", data.get("Order Date"), driver);
		Keyword.sendText("SQ_ValueDate", data.get("Value Date"), driver);
		
		String OrderType = Keyword.getValueOfAttribute("SQ_OrderType", "value", driver);
		if(!OrderType.equalsIgnoreCase(data.get("Order Type")))
		{
			Keyword.clearElement("SQ_OrderType", driver);
			Keyword.sendText("SQ_OrderType", data.get("Order Type"), driver);
		}
		Re.addStepLog( "<b>Order Type:<b>"+data.get("Order Type"));
		
		Keyword.sendText("SQ_LimitExpDate", data.get("Limit Exp Date"), driver);
		
		Keyword.sendText("SQ_LimitPrice", data.get("Limit Price"), driver);
		
		Keyword.sendText("SQ_ClientNum1", custId, driver);
		Re.addStepLog(  "<b>Client Number1:<b>"+data.get("Client Number.1"));
		
		Keyword.sendText("SQ_PortFolio1", custId+"-2", driver);
		Re.addStepLog(   "<b>PortFolio1:<b>"+data.get("Portfolio.1"));
		
		Keyword.sendText("SQ_ClientDepository", data.get("Client Depository"), driver);
		Re.addStepLog(  "<b>Client Depository:<b>"+data.get("Client Depository"));
		
		Keyword.sendText("SQ_Nominal", data.get("Nominal.1"), driver);
		Re.addStepLog(  "<b>Nominal:<b>"+data.get("Nominal.1"));
		
		Re.addStepLog(   "<b>Clicked on the Commit Button.<b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver, "Transaction created"));
		Keyword.clickElement("SQ_CommitButton", driver);
		Keyword.acceptOverRideClick("acceptOverrideText", driver);
	}
	
	public static void brokerDetails() throws Throwable
	{
		Keyword.clickElement("SQ_BrokerTab", driver);
		Re.addStepLog(  "<b>Clicked on the Broker Tab.<b>");
		
		Keyword.sendText("SQ_BrokerNo1", data.get("Broker No.1"), driver);
		Re.addStepLog(  "<b>Broker No.1:<b>"+data.get("Broker No.1"));
		
		Keyword.selectFromDropDown("SQ_BrokerType", data.get("Broker Type.1"), driver);
		Re.addStepLog(  "<b>Broker Type:<b>"+data.get("Broker Type.1"));
		
		Keyword.sendText("SQ_BrokerPrice1", data.get("Price.1"), driver);
		Re.addStepLog(  "<b>Broker No.1:<b>"+data.get("Price.1"));
		
		Keyword.sendText("SQ_DeliveryInstr1", data.get("Delivery Instr.1"), driver);
		Re.addStepLog(  "<b>Delivery Instr1:<b>"+data.get("Delivery Instr.1"));

	}
	
	public static void onboardCustomer() throws Throwable
	{
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		Keyword.selectFromDropDown("SQ_customerTypeDropDown", "Customer", driver);
		Keyword.clickElement("validatebutton", driver);
		Keyword.clickElement("commitButton", driver);
		Keyword.selectFromDropDown("SQ_signedMandateDropDown", "YES", driver);
		Keyword.selectFromDropDown("SQ_custodyDropDown", "YES", driver);
		Keyword.selectFromDropDown("SQ_DXTradingDropDown", "YES", driver);
		Keyword.clickElement("commitButton", driver);
		
	}
	public static void createPortfolio(String accNo,String name)throws Throwable
	{

		Keyword.sendText("SQ_referenceCurrencyTextBox", data.get("Ref Currency"), driver);
		Keyword.sendText("SQ_valCurrencyTextBox", data.get("Ref Currency"), driver);
		Keyword.sendText("SQ_accountNoTextBox", accNo, driver);		
		Keyword.sendText("SQ_portfolioprgTextBox", data.get("Portfolio Prog."), driver);
		Keyword.sendText("SQ_portfolioMgrTextBox", data.get("Portfolio Mgr."), driver);
		Keyword.sendText("SQ_managedAccountTextBox", data.get("Management Acct."), driver);
		Keyword.sendText("SQ_startDateTextBox", data.get("Start Date"), driver);
		Keyword.sendText("SQ_accountNameTextBox", name, driver);
		Keyword.clickElement("validatebutton", driver);
		Keyword.clickElement("commitButton", driver);
		Keyword.selectFromDropDown("SQ_investmentProposalDropDown", "YES", driver);
		Keyword.acceptOverRideClick("SQ_acceptOverridesOrderExecution", driver);
	}
}
	

