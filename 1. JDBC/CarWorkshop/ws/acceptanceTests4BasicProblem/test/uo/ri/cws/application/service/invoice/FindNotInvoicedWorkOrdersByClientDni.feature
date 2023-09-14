Feature: Find not yet invoiced workorders for a client by dni
	As a Cashier
	I need to find not yet invoiced workorders
	To generate the invoice

  Scenario Outline: Find not invoiced workorders by dni
    Given a client registered
    And a vehicle
    And a list of several finished workorder ids
    When I search not invoiced workorders by dni
    Then I get only finished workorders

  Scenario Outline: Find not invoiced workorders by dni with no workorders
    Given a client registered
	  When I search not invoiced workorders by dni
    Then I get an empty list
    
  Scenario Outline: Find not invoiced workorders by dni with no finished workorders
    Given a client registered
    And a vehicle
    And a list of several finished workorder ids
    And one INVOICED workorder  
	  When I search not invoiced workorders by dni
    Then I get only finished workorders
    
    
  Scenario: Try to find workorders for a non existent client
		When I try to find workorders for a non existent dni 
    Then an error happens with an explaining message

  Scenario Outline: Try to find workorders with null argument
    When I try to find workorders with null dni
    Then argument is rejected with an explaining message

  Scenario Outline: Try to find a workorders with wrong parameters
    When I look for a workorder by empty dni 
    Then argument is rejected with an explaining message