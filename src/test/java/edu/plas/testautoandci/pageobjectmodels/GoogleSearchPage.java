package edu.plas.testautoandci.pageobjectmodels;

import edu.plas.testautoandci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPage {
    private WebDriver driver = Driver.getWebDriver();

    public boolean isGoogleSearchTextBarDisplayed() {
        return driver.findElement(By.id("lst-ib")).isDisplayed();
    }

    public boolean isGoogleSearchButtonDisplayed() {
        return driver.findElement(By.name("btnK")).isDisplayed();
    }

    public boolean isGoogleSearchImFeelingLuckyButtonDisplayed() {
        return driver.findElement(By.name("btnI")).isDisplayed();
    }
}
