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
            } else if (browser.equals("localChrome")) {
                if (System.getProperty("os.name").contains("Mac")) {
                    System.setProperty("webdriver.chrome.driver", "browserdriver/chrome/chromedriver");
                } else if (System.getProperty("os.name").contains("Windows")) {
                    System.setProperty("webdriver.chrome.driver", "browserdriver/chrome/chromedriver.exe");
                }
                driver = new ChromeDriver();
            } else if (browser.equals("localIE")) {
                System.setProperty("webdriver.ie.driver", "browserdriver/ie/IEDriverServer.exe");
                DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                driver = new InternetExplorerDriver(ieCapabilities);

            } else if (browser.equals("gridFirefox")) {
                DesiredCapabilities capability = DesiredCapabilities.firefox();
                capability.setBrowserName("firefox");
                capability.setVersion("41.0.1");
                capability.setPlatform(Platform.WINDOWS);
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setEnableNativeEvents(false);
                capability.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
                driver = new RemoteWebDriver(new URL("http://40.127.132.250:4444/wd/hub"), capability);

            } else if (browser.equals("gridChrome")) {
                DesiredCapabilities capability = DesiredCapabilities.chrome();
                capability.setBrowserName("chrome");
                capability.setPlatform(Platform.WINDOWS);
                new RemoteWebDriver(new URL("http://40.127.132.250:4444/wd/hub"), capability);

            } else if (browser.equals("gridIE")) {
                DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
                capability.setBrowserName("iexplore");
                capability.setVersion("11");
                capability.setPlatform(Platform.WINDOWS);
                new RemoteWebDriver(new URL("http://40.127.132.250:4444/wd/hub"), capability);
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
