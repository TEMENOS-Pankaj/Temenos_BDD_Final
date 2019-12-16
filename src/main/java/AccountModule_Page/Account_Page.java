package AccountModule_Page;

import utility.BaseClass;
import utility.Keyword;

public class Account_Page extends BaseClass {
	public static void verifySavingAccountPage() throws Throwable{
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		String homepagepagetitle = driver.getTitle();
		if (homepagepagetitle.equalsIgnoreCase("ACCOUNT")) {
			Re.addStepLog("<b>Successfully verified the saving account page. <b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "SAVING ACCOUNT page verification"));
		} else {
			Re.addStepLog("<b>Unable to verify the saving account.<b>");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "SAVING ACCOUNT page verification"));
		}
}
	public static void enterSavingAccountPageDetails(String cust_id) throws Throwable{
		Keyword.sendText("customerId_Textbox", cust_id, driver);
		Keyword.sendText("currency_Textbox",data.get("Currency") , driver);
		Keyword.sendText("jointHolderIdTextbox", data.get("JointHolderIdTextbox"), driver);
		Keyword.sendText("jointRelationCodeTextbox", data.get("JointRelationCode"), driver);
		Keyword.selectFromDropDown("intLiquidationTypeDropdown", data.get("Int Liquidation Type.1"), driver);
		Keyword.sendText("intLiquidationAccountTextbox", data.get("Int Liquidation Acct.1"), driver);
		Re.addStepLog("<b>Entered the account details. <b>");
		Re.addScreenCaptureFromPath(captureScreenShot(driver,  "Enter account details"));
		Keyword.clickElement("commitdeal_Button", driver);
		
	}
	public static void authorizeAccountPageDetails(String custid) throws Throwable{
		String authCustId=Keyword.getTextFromElement("authCustomerId", driver);
		String authCurrency=Keyword.getTextFromElement("authCurrency", driver);
		String exptCurrency=data.get("Currency");
		String exptCustId=custid;
		if(authCustId.equalsIgnoreCase(exptCustId)&&authCurrency.equalsIgnoreCase(exptCurrency)){
			Re.addStepLog("\"<b>Successfully verified the saving account details <b>\"");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Saving Account verification"));
			
		} else {
			Re.addStepLog("\"<b>Unable to verify the saving account details.<b>\"");
			Re.addScreenCaptureFromPath(captureScreenShot(driver, "Saving Account verification"));
		}
		}
	}
	

