package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.cucumber.listener.Reporter;
import com.mysql.jdbc.Statement;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.testng.TestNGCucumberRunner;

public class BaseClass {

	protected static WebDriver driver;
	protected TestNGCucumberRunner testNGCucumberRunner;
	public static Reporter Re;
	protected WebDriverWait waitDriver = null;
	protected static ArrayList<String> caseslist;
	protected static HashMap<String, String> data; 
	public static HashMap<String, String> paraLocator;
	public static HashMap<String, String> paraSelector;
	public static String browser = "";
	public static String baseUrl = "";
	public static String adminUsername = "";
	public static String adminPassword = "";
	public static String username = "";
	public static String password = "";
	public static String timeout = "";
	public static String myDriver = "";
	public static String myUrl = "";
	public static String DBUNAME = "";
	public static String DBPWD = "";
	public static int statusCode;
	public static String className = "";
	private static String OutputValue = null;
	//public static ExtentReports extent;
	public static ExtentTest test;
	protected String filePath;
	static String imagesDirectory = "";
	static String relativePathforImage = "";
	public static String reportConfigPath;
	public static String reportConfigPath1;

	public void getBrowser() throws Throwable {

		if (browser.equalsIgnoreCase("chrome")) {
		//	extent = ExtentManager.getReporter(filePath, baseUrl);
			System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(baseUrl);
			driver.manage().window().maximize();
		}
	}

	public static By getWebElement(String parameter) {
		By loc;
		switch (paraSelector.get(parameter)) {
		case "ID":
			loc = By.id(paraLocator.get(parameter));
			break;
		case "XPATH":
			loc = By.xpath(paraLocator.get(parameter));
			break;
		case "CLASSNAME":
			loc = By.className(paraLocator.get(parameter));
			break;
		case "CSSSELECTOR":
			loc = By.cssSelector(paraLocator.get(parameter));
			break;
		case "LINKTEXT":
			loc = By.linkText(paraLocator.get(parameter));
			break;
		case "PARTIALLINKTEXT":
			loc = By.partialLinkText(paraLocator.get(parameter));
			break;
		case "NAME":
			loc = By.name(paraLocator.get(parameter));
			break;
		case "TAGNAME":
			loc = By.tagName(paraLocator.get(parameter));
			break;
		default:
			System.out.println("Locator not found");
			return null;
		}

		return loc;
	}

	public WebDriver SwitchToFrame() {
		driver.switchTo().frame(0);
		return driver;
	}

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "configFile" })
	public void beforeSuite(String configFile) throws Throwable {
		Properties p = new Properties();
		FileInputStream readconfig = new FileInputStream(configFile);
		p.load(readconfig);
		browser = p.getProperty("browser");
		baseUrl = p.getProperty("baseUrl");
		adminUsername = p.getProperty("adminUsername");
		adminPassword = p.getProperty("adminPassword");
		username = p.getProperty("Username");
		password = p.getProperty("Password");
		timeout = p.getProperty("timeout");
		myDriver = p.getProperty("myDriver");
		myUrl = p.getProperty("myUrl");
		DBUNAME = p.getProperty("DBUNAME");
		DBPWD = p.getProperty("DBPWD");
		
		String workingDir = System.getProperty("user.dir") + "\\target\\cucumber-reports";
		filePath = workingDir + "/report.html";
	//	extent = ExtentManager.getReporter(filePath, baseUrl);
		reportConfigPath = p.getProperty("reportConfigPath");
		reportConfigPath1 = System.getProperty("user.dir") + reportConfigPath;
		
		
		if (getAppStatus() != 200) {
			try {
				System.out.println("Server is down");
				throw new ApplicationException("Server down with status: " + getAppStatus());
			} catch (ApplicationException e) {
				ErrorHandler.handle(e, driver);
			}

		}
	}

	@AfterSuite
	public void afterSuite() throws InterruptedException {
		driver.quit();
		//extent.close();
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver.get("C:\\Users\\T24R17\\Desktop\\Temenos_BDD_Kajol\\Temenos_BDD_Vijay\\target\\cucumber-reports\\report.html");
		driver.get("file://" + filePath); 
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
	//	extent = ExtentManager.getReporter(filePath, baseUrl);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		driver.quit();
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test failed");
			String screenshot_path = captureScreenShot(driver, result.getName());
			String image = test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL, image);
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Test passed");
		}
	}

	public static String captureScreenShot(WebDriver driver, String screenshotName) {
		try {
			Calendar calander = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yy_hh_mm_ss");
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			String dest = imagesDirectory + screenshotName + "-" + formater.format(calander.getTime()) + ".png";
			File destination = new File(dest);
			FileUtils.copyFile(src, destination);
			return dest;
		}

		catch (Exception e) {
			System.out.println("Exception while taking screenshot" + e.getMessage());
			return e.getMessage();
		}
	}

	public static void createDirectory(String classname) {

		classname = classname.substring(10);
		imagesDirectory = System.getProperty("user.dir") + "/extentReports" + "/" + classname + "/";
		relativePathforImage = "./" + classname + "/";
		File file = new File(imagesDirectory);
		if (!file.exists()) {
			file.mkdir();
		} else {
			try {
				FileUtils.cleanDirectory(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static int getAppStatus() throws IOException {
		URL url = null;
		try {
			url = new URL(baseUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		statusCode = http.getResponseCode();
		return statusCode;
	}

	public ExtentTest getClassName(String classNmae) {
		className = this.getClass().getName();
		createDirectory(className);
	//	test = extent.startTest(classNmae);
		return test;
	}

	// ReadDataFromExcel
	public static HashMap<String, String> ReadData(String Sheetname, String testId)
			throws ClassNotFoundException, SQLException, IOException {
		System.out.println("read excel called____________");
		HashMap<String, String> datamap = new HashMap<String, String>();
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, DBUNAME, DBPWD);
		String query = "SELECT * FROM test_data WHERE scenario_Name='" + Sheetname + "' AND TestCase_ID='" + testId
				+ "';";
		Statement stmt = (Statement) conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			datamap.put(rs.getString(3), rs.getString(4));
		}
		System.out.println("***********" + datamap.size() + "--------" + datamap);
		conn.close();
		return datamap;
	}
	public static ArrayList<String> readCases( String Sheetname)
			throws IOException, ClassNotFoundException, SQLException {
		ArrayList<String> addcases = new ArrayList<String>();
		try {
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, DBUNAME, DBPWD);
			String query = "SELECT * FROM test_data WHERE scenario_Name='" + Sheetname
					+ "' AND INPUTField='Execution_Flag' AND INPUTData='TRUE';";
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				addcases.add(rs.getString(2).toString());
			}
			conn.close();
			if (addcases.isEmpty()) {
				System.out.println("*** No case in current Suit Set to \"TRUE\" ***");
			}
		} catch (Exception e) {
			System.out.println("*** Error in reading cases from suite.xlsx ***");
			e.printStackTrace();
		}

		return addcases;
	}
	
	// datapprovider
		public static Object[][] dataTest(ArrayList<String> caselist) {
			String[][] array = new String[caseslist.size()][1];
			int i = 0;
			for (int j = 0; j < caseslist.size(); j++) {
				array[j][i] = caseslist.get(j);
			}
			Object[][] tdata = (Object[][]) array;
			System.out.println(tdata.toString());
			return tdata;
		}
		public static void writeOutputinDB(String Sheetname, String TestCase_id, String Field_Name, String Field_Value)
				throws IOException, ClassNotFoundException, SQLException {
			// Vijay_code
			// System.out.println("Vijay_DBupdate");
			int RC = 0;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, DBUNAME, DBPWD);
			String query = "SELECT * from output_master WHERE Scenario_Name='" + Sheetname + "'AND TestCase_ID='"
					+ TestCase_id + "'AND Field_Name='" + Field_Name + "';";
			Statement stmt = (Statement) conn.createStatement();
			Statement stmtUpdate = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String Update_query = "UPDATE output_master SET Field_Value='" + Field_Value + "' WHERE Scenario_Name='"
					+ Sheetname + "'AND TestCase_ID='" + TestCase_id + "'AND Field_Name='" + Field_Name + "';";

			while (rs.next()) {
				int resultRC = stmtUpdate.executeUpdate(Update_query);
				System.out.println("Number of records updated is= " + resultRC);
				RC++;
				System.out.println("OutputRecord_Updated");
			}
			if (RC == 0) {
				query = " INSERT INTO output_master (Scenario_Name, TestCase_ID, Field_Name,Field_Value)"
						+ " values (?,?,?,?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, Sheetname);
				preparedStmt.setString(2, TestCase_id);
				preparedStmt.setString(3, Field_Name);
				preparedStmt.setString(4, Field_Value);

				try {
					preparedStmt.execute();
					System.out.println("OutputRecord_Inserted");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			conn.close();
		}
		public static String getOutputFromDB(String Sheetname, String TestCase_id, String Field_Name)
				throws IOException, ClassNotFoundException, SQLException {
			// Vijay_code
			// System.out.println("Vijay_DBupdate");
			int RC = 0;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, DBUNAME, DBPWD);
			String query = "SELECT Field_Value from output_master WHERE Scenario_Name='" + Sheetname + "'AND TestCase_ID='"
					+ TestCase_id + "'AND Field_Name='" + Field_Name + "';";
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				OutputValue = rs.getString(1);
				RC++;
				System.out.println("RecordRetrived");
			}
			if (RC == 0) {
				System.out.println("NoRecord_found");
			}
			conn.close();
			return OutputValue;

		}
		// ReadOR
		public static void ReadOR(String Sheetname) throws ClassNotFoundException, SQLException, IOException {
			System.out.println("read OR called");
			paraSelector = new HashMap<String, String>();
			paraLocator = new HashMap<String, String>();
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, DBUNAME, DBPWD);
			String query = "SELECT * FROM or_master WHERE Module_Name='" + Sheetname + "';";
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				paraSelector.put(rs.getString(2), rs.getString(3));
				paraLocator.put(rs.getString(2), rs.getString(4));
			}
			conn.close();
		}
		//Need to Check with Pankaj
		public static void sleep(double seconds) {
			try {
				Thread.sleep(Double.valueOf(seconds * 1000).intValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void waitTillElementisInvisible(WebElement element) {

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.invisibilityOf(element));
		}

		public void waitTillElementVisible(WebElement element) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(element));
			} catch (Exception e) {
				System.out.println(element + "not visible");
			}
		}

		public void waitTillElementVisibleAndClick(WebElement element) {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
		}

		public void waitTillElementClickable(WebElement element) {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}

		public void javaScriptClick(WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}

		public void verticalScrollingDown() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			for (int i = 0; i < 2; i++) {
				js.executeScript("window.scrollBy(0,250)");
			}
		}

		public void verticalScrollingUp() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			for (int i = 0; i < 2; i++) {
				js.executeScript("window.scrollBy(0,-250)");
			}
		}

		public void horizontalScrollingToRight() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			for (int i = 0; i < 2; i++) {
				js.executeScript("window.scrollBy(200,0)");
			}
		}

		public void horizontalScrollingToLeft() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			for (int i = 0; i < 2; i++) {
				js.executeScript("window.scrollBy(-200,0)");
			}
		}
}
