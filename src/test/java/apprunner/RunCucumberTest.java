package apprunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


// mvn clean test -DDefaultENV=DEV1 -Dcucumber.filter.tags=@JEBRil -Dtest.suite.filename=TestNG_BDD_EIS_testing.xml
// BELOW IS WORKING
// mvn clean test -DDefaultENV=DEV1 -Dcucumber.filter.tags=@JEBRIL
@CucumberOptions(
		features = {"src/test/resources/"}, 
		glue={"com/yourdomain/stepDef/steps", "com/yourdomain/stepDef/support"},
		plugin = {"pretty", "json:target/cucumber-reports/cucumber.json", "html:target/cucumber-reports"}, 
		tags = {"@JEBRIL"},
		monochrome=true,
		dryRun = false
		)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}

	