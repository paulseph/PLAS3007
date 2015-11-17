@google @calculator
Feature: Google Calculator

  @lecture6
  Scenario Outline: Google calculator through Search
    Given I navigate to https://www.google.com/ncr
    When I search for '<searchText>' on Google Search
    Then the Google Calculator component is displayed
    And the result on Google Calculator is '<calculationResult>'

  Examples:
    | searchText | calculationResult |
    | 5+7        | 12                |
    | 12-7       | 5                 |