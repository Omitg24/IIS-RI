Feature: Find a mechanic by dni
	As a Manager
	I need to recover a mechanic by dni
	To see its details

  Scenario Outline: Find an existing mechanic
    Given a mechanic
	  When I look for mechanic by dni
    Then I get mechanic

  Scenario: Try to find a mechanic with non existing dni
		When I try to find a mechanic by a non existent dni 
		Then mechanic not found

  Scenario Outline: Try to find a mechanic with null argument
    When I try to find a mechanic with null dni
    Then argument is rejected with an explaining message

  Scenario Outline: Try to find a mechanic with wrong parameters
    When I look for a mechanic by wrong dni <dni>
    Then argument is rejected with an explaining message

    Examples: 
      | dni   			| 
			| "" 					| 
      | "         "	|		