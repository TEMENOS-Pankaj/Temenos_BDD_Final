package LoginandHome_Page;

import org.openqa.selenium.WebDriver;

import utility.BaseClass;
import utility.Keyword;

public class LoginAndLogOut_Page extends BaseClass {
	WebDriver driver;
	
	public LoginAndLogOut_Page(WebDriver driver){
		this.driver=driver;
	}
	
	public void login(String user, String password) throws Throwable {
		Keyword.sendText("UserName_Textbox",user, driver);
		Keyword.sendText("Password_Textbox", password, driver);
		Keyword.clickElement("signInButton", driver);
	}
	
	
	public void logOut(WebDriver driver) throws Throwable {
		Keyword.pageHandleMainPage(driver);
		Keyword.handleFrame(0, driver);
		Keyword.clickElement("signOffButton", driver);
		
	}
	

}
