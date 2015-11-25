@customSite @lecture7
Feature: Custom site

  @dropdownByText
  Scenario Outline: Dropdown test by text
    Given I navigate to http://40.127.132.250:8090/
    When I select the '<parkingText>' type of Parking by 'Text'
    Then the correct '<parkingText>' Parking is displayed along with its '<parkingValue>' value

  Examples:
    | parkingText               | parkingValue |
    | Short-Term Parking        | STP          |
    | Economy Parking           | EP           |
    | Long-Term Surface Parking | LTS          |
    | Long-Term Garage Parking  | LTG          |
    | Valet Parking             | VP           |

  @dropdownByValue
  Scenario Outline: Dropdown test by value
    Given I navigate to http://40.127.132.250:8090/
    When I select the '<parkingValue>' type of Parking by 'Value'
    Then the correct '<parkingText>' Parking is displayed along with its '<parkingValue>' value

  Examples:
    | parkingText               | parkingValue |
    | Short-Term Parking        | STP          |
    | Economy Parking           | EP           |
    | Long-Term Surface Parking | LTS          |
    | Long-Term Garage Parking  | LTG          |
    | Valet Parking             | VP           |

  @hover
  Scenario: Hover over text
    Given I navigate to http://40.127.132.250:8090/
    Then the text that appears upon hover is not shown
    When I hover over the text that triggers the hovered text
    Then the text that appears upon hover is shown

  @window
  Scenario: Click on link that opens new window
    Given I navigate to http://40.127.132.250:8090/
    When I click on the Click Me to Google! link
    Then the Google homepage is opened
    When I close the Google homepage
    Then the Click Me to Google! link is underlined

  @iframe
  Scenario: Checking text in iframes
    Given I navigate to http://40.127.132.250:8090/
    Then the text 'This page is displayed in an iframe' appears in the first iframe
    And the text 'This page is displayed in an iframe with another iframe' appears in the second iframe
    And there are 6 hr tags in the main document

  @alert
  Scenario Outline: Handling alerts
    Given I navigate to http://40.127.132.250:8090/
    When I click on the Press me, press me! button
    And I '<action>' the alert
    Then the '<text>' alert result text is displayed

  Examples:
    | action  | text                |
    | accept  | You pressed OK!     |
    | dismiss | You pressed Cancel! |

  @dragAndDrop
  Scenario: Drag and drop image
    Given I navigate to http://40.127.132.250:8090/
    When I drag the image of the dog and drop into the empty box
    Then the image of the dog is now inside the box