package edu.plas.testautoandci.helper;

import cucumber.api.Scenario;
import edu.plas.testautoandci.driver.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;

public class DriverScreenshot {
    public static void takeScreenshot(Scenario scenario) {
        File sourceImageFile = null;

        if (Driver.getBrowser().contains("local")) {
            sourceImageFile = ((TakesScreenshot) Driver.getWebDriver()).getScreenshotAs(OutputType.FILE);
        } else if (Driver.getBrowser().contains("grid")) {
            sourceImageFile = ((TakesScreenshot) new Augmenter().augment(Driver.getWebDriver())).getScreenshotAs(OutputType.FILE);
        }

        if (sourceImageFile == null) {
            throw new RuntimeException("Source Image File cannot be null - screen shot not taken!");
        }

        String htmlReportsPath = "reports/html-reports/";
        String imageName = System.currentTimeMillis() + ".jpeg";
        String screenShotPath = htmlReportsPath + imageName;
        try {
            FileUtils.copyFile(sourceImageFile, new File(screenShotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String cucumberFailureScreenShotPath;
        String buildURL = System.getenv("BUILD_URL");
        if (buildURL != null) {
            buildURL = buildURL.replaceAll("\\d+/$", "ws/");
            String absoluteImagePath = buildURL + screenShotPath;
            cucumberFailureScreenShotPath = absoluteImagePath.replace(" ", "%20");
        } else {
            cucumberFailureScreenShotPath = imageName;
        }
        scenario.write("<a href=\"" + cucumberFailureScreenShotPath + "\" target=\"_blank\">Click to reveal screenshot at time of failure</a>");
    }
}
