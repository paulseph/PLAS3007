package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PaulsephSteps {
    @Given ("^I test")
    public void paulsephGiven(){
        System.out.println("Given reached.");
    }

    @Then("^I get result")
    public void paulsephThen(){
        System.out.println("Then reached.");
    }
}
