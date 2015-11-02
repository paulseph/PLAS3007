@google @imageSearch
Feature: Google Image Search

  Scenario Outline: Image search
    Given I navigate to Google Images
    When I search for '<searchText>' on Google Images
    Then there are '<occurrences>' images that contain '<expectedSearchResult>'

    Examples:
      | searchText                         | occurrences | expectedSearchResult             |
      | most colourful monkey in the world | at least 3  | pink monkey                      |
      | boring zebra                       | 0           | orange with red polka dot zebras |