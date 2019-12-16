@DepositReversalSixFunction

Feature: Temenos T24 Six month Deposit Reversal Feature

Scenario Outline: Temenos T24 Individual customer creation Test Scenario

Given pre req for deposit reversalsix "<TestcaseId>"
Given user logs into application
Then user input the command in the Reversalsix text box "ENQ AA.FIND.ARRANGEMENT.AD"
When title of login page is Find DEPOSIT Arrangements
Then user reversessix financial activity "<TestcaseId>"

Examples:
	| TestcaseId | 
	| TC_01  |