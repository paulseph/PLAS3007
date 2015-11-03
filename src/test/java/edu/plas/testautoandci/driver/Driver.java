package edu.plas.testautoandci.driver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver driver = null;
    private static String BROWSER;

    @Before
    public void setup() {
        BROWSER = System.getProperty("browser");
        startWebDriver();
    }

    public static WebDriver getWebDriver() {
        if (driver == null) {
            throw new IllegalStateException("Selenium WebDriver is not initialised!");
        }
        return driver;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void startWebDriver() {
        try {
            if (BROWSER.equals("localFirefox")) {
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setEnableNativeEvents(false);
                driver = new FirefoxDriver(firefoxProfile);
            } else {
                throw new IllegalArgumentException("Browser system property is wrong! Cannot be " + BROWSER);
            }
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Selenium WebDriver!", e);
        }
    }
}
