
@ExistingTaxRate_deposit

Feature: Temenos T24 deposite Feature

Scenario Outline: Validate Interest Accrual for a deposit 

Given Input data for Existing Dep Customer "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "ENQ AA.FIND.ARRANGEMENT.AD"
Then user Enter Arrangement id click on Find button and Click on overview "<TestcaseId>"
Then user click on Additional link
Then user Verify Tax Rate is displayed


Examples:
	| TestcaseId |
	| TC_01	     |
	
