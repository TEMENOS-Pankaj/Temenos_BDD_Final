@DepositRollSixFunction

Feature: Temenos T24 Six month Roll over Feature

Scenario Outline: Temenos T24 Individual customer creation Test Scenario

Given pre req for sixrollover application"<TestcaseId>"
Given user logs into application
Then user input the command in the text box "ENQ AA.FIND.ARRANGEMENT.AD"
When title of login page is Find Deposit Arrangements
Then user inputs the ROsix value in the req field"<TestcaseId>"
Then user authorisez the ROsix deal"<TestcaseId>"
Then user check ROsix accounting entries"<TestcaseId>"


Examples:
	| TestcaseId | 
	| TC_01  |