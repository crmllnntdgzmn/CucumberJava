package com.qa.main;

import org.junit.runner.Result;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="classpath:Features", // changed from "src/test/resources/Features"
        glue= {"StepDefinitions"},
        plugin = {"pretty", "html:target/HtmlReports/report.html"}
        )

public class TestRunner {

	public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestRunner.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful()) {
            System.out.println("All tests finished successfully...");
        }
    }
}
