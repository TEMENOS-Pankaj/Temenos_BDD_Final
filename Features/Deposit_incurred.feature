@DepositIncurredFunction


Feature: Temenos T24 deposit Feature

Scenario Outline: Validate Interest Accrual for a deposit 

Given Input data for Dep Customer "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "ENQ AA.FIND.ARRANGEMENT.AD"
Then user Enter the Arrangment Id in Search and click the find "<TestcaseId>" 
Then user Click on the Search icon
Then user Validates Interest Accrual for a deposit

Examples:
	| TestcaseId |
	| TC_01	     |
	
	

	
	