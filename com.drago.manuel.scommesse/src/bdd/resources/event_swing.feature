Feature: Event Application Frame
  Specifications of the behavior of the Event Application Frame

  Scenario: The initial state of the view
    Given The database contains an event with home team "Juventus" and away team "Inter" and outcome "X" and odds "2.75"
    When The Student View is shown
    Then The list contains an element with home team "Juventus" and away team "Inter" and outcome "X" and odds "2.75"