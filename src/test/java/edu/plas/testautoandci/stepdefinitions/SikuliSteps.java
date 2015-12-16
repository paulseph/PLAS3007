package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import edu.plas.testautoandci.driver.SikuliDriver;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class SikuliSteps {
    private Screen sikuliDriver = SikuliDriver.getSikuliDriver();

    @Given("^I use Sikuli to navigate to (.*)$")
    public void iNavigateToOn(String url) throws FindFailed {
        App.open("Firefox");

        String addressBarImage = SikuliDriver.SIKULI_IMAGES_PATH + "firefox_address_bar.png";
        sikuliDriver.wait(addressBarImage, 30);
        sikuliDriver.type(addressBarImage, url + Key.ENTER);
    }

    @When("^I click on the Sikuli (.*) tab$")
    public void iClickOnTheSikuliTab(String tab) throws FindFailed {
        switch (tab) {
            case "Documentation":
                sikuliDriver.click("src/test/resources/sikuliimages/documentation_tab.png");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}
