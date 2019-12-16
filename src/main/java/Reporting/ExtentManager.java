package Reporting;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager
{
	private static ExtentReports extent;	

	public synchronized static ExtentReports getReporter(String filePath,String testURL) {
		if (extent == null) {
			extent = new ExtentReports(filePath, true);
			
			extent
			.addSystemInfo("Host Name", "Temenos")
			.addSystemInfo("Environment", "QA")
			.addSystemInfo("TestURL", testURL);
		}

		return extent;
	}
}