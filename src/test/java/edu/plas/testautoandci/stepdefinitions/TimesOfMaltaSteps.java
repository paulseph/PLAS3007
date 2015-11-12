package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Then;
import edu.plas.testautoandci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;

public class TimesOfMaltaSteps {
    @Then("^the Times Talk section image has a height of (\\d+) pixels and width of (\\d+) pixels$")
    public void theTimesTalkSectionImageHasAHeightOf(int height, int width) {
        WebElement timesTalk = Driver.getWebDriver().findElement(By.id("widget_timestalk"));
        WebElement timesTalkImage = timesTalk.findElement(By.className("cover")).findElement(By.tagName("img"));

        // CSS
        // WebElement timesTalkImage = Driver.getWebDriver().findElement(By.cssSelector("#widget_timestalk .cover img"));

        // XPath
        // WebElement timesTalkImage = Driver.getWebDriver().findElement(By.xpath("//*[@id='widget_timestalk']//*[@class='cover']//img"));

        assertEquals(height, timesTalkImage.getSize().getHeight());
        assertEquals(width, timesTalkImage.getSize().getWidth());
    }
}