@DepositReversalFunction

Feature: Temenos T24 Deposit Reversal Feature

Scenario Outline: Temenos T24 Individual customer creation Test Scenario

Given pre req for deposit reversal "<TestcaseId>"
Given user logs into application
Then user input the command in the Reversal text box "ENQ AA.FIND.ARRANGEMENT.AD"
When title of login page is Find DEPOSIT Arrangements
Then user reverses financial activity "<TestcaseId>"

Examples:
	| TestcaseId | 
	| TC_01  |