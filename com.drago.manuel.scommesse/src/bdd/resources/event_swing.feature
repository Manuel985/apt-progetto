Feature: Event Application Frame
  Specifications of the behavior of the Event Application Frame

  Background:
    Given The database contains an event
    And The Event View is shown
    
  Scenario: Add a new event
    Given The user provides event data in the text fields
    When The user clicks the "Add" button
    Then The list contains the new event
    
  Scenario: Delete an event
    Given The user selects an event from the list
    When The user clicks the "Delete" button
    Then The event is removed from the list