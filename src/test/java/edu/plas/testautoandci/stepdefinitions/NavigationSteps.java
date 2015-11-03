package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Given;
import edu.plas.testautoandci.driver.Driver;

public class NavigationSteps {
    @Given("^I navigate to (.*)$")
    public void iNavigateTo(String site) {
        Driver.getWebDriver().get(site);
    }
}
