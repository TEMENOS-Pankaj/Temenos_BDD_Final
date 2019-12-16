package stepDefinitions;

import LoginandHome_Page.LoginAndLogOut_Page;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import utility.BaseClass;

public class LoginStepDefinition extends BaseClass{
	LoginAndLogOut_Page loginPage;
	@Given("^user logs into application$")
	public void user_logs_into_application() throws Throwable {
		getBrowser();
		Re.addStepLog("Browser Opened & Login Page Loaded<br>");
		loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(username,password); 
		//driver.switchTo().alert().accept(); 
			 

	}
	
	@Given("^user logs into the application as a admin$")
	public void user_logs_into_application_with_admin() throws Throwable {
		getBrowser();
		Re.addStepLog("Browser Opened & Login Page Loaded<br>");
		loginPage = new LoginAndLogOut_Page(driver);
		loginPage.login(adminUsername,adminPassword);
		//driver.switchTo().alert().accept();
	}
	
	@And("^logout from the App$")
	public void logOutApp() throws Throwable {
		loginPage.logOut(driver);
		driver.quit();

	}
}
