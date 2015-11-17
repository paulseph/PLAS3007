package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.plas.testautoandci.driver.Driver;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class MathComSteps {
    @When("^I input '(\\d+)(.)(\\d+)' in the calculator$")
    public void iInputNum1OperatorNum2InTheCalculators(int num1, String operator, int num2) {
        if ("*".equals(operator)) {
            operator = "x";
        }

        String sequence = num1 + operator + num2;

        for (char digit : sequence.toCharArray()) {
            Driver.getWebDriver().findElement(By.xpath("//form[@name=\"Calc\"]//input[contains(@value, \"" + digit + "\")]")).click();
        }
        Driver.getWebDriver().findElement(By.xpath("//form[@name=\"Calc\"]//input[contains(@value, \"=\")]")).click();
    }

    @Then("^the calculated answer is '(.*)'$")
    public void theCalculatedAnswerIs(String answer) {
        assertEquals(answer, Driver.getWebDriver().findElement(By.xpath("//form[@name=\"Calc\"]//input[@name=\"Input\"]")).getAttribute("value"));
    }
}
