Feature: Event Application Frame
  Specifications of the behavior of the Event Application Frame

  Background:
    Given The database contains an event
    And The Event View is shown
    
  Scenario: Add a new event
    Given The user provides event data in the text fields
    When The user clicks the "Add" button
    Then The list contains the new event