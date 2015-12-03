package edu.plas.testautoandci.pageobjectmodels;

import edu.plas.testautoandci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class StudentsListPage {
    private WebDriver driver = Driver.getWebDriver();
    private final String STUDENTS_LIST_XPATH = "//tr[starts-with(@id, 'student')]";

    private List<WebElement> getListOfStudents() {
        return driver.findElements(By.xpath(STUDENTS_LIST_XPATH));
    }

    public int getNumberOfStudentsInList() {
        return getListOfStudents().size();
    }

    public int getNumberOfStudentIDsInList() {
        return getNumberOfStudentFieldInList("st_id");
    }

    public int getNumberOfStudentFirstNamesInList() {
        return getNumberOfStudentFieldInList("st_firstname");
    }

    public int getNumberOfStudentLastNamesInList() {
        return getNumberOfStudentFieldInList("st_lastname");
    }

    public int getNumberOfStudentCountriesInList() {
        return getNumberOfStudentFieldInList("st_country");
    }

    public int getNumberOfStudentMobilesInList() {
        return getNumberOfStudentFieldInList("st_mobile");
    }

    private int getNumberOfStudentFieldInList(String fieldId) {
        int numberOfPopulatedFields = 0;

        for (WebElement student : getListOfStudents()) {
            String field = student.findElement(By.id(fieldId)).getText();
            if(field != null && !field.isEmpty()) {
                numberOfPopulatedFields++;
            }
        }

        return numberOfPopulatedFields;
    }

    public int getNumberOfStudentsCalled(String studentName) {
        return driver.findElements(By.xpath(STUDENTS_LIST_XPATH + "/td[@id='st_firstname' and text()='" + studentName + "']")).size();
    }

    public int getNumberOfStudentsFromCountry(String studentCountry) {
        return driver.findElements(By.xpath(STUDENTS_LIST_XPATH + "/td[@id='st_country' and text()='" + studentCountry + "']")).size();
    }

    public List<String> getListOfAllStudentMobiles() {
        List<String> allMobileNumbers = new ArrayList<>();

        for (WebElement student : getListOfStudents()) {
            allMobileNumbers.add(student.findElement(By.id("st_mobile")).getText());
        }

        return allMobileNumbers;
    }

    public void deleteAllStudents() {
        driver.findElement(By.cssSelector("#delete_all a")).click();
    }
}