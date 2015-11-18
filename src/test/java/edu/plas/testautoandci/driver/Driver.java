package edu.plas.testautoandci.driver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import edu.plas.testautoandci.helper.DriverScreenShotHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver driver = null;
    private static String browser;
    private static boolean imagesCleaned = false;

    @Before
    public void setup() {
        // Delete all screen shots from previous execution
        // THIS SHOULD BE EXECUTED ONLY ONCE
        if (!imagesCleaned) {
            File reportsDirectory = new File("reports/html-reports");
            final File[] files = reportsDirectory.listFiles((dir, name) -> {
                return name.matches(".*.jpeg");
            });
            for (final File file : files) {
                file.delete();
            }
            imagesCleaned = true;
        }

        browser = System.getProperty("browser");
        startWebDriver();
    }

    public static WebDriver getWebDriver() {
        if (driver == null) {
            throw new IllegalStateException("Selenium WebDriver is not initialised!");
        }
        return driver;
    }

    @After
    public void tearDown(Scenario scenario) {
        // If Cucumber scenario fails, output time of failure and take screen shot
        if (scenario.isFailed()) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            scenario.write("Time of failure: " + dateFormat.format(Calendar.getInstance().getTime()));
            DriverScreenShotHelper.takeScreenShot(scenario);
        }

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void startWebDriver() {
        try {
            if (browser.equals("localFirefox")) {
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setEnableNativeEvents(false);
                driver = new FirefoxDriver(firefoxProfile);
            } else {
                throw new IllegalArgumentException("Browser system property is wrong! Cannot be " + browser);
            }
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Selenium WebDriver!", e);
        }
    }

    public static String getBrowser() {
        return browser;
    }
}
