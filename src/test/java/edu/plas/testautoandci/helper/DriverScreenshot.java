package edu.plas.testautoandci.helper;

import cucumber.api.Scenario;
import edu.plas.testautoandci.driver.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class DriverScreenshot {
    public static void takeScreenshot(Scenario scenario) {
        File sourceImageFile = ((TakesScreenshot) Driver.getWebDriver()).getScreenshotAs(OutputType.FILE);

        if (sourceImageFile == null) {
            throw new RuntimeException("Source Image File cannot be null - screen shot not taken!");
        }



        String htmlReportsPath = "reports/html-reports/";

        try {
            new File(htmlReportsPath + "hello.txt").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String imageName = System.currentTimeMillis() + ".jpeg";
        String screenShotPath = htmlReportsPath + imageName;

        System.out.println(screenShotPath);

        try {
            FileUtils.copyFile(sourceImageFile, new File(screenShotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String cucumberFailureScreenShotPath;
        // This handles screen shot paths when test is run on Jenkins
        String buildURL = System.getenv("BUILD_URL");
        if (buildURL != null) {
            buildURL = buildURL.replaceAll("\\d+/$", "ws/");
            String absoluteImagePath = buildURL + screenShotPath;
            cucumberFailureScreenShotPath = absoluteImagePath.replace(" ", "%20");
        } else {
            // This is the path for tests executed on local machine
            cucumberFailureScreenShotPath = imageName;
        }
        scenario.write("<a href=\"" + cucumberFailureScreenShotPath + "\" target=\"_blank\">Click to reveal screenshot at time of failure</a>");
    }
}
