package testrunners;

import io.cucumber.testng.CucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;



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
}
