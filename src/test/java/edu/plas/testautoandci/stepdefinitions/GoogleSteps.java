package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GoogleSteps {
    @When("^I search for '(.*)' on Google Search$")
    public void iSearchForSearchText(String searchText) {
    }

    @Then("^the Google Calculator component is displayed$")
    public void theGoogleCalculatorComponentIsDisplayed() {
    }

    @Then("^the result on Google Calculator is '(.*)'$")
    public void theResultOnGoogleCalendar(String calculationResult) {
    }

    @When("^I search for '(.*)' on Google Images$")
    public void iSearchForTextOnGoogleImages(String searchText) {
    }

    @Then("^there are '(.*)' images that contain '(.*)'$")
    public void iSearchForTextOnGoogleImages(String occurences, String expectedSearchResult) {
    }

    @Then("^the Google stats tab is displayed$")
    public void theGoogleStatsTabIsDisplated() {
    }

    @Then("^the Google search results are displayed$")
    public void theGoogleSearchResultsAreDisplayed() {
    }

    @Then("the flag of '(.*)' is displayed$")
    public void theFlagOfCountryIsDisplayed(String country) {
    }
}

