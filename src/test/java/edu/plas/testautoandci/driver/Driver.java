package edu.plas.testautoandci.driver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import edu.plas.testautoandci.helper.DriverScreenShotHelper;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver driver = null;
    private static String browser;
    private static boolean imagesCleaned = false;
    private static final String SELENIUM_GRID_HUB_URL = "http://40.127.132.250:4444/wd/hub";
    private static final String CHROME_DRIVER_MAC_PATH = "browserdriver/chrome/chromedriver";
    private static final String CHROME_DRIVER_WINDOWS_PATH = "browserdriver/chrome/chromedriver.exe";
    private static final String IE_DRIVER_WINDOWS_PATH = "browserdriver/ie/IEDriverServer.exe";

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
            switch (browser) {
                case "localFirefox":
                    FirefoxProfile firefoxProfile = new FirefoxProfile();
                    firefoxProfile.setEnableNativeEvents(false);
                    driver = new FirefoxDriver(firefoxProfile);
                    break;

                case "localChrome":
                    if (System.getProperty("os.name").contains("Mac")) {
                        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_MAC_PATH);
                    } else if (System.getProperty("os.name").contains("Windows")) {
                        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_WINDOWS_PATH);
                    }
                    driver = new ChromeDriver();
                    break;

                case "localIE":
                    System.setProperty("webdriver.ie.driver", IE_DRIVER_WINDOWS_PATH);
                    DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                    driver = new InternetExplorerDriver(ieCapabilities);
                    break;

                case "gridFirefox":
                    FirefoxProfile firefoxProfileGrid = new FirefoxProfile();
                    firefoxProfileGrid.setEnableNativeEvents(false);
                    DesiredCapabilities firefoxCapability = DesiredCapabilities.firefox();
                    firefoxCapability.setBrowserName("firefox");
                    firefoxCapability.setVersion("41.0.1");
                    firefoxCapability.setPlatform(Platform.WINDOWS);
                    firefoxCapability.setCapability(FirefoxDriver.PROFILE, firefoxProfileGrid);
                    driver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB_URL), firefoxCapability);
                    break;

                case "gridChrome":
                    DesiredCapabilities chromeCapability = DesiredCapabilities.chrome();
                    chromeCapability.setBrowserName("chrome");
                    chromeCapability.setPlatform(Platform.WINDOWS);
                    driver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB_URL), chromeCapability);
                    break;

                case "gridIE":
                    DesiredCapabilities ieCapability = DesiredCapabilities.internetExplorer();
                    ieCapability.setBrowserName("internet explorer");
                    ieCapability.setVersion("11");
                    ieCapability.setPlatform(Platform.WINDOWS);
                    driver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB_URL), ieCapability);
                    break;

                default:
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
