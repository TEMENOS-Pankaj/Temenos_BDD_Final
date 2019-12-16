package LoginandHome_Page;

import utility.BaseClass;
import utility.Keyword;

public class Home_Page extends BaseClass {
	public static void Homepage(String command) throws Throwable {
		
		Keyword.handleFrame(0, driver);
		Keyword.clearElement("commandLineTextbox", driver);
		Keyword.sendText("commandLineTextbox", command, driver);
		String loginpagetitle = driver.getTitle();
		if (loginpagetitle.equalsIgnoreCase("T24 - Model Bank")) {
			/*test.log(LogStatus.PASS, "<b>Entered the command in text box.<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "T24 Model Bank")));*/
			 Re.addStepLog("Entered the command in text box.");
		} else {
			/*test.log(LogStatus.FAIL, "<b>Unable to Enter the command in text box.<b>"
					+ test.addScreenCapture(captureScreenShot(driver, "T24 Model Bank")));*/
			Re.addStepLog("Unable to Enter the command in text box.");
		}
		Keyword.clickElement("commandLineCommitButton", driver);

	}
}
