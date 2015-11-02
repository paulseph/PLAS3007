@google @search
Feature: Google Search

  Scenario: Google Search returns stats and results
    Given I navigate to Google Search
    When I search for 'University of Malta' on Google Search
    Then the Google stats tab is displayed
    And the Google search results are displayed

  Scenario: Google Search for country returns flag
    Given I navigate to Google Search
    When I search for 'Malta' on Google Search
    Then the flag of 'Malta' is displayed