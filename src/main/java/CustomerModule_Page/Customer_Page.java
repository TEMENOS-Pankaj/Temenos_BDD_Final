package CustomerModule_Page;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.LogStatus;

import utility.BaseClass;
import utility.Keyword;

public class Customer_Page extends BaseClass
{
	static String testCaseId;
	public static void customerDataMandatory() throws Throwable{	
		Keyword.selectFromDropDown("titleDropdown", data.get("Title"), driver);
		Keyword.sendText("gbFullName", data.get("GB Full Name"), driver);
		Keyword.sendText("gbShortname", data.get("GB Short Name"), driver);
		Keyword.sendText("givenNameTextBox", data.get("Given Name"), driver);
		Keyword.selectFromDropDown("titleDropdown", data.get("Title"), driver);
		String genderData = Keyword.getTextFromElement("genderRadioButton", driver);
		if (genderData.equalsIgnoreCase(data.get("Gender"))) {
			Keyword.clickElement("genderMaleRadioButton", driver);
		} else {
			Keyword.clickElement("genderFemaleRadioButton", driver);
		}
		Keyword.sendText("mnemonicTextBox", generateMnemonic(data.get("Mnemonic")), driver);
		Keyword.sendText("sectorTextBox", data.get("Sector"), driver);
		Keyword.clickElement("targetTextBox", driver);
		
		boolean targetVerify = Keyword.getValueOfAttribute("targetTextBox", "value", driver).equalsIgnoreCase(data.get("Target"));
		boolean indusVerify = Keyword.getValueOfAttribute("industryTextBox", "value", driver).equalsIgnoreCase(data.get("Industry"));
		boolean langVerify = Keyword.getValueOfAttribute("languageTextBox", "value", driver).equalsIgnoreCase(data.get("Language"));
		boolean custstatusVerify = Keyword.getValueOfAttribute("customerStatusTextBox", "value", driver).equalsIgnoreCase(data.get("Customer Status"));
		boolean resVerify = Keyword.getValueOfAttribute("residenceTextBox", "value", driver).equalsIgnoreCase(data.get("Residence"));
		boolean accofficerVerify = Keyword.getValueOfAttribute("accountOfficerTextBox", "value", driver).equalsIgnoreCase(data.get("Account Officer"));
		boolean nationVerify = Keyword.getValueOfAttribute("nationalityTextBox", "value", driver).equalsIgnoreCase(data.get("Nationality"));
		if (targetVerify && indusVerify && custstatusVerify && langVerify && resVerify && accofficerVerify
				&& nationVerify) {
			 Re.addStepLog("Entered the other required fields. <br>");
		} else {
			 Re.addStepLog("Entered the other required fields. <br>");
			 
			Keyword.sendText("targetTextBox", data.get("Target"), driver);
			Keyword.sendText("industryTextBox", data.get("Industry"), driver);
			Keyword.clickElement("languageTextBox", driver);
			Keyword.clearElement("languageTextBox", driver);
			Keyword.sendText("languageTextBox", data.get("Language"), driver);
			Keyword.sendText("customerStatusTextBox", data.get("Customer Status"), driver);
			Keyword.sendText("residenceTextBox", data.get("Residence"), driver);
			Keyword.sendText("accountOfficerTextBox", data.get("Account Officer"), driver);
			Keyword.sendText("nationalityTextBox", data.get("Nationality"), driver);
			 Re.addStepLog("Entered the other required fields. <br>");
		}
		
		 Re.addStepLog("Entered the other required fields.<br>");
		 Re.addScreenCaptureFromPath(captureScreenShot(driver, "Entered the other required fields."));
		
	}
	
	public static String generateMnemonic(String mnemonic){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddmss");
		String newMnemonic = (mnemonic + sdf.format(date).toString());
		return newMnemonic;
	}

	public static void physicaladdress() throws Throwable {
		Keyword.clickElement("gbStreetTextBox", driver);
		Keyword.sendText("gbStreetTextBox", data.get("GB Street"), driver);
		Keyword.sendText("gbAddressTextBox", data.get("GB Address"), driver);
		Keyword.sendText("gbTownCityTextBox", data.get("GB Town/City"), driver);
		Keyword.sendText("gbCountryTextBox", data.get("GB Country"), driver);
		Keyword.sendText("gbPostcodeTextBox", data.get("GB Post Code"), driver);
	
		
		 Re.addStepLog("Entered the physical address detail. <br>");
		
	}

	public static void iddoc() throws Throwable {
		Keyword.clickElement("idDocTab", driver);
		Keyword.sendText("legalIdTextBox", data.get("Legal ID.1"), driver);
		Keyword.selectFromDropDown("documentDropDown", data.get("Document Name.1"), driver);
		Keyword.sendText("nameOnIdTextBox", data.get("Name on ID.1"), driver);
		Keyword.sendText("issueAuthorityTextBox", data.get("Issue Authority.1"), driver);
		Keyword.sendText("issueDateTextBox", data.get("Issue Date.1"), driver);
		Keyword.sendText("expirationDateTextBox", data.get("Expiration Date.1"), driver);

		 Re.addStepLog("Entered the iddoc detail <br>");
		
	}
	
	//corporate customer
	public static void customerDataCorporate() throws Throwable{
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		Keyword.sendText("gbFullName", data.get("GB Full Name"), driver);
		Keyword.sendText("gbShortname", data.get("GB Short Name"), driver);
		Keyword.sendText("mnemonicTextBox", generateMnemonic(data.get("Mnemonic")), driver);
		Keyword.sendText("sectorTextBox", data.get("Sector"), driver);
		Keyword.clickElement("targetTextBox", driver);
		boolean targetVerify = Keyword.getValueOfAttribute("targetTextBox", "value", driver).equalsIgnoreCase(data.get("Target"));
		boolean indusVerify = Keyword.getValueOfAttribute("industryTextBox", "value", driver).equalsIgnoreCase(data.get("Industry"));
		boolean langVerify = Keyword.getValueOfAttribute("languageTextBox", "value", driver).equalsIgnoreCase(data.get("Language"));
		boolean custstatusVerify = Keyword.getValueOfAttribute("customerStatusTextBox", "value", driver).equalsIgnoreCase(data.get("Customer Status"));
		boolean accofficerVerify = Keyword.getValueOfAttribute("accountOfficerTextBox", "value", driver).equalsIgnoreCase(data.get("Account Officer"));
		if (targetVerify && indusVerify && custstatusVerify && langVerify &&  accofficerVerify
				) {
		//	test.log(LogStatus.PASS, "<b>Autofilled the  fields as per sector.<b>");
			 Re.addStepLog("Autofilled the  fields as per sector. <br>");
		}
		else{
			Keyword.sendText("targetTextBox", data.get("Target"), driver);
			Keyword.sendText("industryTextBox", data.get("Industry"), driver);
			Keyword.clickElement("languageTextBox", driver);
			Keyword.clearElement("languageTextBox", driver);
			Keyword.sendText("languageTextBox", data.get("Language"), driver);
			Keyword.sendText("customerStatusTextBox", data.get("Customer Status"), driver);
			Keyword.sendText("accountOfficerTextBox", data.get("Account Officer"), driver);
			//test.log(LogStatus.PASS, "<b>filled the  fields as per sector.<b>");
			 Re.addStepLog("filled the  fields as per sector. <br>");
		}
		
		Keyword.sendText("residenceTextBox", data.get("Residence"), driver);
		Keyword.sendText("nationalityTextBox", data.get("Nationality"), driver);
		Keyword.sendText("gbStreetTextBox", data.get("GB Street"), driver);
		Keyword.sendText("gbAddressTextBox", data.get("GB Address.1"), driver);
		Keyword.sendText("gbTownCityTextBox", data.get("GB Town/City"), driver);
		Keyword.sendText("gbCountryTextBox", data.get("GB Country"), driver);
			/*test.log(LogStatus.PASS, "<b>Entered the  required fields.<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "T24 Model Bank mandatory field")));*/
		 Re.addStepLog("Entered the  required fields <br>");
		// Re.addScreenCaptureFromPath(captureScreenShot(driver, "T24 Model Bank physical address details"));
		}
	
	//corporateselectedoperations
	public static void customerOptionalOpertions()throws Throwable{
		Keyword.selectFromDropDown("businessAuthnticationDropdown", "RECEIVED", driver);
		Keyword.selectFromDropDown("memorandumDropdown", "RECEIVED", driver);
		Keyword.clickElement("commitButton", driver);
	}
	
	//Corporatecustomeramendment
	public static void ammendCustomer(String testCaseId) throws Throwable{ 
		Keyword.pageHandle(driver);
		Keyword.clearElement("customerNoTextBox", driver);
		Keyword.sendText("customerNoTextBox",testCaseId, driver);
		Keyword.clickElement("findButton", driver);
		Keyword.clickElement("ammendIcon", driver);
		//pageHandle();
		driver.manage().window().maximize();
		Keyword.clearElement("industryTextBox", driver);
		Keyword.sendText("industryTextBox", data.get("Industry"), driver);
		Keyword.sendText("customerTypeTextbox", data.get("Customer Type"), driver);
		Keyword.sendText("dateOfIncorpTextbox", data.get("Date of Incorp"), driver);
		test.log(LogStatus.PASS, "<b>Committed the record using the option highlighted.<b>");
		Keyword.clickElement("acceptOverrideText", driver);
	}
	
	public static void customerDataPrivate() throws Throwable
	{
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		
		Keyword.selectFromDropDown("titleDropdown", data.get("Title"), driver);
		Keyword.sendText("givenNameTextBox", data.get("Given Name"), driver);
		Keyword.sendText("gbFullName", data.get("GB Full Name"), driver);
		Keyword.sendText("gbShortname", data.get("GB Short Name"), driver);
		Keyword.selectFromDropDown("titleDropdown", data.get("Title"), driver);
		String genderData = Keyword.getTextFromElement("genderRadioButton", driver);
		if (genderData.equalsIgnoreCase(data.get("Gender"))) {
			Keyword.clickElement("genderMaleRadioButton", driver);
		} else {
			Keyword.clickElement("genderFemaleRadioButton", driver);
		}
		Keyword.sendText("mnemonicTextBox", generateMnemonic(data.get("Mnemonic")), driver);
		Keyword.sendText("sectorTextBox", data.get("Sector"), driver);
		/////%%%%@@@@###
		
		Keyword.clickElement("targetTextBox", driver);
		boolean targetVerify = Keyword.getValueOfAttribute("targetTextBox", "value", driver).equalsIgnoreCase(data.get("Target"));
		boolean indusVerify = Keyword.getValueOfAttribute("industryTextBox", "value", driver).equalsIgnoreCase(data.get("Industry"));
		boolean langVerify = Keyword.getValueOfAttribute("languageTextBox", "value", driver).equalsIgnoreCase(data.get("Language"));
		boolean custstatusVerify = Keyword.getValueOfAttribute("customerStatusTextBox", "value", driver).equalsIgnoreCase(data.get("Customer Status"));
		boolean accofficerVerify = Keyword.getValueOfAttribute("accountOfficerTextBox", "value", driver).equalsIgnoreCase(data.get("Account Officer"));
		if (targetVerify && indusVerify && custstatusVerify && langVerify &&  accofficerVerify
				) {
			test.log(LogStatus.PASS, "<b>Autofilled the  fields as per sector.<b>");
		}
		else{
			Keyword.clearElement("targetTextBox", driver);
			Keyword.sendText("targetTextBox", data.get("Target"), driver);
			Keyword.clearElement("industryTextBox", driver);
			Keyword.sendText("industryTextBox", data.get("Industry"), driver);
			Keyword.clickElement("languageTextBox", driver);
			Keyword.clearElement("languageTextBox", driver);
			Keyword.sendText("languageTextBox", data.get("Language"), driver);
			Keyword.clearElement("customerStatusTextBox", driver);
			Keyword.sendText("customerStatusTextBox", data.get("Customer Status"), driver);
			Keyword.clearElement("accountOfficerTextBox", driver);
			Keyword.sendText("accountOfficerTextBox", data.get("Account Officer"), driver);
			test.log(LogStatus.PASS, "<b>filled the  fields as per sector.<b>");
		}
		
		Keyword.sendText("residenceTextBox", data.get("Residence"), driver);
		Keyword.sendText("nationalityTextBox", data.get("Nationality"), driver);
		Keyword.sendText("gbStreetTextBox", data.get("GB Street"), driver);
		Keyword.sendText("gbAddressTextBox", data.get("GB Address.1"), driver);
		Keyword.sendText("gbTownCityTextBox", data.get("GB Town/City"), driver);
		Keyword.sendText("gbCountryTextBox", data.get("GB Country"), driver);
			test.log(LogStatus.PASS, "<b>Entered the  required fields.<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "T24 Model Bank mandatory field")));
		
	}
	
	public static void PrivateSecurity() throws Throwable
	{
		
		Keyword.pageHandle(driver);
		driver.manage().window().maximize();
		
		Keyword.clickElement("PVT_MultiValuebutton", driver);
		Keyword.selectFromDropDown("PVT_CustomerType2", "Customer", driver);
		Keyword.clickElement("validatebutton", driver);
		Keyword.clickElement("commitButton", driver);
		Keyword.selectFromDropDown("PVT_signedMandateDropDown", "YES", driver);
		Keyword.selectFromDropDown("PVT_custodyDropDown", "YES", driver);
		Keyword.selectFromDropDown("PVT_DXTradingDropDown", "YES", driver);
		Keyword.clickElement("commitButton", driver);
		
	}

	
}
