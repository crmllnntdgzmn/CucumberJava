package com.qa.main;

import org.junit.runner.Result;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="classpath:Features",
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
            openHtmlReport();
        }
    }
	
	public static void openHtmlReport() {
		String htmlReportPath = System.getProperty("user.dir") + "/target/HtmlReports/report.html";
		
		File htmlReport = new File(htmlReportPath);
		if (htmlReport.exists()) {
			try {
				Desktop.getDesktop().browse(htmlReport.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Unable to find HTML report");
		}
	}
}
