package edu.plas.testautoandci.pageobjectmodels;

import edu.plas.testautoandci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StudentsCreationPage {
    private WebDriver driver = Driver.getWebDriver();

    public void createNewUser(String firstName, String lastName, String country, String mobile) {
        populateFirstName(firstName);
        populateLastName(lastName);
        populateCountry(country);
        populateMobile(mobile);
        driver.findElement(By.tagName("form")).submit();
    }

    private void populateFirstName(String firstName) {
        driver.findElement(By.name("firstName")).sendKeys(firstName);
    }

    private void populateLastName(String lastName) {
        driver.findElement(By.name("lastName")).sendKeys(lastName);
    }

    private void populateCountry(String country) {
        driver.findElement(By.name("country")).sendKeys(country);
    }

    private void populateMobile(String mobile) {
        driver.findElement(By.name("mobile")).sendKeys(mobile);
    }
}
