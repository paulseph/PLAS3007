package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.plas.testautoandci.driver.SikuliDriver;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class SikuliSteps {
    private Screen sikuliDriver = SikuliDriver.getSikuliDriver();

    @Given("^I use Sikuli to navigate to (.*)$")
    public void iNavigateToOn(String url) throws FindFailed, InterruptedException {
        Thread.sleep(1000);
        App.open("Firefox");

        String newTabImage = SikuliDriver.SIKULI_IMAGES_PATH + "firefox_new_tab.png";
        sikuliDriver.wait(newTabImage, 60);
        sikuliDriver.click(newTabImage);

        String addressBarImage = SikuliDriver.SIKULI_IMAGES_PATH + "firefox_address_bar.png";
        sikuliDriver.wait(addressBarImage, 15);
        sikuliDriver.type(addressBarImage, url + Key.ENTER);
    }

    @When("^I click on the '(.*)' tab using Sikuli$")
    public void iClickOnTheTabUsingSikuli(String tab) throws FindFailed {
        switch (tab) {
            case "Documentation":
                sikuliDriver.wait(SikuliDriver.SIKULI_IMAGES_PATH + "documentation_tab.png", 15);
                sikuliDriver.click(SikuliDriver.SIKULI_IMAGES_PATH + "documentation_tab.png");
                break;
            default:
                throw new IllegalArgumentException("Tab handling not found!");
        }
    }

    @When("^I click on the '(.*)' link using Sikuli$")
    public void iClickOnTheLinkUsingSikuli(String link) throws FindFailed {
        switch (link) {
            case "table of contents":
                sikuliDriver.wait(SikuliDriver.SIKULI_IMAGES_PATH + "table_of_contents.png", 15);
                sikuliDriver.click(SikuliDriver.SIKULI_IMAGES_PATH + "table_of_contents.png");
                break;
            default:
                throw new IllegalArgumentException("Link handling not found!");
        }
    }

    @Then("^the '(.*)' text is '(.*)'")
    public void theTextIsCorrect(String sectionName, String expectedText) throws FindFailed {
        switch (sectionName) {
            case "Complete Guide":
                sikuliDriver.wait(SikuliDriver.SIKULI_IMAGES_PATH + "complete_guide.png", 15);
                assertTrue(sikuliDriver.find(SikuliDriver.SIKULI_IMAGES_PATH + "complete_guide.png").text().contains(expectedText));
                break;
            default:
                throw new IllegalArgumentException("Section handling not found!");
        }
    }

    @When("^I click on the 'Magazine' tab in the top menu$")
    public void iClickOnTheMagazineTabInTheTopMenu() throws FindFailed {
        String magazineTopMenuImage = SikuliDriver.SIKULI_IMAGES_PATH + "magazine_top_menu.png";
        sikuliDriver.wait(magazineTopMenuImage, 15);
        sikuliDriver.click(magazineTopMenuImage);
    }

    @Then("^the Magazine heading is shown")
    public void theMagazineHeadingIsShown() throws FindFailed {
        String magazineHeadingImage = SikuliDriver.SIKULI_IMAGES_PATH + "magazine_heading.png";
        double matchScore = sikuliDriver.exists(magazineHeadingImage, 15).getScore();
        assertEquals(1.0, matchScore, 0.05);
    }

    @Then("^the Xmas shipping text is shown as '(.*)'$")
    public void theXmasShippingTextIsShownAs(String expectedText) throws FindFailed {
        String amazonXmasShippingImage = SikuliDriver.SIKULI_IMAGES_PATH + "xmas_shipping.png";
        sikuliDriver.wait(amazonXmasShippingImage, 15);
        System.out.println(sikuliDriver.find(amazonXmasShippingImage).text());
        assertTrue(sikuliDriver.find(amazonXmasShippingImage).text().contains(expectedText));
    }

    @When("^I hover over the 'Science' link in the top menu$")
    public void iHoverOverTheLinkInTheTopMenu() throws FindFailed {
        String wiredScienceTopMenuImage = SikuliDriver.SIKULI_IMAGES_PATH + "wired_science_top_menu.png";
        sikuliDriver.wait(wiredScienceTopMenuImage, 15);
        sikuliDriver.click(wiredScienceTopMenuImage);
    }

    @Then("^the 'Science' link changes to yellow$")
    public void theScienceLinkChangesToYellow() throws FindFailed {
        String wiredScienceTopMenuHoverImage = SikuliDriver.SIKULI_IMAGES_PATH + "wired_science_top_menu_hover.png";
        double matchScore = sikuliDriver.exists(wiredScienceTopMenuHoverImage, 15).getScore();
        assertEquals(1.0, matchScore, 0.05);
    }
}
