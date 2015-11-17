package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.plas.testautoandci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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


    @Then("^there are (\\d+) main menu items$")
    public void theMainMenuIsVisibleWithTheFollowingItems(int numberOfMenuItems) {
        List<WebElement> menuElements = Driver.getWebDriver().findElement(By.className("navigation-wide-list")).findElements(By.tagName("li"));

        // CSS
        // List<WebElement> menuElements = Driver.getWebDriver().findElements(By.cssSelector(".navigation-wide-list li"));

        // XPath
        // List<WebElement> menuElements = Driver.getWebDriver().findElements(By.xpath("//*[@class=\"navigation-wide-list\"]/li"));

        assertEquals(numberOfMenuItems, menuElements.size());
    }

    @Then("^the main menu is visible with the following items:$")
    public void theMainMenuIsVisibleWithTheFollowingItems(List<String> menuItems) {
    }

    @When("^I click on the menu item '(.*)'$")
    public void iClickOnTheMenuItem(String menu) {
        WebElement menuItem = Driver.getWebDriver().findElement(By.xpath("//*[@class='navigation-wide-list']/li//span[text()='" + menu + "']"));
        menuItem.click();
    }

    @Then("^the '(.*)' index is displayed$")
    public void theMenuItemIndexIsDisplayed(String menuItem) {
        if ("Video".equals(menuItem)) {
            // Video index does not set the menu item as selected
            assertTrue(Driver.getWebDriver().findElement(By.id("media-asset-page-video")).isDisplayed());
        } else {
            String selectedMenu = Driver.getWebDriver().findElement(By.cssSelector(".navigation-wide-list li.selected a")).getText();
            assertEquals(menuItem, selectedMenu);
        }
    }

    @Then("^the Markets menu contains (\\d+) items$")
    public void theMarketsManuContainsItems(int numberOfMarkets) {
        List<WebElement> marketElements = Driver.getWebDriver().findElement(By.id("market_data-markets_index_promo"))
                .findElements(By.className("markets-index-promo__row"));

        // CSS
        // List<WebElement> marketElements = Driver.getWebDriver().findElements(By.cssSelector("#market_data-markets_index_promo .markets-index-promo__row"));

        // XPath
        // List<WebElement> marketElements = Driver.getWebDriver().findElements(By.xpath("//*[@id=\"market_data-markets_index_promo\"]//*[@class=\"markets-index-promo__row\"]"));

        assertEquals(numberOfMarkets, marketElements.size());
    }


    @Then("^the Markets menu items are: (.*)$")
    public void theMarketsMenuItemsAre (List<String> marketItems) {
        List<WebElement> marketElements = Driver.getWebDriver()
                .findElement(By.id("market_data-markets_index_promo"))
                .findElements(By.className("markets-index-promo__row"));

        // CSS and XPath is the same as in the previous method

        for (int i = 0; i < marketElements.size(); i++) {
            String expectedMarketName = marketItems.get(i);
            String actualMarketName = marketElements.get(i)
                    .findElement(By.className("markets-index-promo__index-name")).getText();
            assertEquals(expectedMarketName, actualMarketName);
        }
    }

    @Then("^each item in the Markets menu contains the '(.*)'$")
    public void eachItemInTheMarketsMenuContainsThe(String marketMenuItem) {
        List<WebElement> markets = Driver.getWebDriver().findElements(By.className("markets-index-promo__row"));

        for (WebElement market: markets) {
            switch (marketMenuItem) {
                case "index":
                    WebElement index = market.findElement(By.xpath("//td[not(@class)]"));
                    assertTrue("Market index points should be displayed!", index.isDisplayed());
                    break;
                case "arrow":
                    WebElement arrow = market.findElement(By.xpath("//td[@class='down' or @class='up']"));
                    assertTrue("Market index arrow indicator should be displayed!", arrow.isDisplayed());
                    break;
                case "percentage":
                    String percentage = market.findElement(By.xpath("//td[@class='down' or @class='up']")).getText();
                    assertTrue("Market index should include the percentage up or down (%)!", percentage.contains("%"));
                    break;
                default:
            }
        }
    }


    @Then("^the World Service Radio link is visible in the Watch\\/Listen menu$")
    public void theWorldServiceRadioLinkIsVisibleInTheWatchListenMenu() {
    }

    @Then("^the Watch/Listen menu contains (\\d+) items$")
    public void theWatchListenMenuContainsItems(int numberOfItems) {
        List<WebElement> watchListenItems = Driver.getWebDriver().findElement(By.className("column--secondary"))
                .findElement(By.id("comp-candy-asset-munger"))
                .findElements(By.className("condor-item"));

        // CSS
        // List<WebElement> watchListenItems = Driver.getWebDriver().findElements(By.cssSelector(".column--secondary #comp-candy-asset-munger .condor-item"));

        // XPath
        // List<WebElement> watchListenItems = Driver.getWebDriver().findElements(By.xpath("//*[@class=\"column--secondary\"]//*[@id=\"comp-candy-asset-munger\"]/*[contains(@class, \"condor\")]/*[contains(@class, \"condor-item\")]"));
        // List<WebElement> watchListenItems = Driver.getWebDriver().findElements(By.xpath("//*[@class=\"column--secondary\"]//*[@id=\"comp-candy-asset-munger\"]//*[@class = \"condor-item faux-block-link\"]"));
        // List<WebElement> watchListenItems = Driver.getWebDriver().findElements(By.xpath("//*[@class=\"column--secondary\"]//*[@id=\"comp-candy-asset-munger\"]//*[contains(@data-entityid, \"av-stories-now\")]"));

        assertEquals(numberOfItems, watchListenItems.size());
    }

    @Then("^all the Watch\\/Listen menu items have a '(.*)'$")
    public void allTheWatchListenMenuItemsHaveA(String feature) {
        List<WebElement> watchListenItems = Driver.getWebDriver().findElement(By.className("column--secondary"))
                .findElement(By.id("comp-candy-asset-munger"))
                .findElements(By.className("condor-item"));

        // CSS and XPath is the same as in the previous method

        switch (feature) {
            case "title":
                for (WebElement watchListenItem : watchListenItems) {
                    assertTrue(watchListenItem.findElement(By.className("title-link__title-text")).isDisplayed());
                }
                break;
            case "image":
                for (WebElement watchListenItem : watchListenItems) {
                    assertTrue(watchListenItem.findElement(By.tagName("img")).isDisplayed());
                }
                break;
            case "index":
                for (WebElement watchListenItem : watchListenItems) {
                    assertTrue(watchListenItem.findElement(By.className("mini-info-list__section")).isDisplayed());
                }
                break;
            default:
                throw new IllegalArgumentException("Feature can only be one of title, image and index");
        }
    }
}
