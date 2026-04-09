package cucumber_GitHub.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
	    features = "src/test/java/cucumber_GitHub/feature/GitHub.feature",
	    glue = "cucumber_GitHub/stepDefinition",
	    plugin = {"pretty","html:target/report.html"},
	    monochrome = true)

public class GitRunner extends AbstractTestNGCucumberTests {

}
