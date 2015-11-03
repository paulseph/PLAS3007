package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.plas.testautoandci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Then("the Google logo is displayed")
    public void theGoogleLogoIsDisplayed() {
        WebElement googleLogo = Driver.getWebDriver().findElement(By.id("hplogo"));
        assertTrue(googleLogo.isDisplayed());
    }

    @Then("the I'm Feeling Lucky button is displayed")
    public void theImFeelingLuckyButtonIsDisplayed() throws InterruptedException {
        WebElement iMFeelingLuckyButton = Driver.getWebDriver().findElement(By.name("btnI"));
        assertTrue(iMFeelingLuckyButton.isDisplayed());
        assertEquals("I'm Feeling Lucky", iMFeelingLuckyButton.getAttribute("value"));
    }
}

