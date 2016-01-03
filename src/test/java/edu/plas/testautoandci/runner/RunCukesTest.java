package edu.plas.testautoandci.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/edu/plas/testautoandci/features",
        tags = "@paulseph",
        glue = "edu.plas.testautoandci",
        plugin = {"pretty", "junit:reports/junit.xml", "json:reports/cucumber.json", "html:reports/html-reports"},
        monochrome = true)
public class RunCukesTest {
}
