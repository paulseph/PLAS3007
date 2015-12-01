package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Then;
import edu.plas.testautoandci.helper.GeneralHelper;
import edu.plas.testautoandci.pageobjectmodels.StudentsListPage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentsListSteps {
    private StudentsListPage studentsListPage = new StudentsListPage();

    @Then("^there are '(.*)' students in the list$")
    public void thereAreStudentsInTheList(String numberOfStudents) {
        int numberOfStudentsInList = studentsListPage.getNumberOfStudentsInList();
        if (GeneralHelper.isStringInt(numberOfStudents)) {
            assertEquals(Integer.parseInt(numberOfStudents), numberOfStudentsInList);
        } else {
            // Handle non numeric here
        }
    }

    @Then("^all students have an ID, first name, last name, country and mobile$")
    public void allStudentsHaveAnIDFirstNameLastNameCountryAndMobile() {
        int numberOfStudents = studentsListPage.getNumberOfStudentsInList();

        assertEquals(numberOfStudents, studentsListPage.getNumberOfStudentIDsInList());
        assertEquals(numberOfStudents, studentsListPage.getNumberOfStudentFirstNamesInList());
        assertEquals(numberOfStudents, studentsListPage.getNumberOfStudentLastNamesInList());
        assertEquals(numberOfStudents, studentsListPage.getNumberOfStudentCountriesInList());
        assertEquals(numberOfStudents, studentsListPage.getNumberOfStudentMobilesInList());
    }

    @Then("^'(.*)' students in the list are called '(\\w+)'$")
    public void studentsInTheListAreCalled(String numberOfStudents, String studentName) {
        int numberOfStudentsCalledFromList = studentsListPage.getNumberOfStudentsCalled(studentName);
        if (GeneralHelper.isStringInt(numberOfStudents)) {
            assertEquals(Integer.parseInt(numberOfStudents), numberOfStudentsCalledFromList);
        } else {
            // Handle non numeric here
        }
    }

    @Then("^'(.*)' students are from '(\\w+)'$")
    public void studentsAreFrom(String numberOfStudents, String studentCountry) {
        int numberOfStudentsFromCountryFromList = studentsListPage.getNumberOfStudentsFromCountry(studentCountry);
        if (GeneralHelper.isStringInt(numberOfStudents)) {
            assertEquals(Integer.parseInt(numberOfStudents), numberOfStudentsFromCountryFromList);
        } else {
            if ("at least 2".equals(numberOfStudents.toLowerCase())) {
                assertTrue("2 or more students should be from " + studentCountry + " but there are " + numberOfStudentsFromCountryFromList, numberOfStudentsFromCountryFromList >= 2);
            }
        }
    }

    @Then("^all mobile numbers are solely numeric$")
    public void allMobileNumbersAreSolelyNumeric() {
        List<String> assertionMessages = new ArrayList<>();
        for (String mobile : studentsListPage.getListOfAllStudentMobiles()) {
            try {
                assertTrue(Pattern.matches("^[\\d]+", mobile));
            } catch (AssertionError ae) {
                assertionMessages.add("Mobile number (" + mobile + ") should be only numeric!");
            }
        }

        if (assertionMessages.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String message : assertionMessages) {
                sb.append(message).append("\n");
            }
            throw new AssertionError(sb.toString());
        }
    }
}