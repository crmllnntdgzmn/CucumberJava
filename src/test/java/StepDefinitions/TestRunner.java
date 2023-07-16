package StepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/Features", // points to Features folder
		glue= {"StepDefinitions"}, // points to StepDefinitions folder
		plugin = {"pretty", "html:target/HtmlReports/report.html"} // Generating HTML Report on target location
		) 

public class TestRunner {

}
