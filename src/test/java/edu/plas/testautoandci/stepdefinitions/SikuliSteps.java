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

public class SikuliSteps {
    private Screen sikuliDriver = SikuliDriver.getSikuliDriver();

    @Given("^I use Sikuli to navigate to (.*)$")
    public void iNavigateToOn(String url) throws FindFailed {
        App.open("Firefox");

        String addressBarImage = SikuliDriver.SIKULI_IMAGES_PATH + "firefox_address_bar.png";
        sikuliDriver.wait(addressBarImage, 60);
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
                System.out.println("In Default");
                throw new IllegalArgumentException("Section handling not found!");
        }
    }
}
