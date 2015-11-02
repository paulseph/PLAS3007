@bbc @news @watchListen
Feature: BBC News Watch Listen Section

  Scenario: Watch/Listen menu shows the World Service Radio link
    Given I navigate to the BBC News website
    Then the World Service Radio link is visible in the Watch/Listen menu

  Scenario: Watch/Listen menu contains 6 items
    Given I navigate to the BBC News website
    Then the Watch/Listen menu contains 6 items

  Scenario Outline: Watch/Listen menu items all have a title, image and associated index
    Given I navigate to the BBC News website
    Then all the Watch/Listen menu items have a '<feature>'

    Examples:
      | feature |
      | title   |
      | image   |
      | index   |