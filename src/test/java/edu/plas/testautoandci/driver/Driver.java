package edu.plas.testautoandci.driver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    private static WebDriver driver = null;

    @Before
    public void startWebDriver() {
        getWebDriver();
    }

    public static WebDriver getWebDriver() {
        if (driver == null) {
            driver = new FirefoxDriver();
        }
        return driver;
    }

    @After
    public void quitWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
