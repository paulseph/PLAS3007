@timesOfMalta @news @homePage
Feature: Times of Malta home page

    @timesTalk
    Scenario: Times Talk image size
        Given I navigate to http://www.timesofmalta.com/
        Then the Times Talk section image has a height of 156 pixels and width of 278 pixels