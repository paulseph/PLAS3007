@sikuli @lecture11
Feature: Testing Sikuli functionality

  @completeGuide
  Scenario: Test correct text in Complete Guide
    Given I use Sikuli to navigate to http://www.sikuli.org/
    When I click on the 'Documentation' tab using Sikuli
    And I click on the 'table of contents' link using Sikuli
    Then the 'Complete Guide' text is 'What is new in SikuliX'

  @bbcMagazine
  Scenario: Test correct navigation to BBC News Magazine page
    Given I use Sikuli to navigate to http://www.bbc.com/news
    When I click on the 'Magazine' tab in the top menu
    Then the Magazine heading is shown

  @amazonXmas
  Scenario: Test correct text on Amazon site for Xmas shopping
    Given I use Sikuli to navigate to http://www.amazon.com
    Then the Xmas shipping text is shown as 'Shop 12 Days of Deals'

  @wiredScience
  Scenario: Test correct image hover on Wired site
    Given I use Sikuli to navigate to http://www.wired.com
    When I hover over the 'Science' link in the top menu
    Then the 'Science' link changes to yellow

