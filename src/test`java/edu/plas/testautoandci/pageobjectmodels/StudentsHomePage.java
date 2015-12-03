package edu.plas.testautoandci.pageobjectmodels;

import edu.plas.testautoandci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StudentsHomePage {
    private WebDriver driver = Driver.getWebDriver();

    public void clickOnListLink() {
        driver.findElement(By.linkText("List")).click();
    }

    public void clickOnSearchLink() {
        driver.findElement(By.linkText("Search")).click();
    }

    public void clickOnAddLink() {
        driver.findElement(By.linkText("Add")).click();
    }
}
