package MyRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import utility.BaseClass;

@CucumberOptions(features = "Features", glue = { "stepDefinitions" },
		//tags = {"~@Ignore"},
		tags = {  "@TakeLoanFunction"},
		plugin = {
				"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html","json:target/cucumber.json","junit:target/cucumber-reports/Cucumber.xml" }, monochrome = true)

public class TakeLoanRunner extends BaseClass {

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		Re.loadXMLConfig(reportConfigPath1);
		Re.setSystemInfo("User Name", System.getProperty("user.name"));
		Re.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Re.setSystemInfo("Environment ", "SIT");
		Re.setSystemInfo("Machine", "Windows 10" + " - 64 Bit");
		Re.setSystemInfo("Selenium", "3.7.0");
		Re.setSystemInfo("Maven", "3.5.2");
		Re.setSystemInfo("Java Version", "1.8.0_151");
		
		testNGCucumberRunner.finish();
		//	extent.endTest(test);
		driver.quit();
	}

}