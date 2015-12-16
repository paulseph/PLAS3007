package edu.plas.testautoandci.helper;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import edu.plas.testautoandci.driver.Driver;
import edu.plas.testautoandci.driver.SikuliDriver;
import edu.plas.testautoandci.pageobjectmodels.StudentsCreationPage;
import edu.plas.testautoandci.pageobjectmodels.StudentsHomePage;
import edu.plas.testautoandci.pageobjectmodels.StudentsListPage;
import org.sikuli.script.App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CucumberBeforeAfter {
    private static boolean imagesCleaned = false;
    private static boolean studentPopulationExecuted = false;

    // This ensures that this @Before is always executed first
    @Before(order = 0, value = "~@sikuli")
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

        Driver.setBrowser(System.getProperty("browser"));
        Driver.startWebDriver();
    }

    // This ensures that this @After is always executed last
    @After(order = 0, value = "~@sikuli")
    public void tearDown(Scenario scenario) {
        // If Cucumber scenario fails, output time of failure and take screen shot
        if (scenario.isFailed()) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            scenario.write("Time of failure: " + dateFormat.format(Calendar.getInstance().getTime()));
            DriverScreenShotHelper.takeScreenShot(scenario);
        }

        if (Driver.getWebDriver() != null) {
            Driver.getWebDriver().quit();
            Driver.nullWebDriver();
        }
    }

    @After(value = "@sikuli")
    public void tearDownSikuli() {
        App.close("Firefox");
    }

    @Before(value="@students")
    public void populateStudentData() {
        if (!studentPopulationExecuted) {
            System.out.println("Students are being populated - this only happens once at the beginning of test execution");

            Driver.getWebDriver().get("http://40.127.132.250:8090/course/students.jsp");
            new StudentsHomePage().clickOnAddLink();
            StudentsCreationPage studentCreationPage = new StudentsCreationPage();
            try {
                try (BufferedReader br = new BufferedReader(new FileReader(new File("testdata/students.csv")))) {
                    for (String studentData; (studentData = br.readLine()) != null; ) {
                        String[] studentDataExploded = studentData.split(",");
                        String firstName = studentDataExploded[0];
                        String lastName = studentDataExploded[1];
                        String country = studentDataExploded[2];
                        String mobile = studentDataExploded[3];
                        studentCreationPage.createNewUser(firstName, lastName, country, mobile);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Student population complete");

            studentPopulationExecuted = true;

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    System.out.println("Students are being deleted - this only happens at the end of test execution");
                    Driver.startWebDriver();
                    Driver.getWebDriver().get("http://40.127.132.250:8090/course/students.jsp");
                    new StudentsHomePage().clickOnListLink();
                    new StudentsListPage().deleteAllStudents();
                    System.out.println("Student deletion complete");
                    if (Driver.getWebDriver() != null) {
                        Driver.getWebDriver().quit();
                        Driver.nullWebDriver();
                    }
                }
            });
        }
    }
}
