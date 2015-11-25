package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.plas.testautoandci.helper.DriverFrameAndAlertHelper;
import edu.plas.testautoandci.helper.DriverWindowHelper;
import edu.plas.testautoandci.pageobjectmodels.CustomSitePage;
import edu.plas.testautoandci.pageobjectmodels.GoogleSearchPage;

import static org.junit.Assert.*;

public class CustomSiteSteps {
    private CustomSitePage customSitePage = new CustomSitePage();

    @When("^I select the '(.*)' type of Parking by '(?i)(text|value)'$")
    public void iSelectTheTypeOfParkingBy(String parking, String selectBy) {
        if ("text".equals(selectBy.toLowerCase())) {
            customSitePage.selectParkingDropdownByText(parking);
        } else if ("value".equals(selectBy.toLowerCase())) {
            customSitePage.selectParkingDropdownByValue(parking);
        }
    }

    @Then("^the correct '(.*)' Parking is displayed along with its '(.*)' value$")
    public void theCorrectParkingIsDisplayed(String parkingText, String parkingValue) {
        String expectedText = "Selected option: " + parkingText + " (" + parkingValue + ")";
        String actualParkingText = customSitePage.getParkingSelectionText();
        assertEquals(expectedText, actualParkingText);
    }



    @Then("^the text that appears upon hover is not shown$")
    public void theTextThatAppearsUponHoverIsNotShown() {
        assertFalse(customSitePage.isHoverTextDisplayed());
        assertEquals("", customSitePage.getHoverText());
    }

    @When("^I hover over the text that triggers the hovered text$")
    public void iHoverOverTheTextThatTriggersTheHoveredText() {
        customSitePage.hoverOverTextTrigger();
    }

    @Then("^the text that appears upon hover is shown$")
    public void theTextThatAppearsUponHoverIsShown() {
        assertTrue(customSitePage.isHoverTextDisplayed());
        String expectedText = "I only show when you hover on the text above me! :o";
        assertEquals(expectedText, customSitePage.getHoverText());
    }



    @When("^I click on the Click Me to Google! link$")
    public void iClickOnTheClickMeToGoogleLink() {
        customSitePage.clickOnGoogleLink();
    }

    @Then("^the Google homepage is opened$")
    public void theGoogleHomepageIsOpened() {
        DriverWindowHelper.switchToWindow();

        GoogleSearchPage googleSearchPage = new GoogleSearchPage();
        assertTrue(googleSearchPage.isGoogleSearchTextBarDisplayed());
        assertTrue(googleSearchPage.isGoogleSearchButtonDisplayed());
        assertTrue(googleSearchPage.isGoogleSearchImFeelingLuckyButtonDisplayed());

        assertEquals("Google", DriverWindowHelper.getWindowTitle());
    }

    @When("^I close the Google homepage$")
    public void iCloseTheGoogleHomepage() {
        DriverWindowHelper.closeCurrentWindow();
        DriverWindowHelper.switchToMainWindow();
    }

    @Then("^the Click Me to Google! link is underlined$")
    public void theClickMeToGoogleLinkIsUnderlined() {
        assertEquals("underline", customSitePage.getGoogleLinkCssValue("text-decoration"));
    }



    @Then("^the text '(.*)' appears in the first iframe$")
    public void theTextAppearsInTheFirstIframe(String expectedText) {
        DriverFrameAndAlertHelper.switchToFrame("firstFrame");
        assertEquals(expectedText, customSitePage.getTextInFirstIFrame());
    }

    @Then("^the text '(.*)' appears in the second iframe$")
    public void theTextAppearsInTheSecondIframe(String expectedText) {
        DriverFrameAndAlertHelper.switchToFrame(customSitePage.getSecondIFrame());
        assertEquals(expectedText, customSitePage.getTextInSecondIFrame());
    }

    @Then("^there are (\\d+) (.*) tags in the main document$")
    public void thereAreTagsInTheMainDocument(int numberOfTags, String tag) {
        DriverFrameAndAlertHelper.switchToTopFrame();
        assertEquals(numberOfTags, customSitePage.getNumberOfWebElementsByTagName(tag));
    }



    @When("^I click on the Press me, press me! button$")
    public void iClickOnThePressMePressMeButton() {
        customSitePage.clickAlertButton();
    }

    @When("^I '(?i)(accept|dismiss)' the alert$")
    public void iTheAlert(String action) {
        if ("accept".equals(action.toLowerCase())) {
            DriverFrameAndAlertHelper.acceptAlert();
        } else if ("dismiss".equals(action.toLowerCase())) {
            DriverFrameAndAlertHelper.dismissAlert();
        }
    }

    @Then("^the '(.*)' alert result text is displayed$")
    public void theAlertResultTextIsDisplayed(String expectedAlertResult) {
        assertEquals(expectedAlertResult, customSitePage.getAlertSelectionText());
    }



    @When("I drag the image of the dog and drop into the empty box")
    public void iDragTheImageOfTheDogAndDropIntoTheEmptyBox() {
        customSitePage.dragAndDropImageOfDogIntoEmptyBox();
    }

    @Then("the image of the dog is now inside the box")
    public void theImageOfTheDogIsNowInsideTheBox() {
        assertTrue(customSitePage.isImageOfDogDisplayedInsideTheBox());
    }
}