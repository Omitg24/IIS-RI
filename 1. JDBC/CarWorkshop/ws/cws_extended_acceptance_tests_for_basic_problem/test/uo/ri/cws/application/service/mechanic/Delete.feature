Feature: Delete a mechanic
  As a Manager
  I want to delete a mechanic
  in case have not been used to keep the system clean

  Scenario: Delete an existing unused mechanic
    Given a mechanic
    When I remove the mechanic
    Then the mechanic no longer exists
    
  Scenario: Try to remove a non existing mechanic
    When I try to remove a non existent mechanic
    Then an error happens with an explaining message

  Scenario: Try to remove a used mechanic with work orders
    Given a mechanic with work orders registered
    When I try to remove the mechanic
    Then an error happens with an explaining message

 Scenario Outline: Try to delete a mechanic with null argument
    When I try to remove a mechanic with null argument
    Then argument is rejected with an explaining message

  Scenario Outline: Try to delete a mechanic with wrong fields
    When I try to delete a mechanic with <id> 
    Then argument is rejected with an explaining message

    Examples: 
      | id    |
      | "   " |
      | ""    |