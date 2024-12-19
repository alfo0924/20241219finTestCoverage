Feature: Gym Pricing Calculator

  Scenario: Regular weekday pricing
    Given I am on the gym pricing calculator page
    When I select day "Monday"
    And I enter age "30"
    And I select time "after7"
    And I am not a member
    Then the price should be "200.00"

  Scenario: Weekend pricing
    Given I am on the gym pricing calculator page
    When I select day "Saturday"
    And I enter age "30"
    And I select time "after7"
    And I am not a member
    Then the price should be "250.00"

  Scenario: Member discount
    Given I am on the gym pricing calculator page
    When I select day "Monday"
    And I enter age "30"
    And I select time "after7"
    And I am a member with id "IECS-123"
    Then the price should be "100.00"

  Scenario: Invalid age
    Given I am on the gym pricing calculator page
    When I enter age "2"
    Then I should see age error message
