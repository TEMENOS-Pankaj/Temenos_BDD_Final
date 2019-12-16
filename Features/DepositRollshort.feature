@DepositRollshortFunction

Feature: Temenos T24 short term Roll over Feature

Scenario Outline: Temenos T24 Individual customer creation Test Scenario

Given pre req for shortrollover application"<TestcaseId>"
Given user logs into application
Then user input the command in the text box "ENQ AA.FIND.ARRANGEMENT.AD"
When title of login page is Find DEPOSIT Arrangements
Then user inputs the ROshort value in the req field"<TestcaseId>"
Then user authorisez the ROshort deal"<TestcaseId>"
Then user check ROshort accounting entries"<TestcaseId>"


Examples:
	| TestcaseId | 
	| TC_01  |