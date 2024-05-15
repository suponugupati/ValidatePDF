package helpers;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;



public class ExecutionHelper {
	
	public static ExtentReports extent;
	public static Scenario scenario = null;
	public static ThreadLocal<ExtentTest> logger = new ThreadLocal<ExtentTest>();

    static {
    	try {
			extentReportInitialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public static void extentReportInitialize() throws Exception {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
			Date date = new Date();
			String extentReportLocation = System.getProperty("user.dir")+"/ExtentReports/ExtentReport"+dateFormat.format(date)+".html";
			System.out.println("extentReport" + extentReportLocation);
			System.out.println("SystemDirectory"+System.getProperty("user.dir"));
			extent = new ExtentReports(extentReportLocation, true);
			
		} catch (Exception exception) {
		}
	}
	
	 public static void startTest(String scenarioID) throws Throwable{
		 try {
			 setLogger(extent.startTest(scenarioID));
		 } catch (Exception exception) {
                     exception.printStackTrace();
         }
     }
	 
	 public static void setLogger(ExtentTest test){
			logger.set(test);
		}
	 public static ExtentTest getLogger(){
			return logger.get();
		}
	     
	    public static String takeScreenshot(WebDriver driver) throws IOException {
	    	File source = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
	    	String destination= System.getProperty("user.dir")+"/Screenshot/"+"sfdcscreenshot_"+System.currentTimeMillis()+".png";
	    	File finalDestination = new File(destination);
	    	FileUtils.copyFile(source,finalDestination);
	    	return destination;
	    	}  
	    

public static void tearDown(ExtentTest extentTest, Scenario scenario) {
	if (extentTest != null) {
		if (scenario.isFailed()) {
			// Take a screenshot...
			final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
			// embed it in the ExtentReport
			extentTest.log(LogStatus.FAIL, "Scenario Failed",
					extentTest.addScreenCapture("data:image/png;base64," + Base64.getEncoder().encodeToString(screenshot)));
		} else {
			extentTest.log(LogStatus.PASS, "Scenario Passed");
		}
	}
}
	public static void extentReportFlush() throws Exception {
		try {
			extent.endTest(getLogger());
			extent.flush();
		} catch (Exception exception) {
		}
	} 

}
