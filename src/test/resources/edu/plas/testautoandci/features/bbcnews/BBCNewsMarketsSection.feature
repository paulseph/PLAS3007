@bbc @news @markets
Feature: BBC News Markets Section

  Scenario: Markets menu contains 4 stock exchange points
    Given I navigate to http://www.bbc.com/news
    Then the Markets menu contains 4 items
    And the Markets menu items are: FTSE 100, Dow Jones, Nasdaq, Nikkei 225

  Scenario: Each Markets menu item contains the index, arrow and percentage
    Given I navigate to http://www.bbc.com/news
    Then each item in the Markets menu contains the 'index'
    And each item in the Markets menu contains the 'arrow'
    And each item in the Markets menu contains the 'percentage'