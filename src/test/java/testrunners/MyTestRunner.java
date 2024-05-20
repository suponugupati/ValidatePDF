package testrunners;

import helpers.CombineXunitReports;
import io.cucumber.testng.CucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.List;


@CucumberOptions(
		features = {"src/test/resources/features"},
		glue = {"stepDefinition"},
		tags = "@T4",
	//	tags = "@T1 or @T2",
		dryRun = false, monochrome = true
		//	tags = "@T1",

)
public class MyTestRunner extends AbstractTestNGCucumberTests{


	@Override

	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	@AfterMethod
	public void afterMethod() {
		CombineXunitReports combiner = new CombineXunitReports();
		String userHome = System.getProperty("user.home");
		String path = userHome + File.separator + "Downloads" + File.separator + "Validate PDF" + File.separator + "results" + File.separator + "xml";
		List<File> xmlFiles = combiner.getAllXmlFiles(path);

		// Process the XML files as needed
		if (xmlFiles.isEmpty()) {
			System.out.println("No XML files found in directory: " + path);
		} else {
			System.out.println("Found " + xmlFiles.size() + " XML files in directory: " + path);
			// Add further processing of the XML files here
		}
	}
}
