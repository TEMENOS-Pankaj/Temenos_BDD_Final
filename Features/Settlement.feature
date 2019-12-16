@SettlementFunction

Feature: Temenos T24 Settlement Feature

Scenario Outline: Temenos T24 Settlement Scenario

Given Input data for Settlement "<TestcaseId>"
Given user logs into application
Then user navigates to Settlement page
Then user inputs Settlement values in fields "<TestcaseId>"
And logout from the App
Then user logs into the application as a admin 
Then user input the command in the text box "ENQ SC.HOLD.SUM.BY.PF"
Then user checks position "<TestcaseId>"


Examples:
	| TestcaseId | 
	| TC_01  |


