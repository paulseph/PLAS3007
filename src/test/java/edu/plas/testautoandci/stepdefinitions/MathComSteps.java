package edu.plas.testautoandci.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MathComSteps {
    @When("^I input '(\\d+)(.)(\\d+)' in the calculator$")
    public void iInputNum1OperatorNum2InTheCalculators(int num1, String operator, int num2) {
    }

    @Then("^the calculated answer is '(.*)'$")
    public void theCalculatedAnswerIs(String answer) {
    }
}
