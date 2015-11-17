package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.plas.testautoandci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AmazonSteps {
    String titleInSearchResults;

    @When("^I search for '(.*)'$")
    public void iSearchFor(String item) {
        WebElement search = Driver.getWebDriver().findElement(By.name("site-search"));
        search.findElement(By.id("twotabsearchtextbox")).sendKeys(item);
        search.findElement(By.className("nav-input")).click();


    }

    @Then("^a number of results are returned$")
    public void aNumberOfResultsAreReturned() {
        List<WebElement> results = Driver.getWebDriver().findElements(By.xpath("//*[@id=\"s-results-list-atf\"]//li[starts-with(@id,\"result\")]"));
        assertTrue("No results were returned!", results.size() > 0);
    }

    @When("^I select the first result$")
    public void iSelectTheFirstResult() {
        List<WebElement> results = Driver.getWebDriver().findElements(By.xpath("//*[@id=\"s-results-list-atf\"]//li[starts-with(@id,\"result\")]"));
        WebElement firstItem = results.get(0);
        WebElement titleLink = firstItem.findElement(By.xpath("//a[contains(@class, \"s-access-detail-page\")]"));
        titleInSearchResults = titleLink.findElement(By.tagName("h2")).getText();
        titleLink.click();
    }

    @When("^I add the current item to the Basket$")
    public void iAddTheCurrentItemToTheBasket() {
        assertEquals(titleInSearchResults, Driver.getWebDriver().findElement(By.id("productTitle")).getText());
        Driver.getWebDriver().findElement(By.id("add-to-cart-button")).click();
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
