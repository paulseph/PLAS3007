package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MicrosoftPaintSteps {
    @Given("^I launch the Microsoft Paint program$")
    public void iLaunchTheMicrosoftPaintProgram() {
    }

    @Then("^the '(.*)' tool is available$")
    public void theToolIsAvailable(String tool) {
    }

    @When("^I select the '(.*)' tool$")
    public void iSelectTheTool(String tool) {
    }

    @When("^I select the '(.*)' colour$")
    public void iSelectTheColour(String colour) {
    }

    @When("^I draw a '(.*)'$")
    public void iDrawA(String drawing) {
    }

    @Then("^the '(.*)' appears on the canvas$")
    public void theAppearsOnTheCanvas(String drawing) {
    }

    @Then("^my drawing of '(.*)' is similar to the first image on Google Images$")
    public void myDrawingOfIsSimilarToTheFirstImageOnGoogleImages(String drawing) {
    }
}
