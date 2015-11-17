package edu.plas.testautoandci.driver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import edu.plas.testautoandci.helper.DriverScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver DRIVER = null;
    private static String BROWSER;
    private static boolean IMAGES_CLEANED = false;

    @Before
    public void setup() {
        // Delete all screen shots from previous execution
        // THIS SHOULD BE EXECUTED ONLY ONCE
        if (!IMAGES_CLEANED) {
            File reportsDirectory = new File("reports/html-reports");
            final File[] files = reportsDirectory.listFiles((dir, name) -> {
                return name.matches(".*.jpeg");
            });
            for (final File file : files) {
                file.delete();
            }
            IMAGES_CLEANED = true;
        }

        BROWSER = System.getProperty("browser");
        startWebDriver();
    }

    public static WebDriver getWebDriver() {
        if (DRIVER == null) {
            throw new IllegalStateException("Selenium WebDriver is not initialised!");
        }
        return DRIVER;
    }

    @After
    public void tearDown(Scenario scenario) {
        // If Cucumber scenario fails, output time of failure and take screen shot
        if (scenario.isFailed()) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            scenario.write("Time of failure: " + dateFormat.format(Calendar.getInstance().getTime()));
            DriverScreenshot.takeScreenshot(scenario);
        }

        if (DRIVER != null) {
            DRIVER.quit();
            DRIVER = null;
        }
    }

    private void startWebDriver() {
        try {
            if (BROWSER.equals("localFirefox")) {
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setEnableNativeEvents(false);
                DRIVER = new FirefoxDriver(firefoxProfile);
            } else {
                throw new IllegalArgumentException("Browser system property is wrong! Cannot be " + BROWSER);
            }
            DRIVER.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            DRIVER.manage().window().maximize();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Selenium WebDriver!", e);
        }
    }

    public static String getBrowser() {
        return BROWSER;
    }
}
