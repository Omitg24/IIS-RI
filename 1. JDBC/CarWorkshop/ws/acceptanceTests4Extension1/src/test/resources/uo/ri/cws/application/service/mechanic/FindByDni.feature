Feature: Find a mechanic by dni
	As a Manager
	I need to recover a mechanic by dni
	To see its details


  Scenario Outline: Find an existing mechanic
      Given the following mechanic 
        | dni  	|  name | surname |
      | DNI-1 | Name-1 | Surname-1 |
	    When i search mechanic with the following dni
	             | dni  	|  
      | DNI-1 |
	    Then one mechanic is found
      And I get the following mechanic
       | mechanic | 
       | DNI-1,Name-1,Surname-1 | 
 
  Scenario: Search a mechanic with non existing dni
		When Search a mechanic by a non existent dni 
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