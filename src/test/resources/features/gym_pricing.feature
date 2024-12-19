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
  Scenario: Early bird discount
    Given I am on the gym pricing calculator page
    When I select day "Monday"
    And I enter age "30"
    And I select time "before7"
    And I am not a member
    Then the price should be "160.00"

  Scenario: Senior discount
    Given I am on the gym pricing calculator page
    When I select day "Monday"
    And I enter age "65"
    And I select time "after7"
    And I am not a member
    Then the price should be "160.00"

  Scenario: Child discount
    Given I am on the gym pricing calculator page
    When I select day "Monday"
    And I enter age "10"
    And I select time "after7"
    And I am not a member
    Then the price should be "160.00"

  Scenario: Invalid member ID
    Given I am on the gym pricing calculator page
    When I select day "Monday"
    And I enter age "30"
    And I am a member with id "INVALID-123"
    Then I should see member ID error message

  Scenario: Weekend member price
    Given I am on the gym pricing calculator page
    When I select day "Saturday"
    And I enter age "30"
    And I select time "after7"
    And I am a member with id "IECS-123"
    Then the price should be "125.00"

  Scenario: Maximum age limit
    Given I am on the gym pricing calculator page
    When I enter age "76"
    Then I should see age error message

  Scenario: Reset functionality
    Given I am on the gym pricing calculator page
    When I enter age "30"
    And I click reset button
    Then all fields should be cleared