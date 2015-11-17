package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.plas.testautoandci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GoogleSteps {
    @When("^I search for '(.*)' on Google Search$")
    public void iSearchForSearchText(String searchText) {
        Driver.getWebDriver().findElement(By.id("lst-ib")).sendKeys(searchText);
        Driver.getWebDriver().findElement(By.name("btnG")).click();
    }

    @Then("^the Google Calculator component is displayed$")
    public void theGoogleCalculatorComponentIsDisplayed() {
        WebElement googleCalculator = Driver.getWebDriver().findElement(By.id("cwmcwd"));
        assertTrue("Google Calculator should be displayed but is not!", googleCalculator.isDisplayed());
    }

    @Then("^the result on Google Calculator is '(.*)'$")
    public void theResultOnGoogleCalendar(String expectedCalculationResult) {
        String actualCalculationResult = Driver.getWebDriver().findElement(By.id("cwos")).getText();
        assertEquals(expectedCalculationResult, actualCalculationResult);
    }

    @When("^I search for '(.*)' on Google Images$")
    public void iSearchForTextOnGoogleImages(String searchText) {
        Driver.getWebDriver().findElement(By.id("lst-ib")).sendKeys(searchText);
        Driver.getWebDriver().findElement(By.name("btnG")).click();
    }

    @Then("^there are '(.*)' images$")
    public void thereAreImages(String occurences) {
        List<WebElement> images = Driver.getWebDriver().findElements(By.cssSelector("#rg_s .rg_di.rg_el.ivg-i"));

        // XPath
        // List<WebElement> images = Driver.getWebDriver().findElements(By.xpath("//*[@id='rg_s']//*[@class='rg_di rg_el ivg-i']"));

        if ("10 or more".equals(occurences)) {
            assertTrue("There should have been 10 or more images, but there where " + images.size(), images.size() >= 10);
        }
    }

    @Then("^there are '(.*)' images that contain '(.*)'$")
    public void thereAreImagesThatContain(String occurences, String expectedSearchResult) {
    }

    @Then("^the Google stats tab is displayed$")
    public void theGoogleStatsTabIsDisplated() {
        WebElement element = Driver.getWebDriver().findElement(By.id("resultStats"));
        String statsText = element.getText();
        assertTrue(statsText.contains("About"));
        assertTrue(statsText.contains("results"));
        assertTrue(statsText.contains("seconds"));
    }

    @Then("^the Google search results are displayed$")
    public void theGoogleSearchResultsAreDisplayed() {
        List<WebElement> searchResults = Driver.getWebDriver().findElements(By.cssSelector("#search .g"));

        // XPath
        // List<WebElement> searchResults = Driver.getWebDriver().findElements(By.xpath("//*[@id='search']//*[@class='g']"));

        assertTrue("Search results should have been shown", searchResults.size() > 1);
    }

    @Then("^the flag of '(.*)' is displayed$")
    public void theFlagOfCountryIsDisplayed(String country) {
        WebElement flag = Driver.getWebDriver().findElement(By.id("media_result_group")).findElement(By.className("bicc")).findElement(By.tagName("img"));

        // CSS
        // WebElement flag = Driver.getWebDriver().findElement(By.cssSelector("#media_result_group .bicc img"));

        // XPath
        // WebElement flag = Driver.getWebDriver().findElement(By.xpath("//*[@id='media_result_group']//*[@class='bicc']//img"));

        assertTrue("Flag is not displayed", flag.isDisplayed());
        assertTrue("Flag is not displayed", flag.getAttribute("title").toLowerCase().contains(country.toLowerCase()));
    }

    @Then("^the Google logo is displayed$")
    public void theGoogleLogoIsDisplayed() {
        WebElement googleLogo = Driver.getWebDriver().findElement(By.id("hplogo"));
        assertTrue(googleLogo.isDisplayed());
    }

    @Then("^the I'm Feeling Lucky button is displayed$")
    public void theImFeelingLuckyButtonIsDisplayed() {
        WebElement iMFeelingLuckyButton = Driver.getWebDriver().findElement(By.name("btnI"));
        assertTrue(iMFeelingLuckyButton.isDisplayed());
        assertEquals("I'm Feeling Lucky", iMFeelingLuckyButton.getAttribute("value"));
    }

    @When("^I hover over the I'm Feeling Lucky button$")
    public void iHoverOverTheIMFeelingLuckyButton() {
        // Hover over the "I'm Feeling Lucky" button
        WebElement iMFeelingLuckyButton = Driver.getWebDriver().findElement(By.name("btnI"));
        Actions action = new Actions(Driver.getWebDriver());
        action.moveToElement(iMFeelingLuckyButton).build().perform();
    }

    @Then("^a different text is shown in the I'm Feeling Lucky button$")
    public void aDifferentTextIsShownInTheIMFeelingLuckyButton() throws InterruptedException {
        // Wait for 2 seconds so that button text changes
        Thread.sleep(2000);

        String[] iMFeelingLuckyButtonPossibleText = new String[] {"I'm Feeling Doodley", "I'm Feeling Artistic",
                "I'm Feeling Hungry", "I'm Feeling Puzzled", "I'm Feeling Trendy", "I'm Feeling Stellar",
                "I'm Feeling Playful", "I'm Feeling Wonderful", "I'm Feeling Generous", "I'm Feeling Curious"};
        String iMFeelingLuckyButtonText = Driver.getWebDriver().findElement(By.cssSelector("[name='btnI'] + div")).getAttribute("aria-label");
        iMFeelingLuckyButtonText = "to fail";
        assertTrue("I'm Feeling Lucky random text is wrong! - " + iMFeelingLuckyButtonText,
                Arrays.asList(iMFeelingLuckyButtonPossibleText).contains(iMFeelingLuckyButtonText));
    }
}