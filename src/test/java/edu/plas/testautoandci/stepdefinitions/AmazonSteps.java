package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AmazonSteps {
    @When("^I search for '(.*)'$")
    public void iSearchFor(String item) {
    }

    @Then("^a number of results are returned$")
    public void aNumberOfResultsAreReturned() {
    }

    @When("^I select the first result$")
    public void iSelectTheFirstResult() {
    }

    @When("^I add the current item to the Basket$")
    public void iAddTheCurrentItemToTheBasket() {
    }

    @Then("^a message confirming item is added to Basket is shown$")
    public void aMessageConfirmingItemIsAddedToBasketIsShown() {
    }

    @When("^I access the Basket$")
    public void iAccessTheBasket() {
    }

    @Then("^the Basket contains '(\\d+)' items$")
    public void theBasketContainsItems(int numberOfItems) {
    }

    @When("^the current item is displayed inside the Basket$")
    public void theCurrentItemIsDisplayedInsideTheBasket() {
    }

    @When("^I delete the current item from the Basket$")
    public void iDeleteTheCurrentItemFromTheBasket() {
    }
}
