@microsoft @paint
Feature: Microsoft Paint

  Scenario: Colour picker functionality
    Given I launch the Microsoft Paint program
    Then the 'Colour Picker' tool is available

  Scenario: Paint picture and search for it on Google Images
    Given I launch the Microsoft Paint program
    When I select the 'Airbrush' tool
    And I select the 'Mustard' colour
    And I draw a 'mustard seed'
    Then the 'mustard seed' appears on the canvas
    Given I navigate to https://www.google.com/imghp
    When I search for 'mustard seed'
    Then my drawing of 'mustard seed' is similar to the first image on Google Images