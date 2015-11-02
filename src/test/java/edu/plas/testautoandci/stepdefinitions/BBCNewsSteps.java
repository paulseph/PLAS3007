package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

public class BBCNewsSteps {
    @Then("^the Features and Analysis menu contains (\\d+) items$")
    public void theFeaturesAndAnalysisMenuContains(int numberOfItems) {
    }

    @Then("^all the Features and Analysis menu items have a '(\\w+)'$")
    public void allTheFeaturesAndAnalysisMenuItemsHaveAFeature(String feature) {
    }

    @Then("^when I click the '(.*)' the correct story is loaded$")
    public void WhenIClickTheImageTheCorrectStoryIsLoaded(String section) {
    }


    @Then("^the main menu is visible with the following items:$")
    public void theMainMenuIsVisibleWithTheFollowingItems(DataTable arg1) {
    }

    @When("^I click on the menu item '(.*)'$")
    public void iClickOnTheMenuItem(String item) {
    }

    @Then("^the '(.*)' index is displayed$")
    public void theMenuItemIndexIsDisplayed(String mainMenuItems) {
    }

    @Then("^the Markets menu contains (\\d+) items$")
    public void theMarketsManuContainsItems(int numberOfMarkets) {
    }


    @Then("^the Markets menu items are: (.*)$")
    public void theMarketsMenuItemsAre (List<String> marketItems) {
    }

    @Then("^each item in the Markets menu contains the '(.*)'$")
    public void eachItemInTheMarketsMenuContainsThe(String marketMenuItem) {
    }


    @Then("^the World Service Radio link is visible in the Watch\\/Listen menu$")
    public void theWorldServiceRadioLinkIsVisibleInTheWatchListenMenu() {
    }

    @Then("^the Watch/Listen menu contains (\\d+) items$")
    public void theWatchListenMenuContainsItems(int numberOfItems) {
    }

    @Then("^all the Watch\\/Listen menu items have a '(.*)'$")
    public void allTheWatchListenMenuItemsHaveA(String feature) {
    }
}