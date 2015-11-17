@mathcom @calculator
Feature: Math.com online calculator

  @lecture6
  Scenario Outline: Calculator
    Given I navigate to http://www.math.com/students/calculators/source/basic.htm
    When I input '<num1><operator><num2>' in the calculator
    Then the calculated answer is '<answer>'

    Examples:
      | num1 | operator | num2 | answer   |
      | 5    | +        | 2    | 7        |
      | 158  | *        | 22   | 3476     |
      | 10   | -        | 2    | 8        |
      | 12   | /        | 6    | 2        |
      | 5    | /        | 0    | Infinity |