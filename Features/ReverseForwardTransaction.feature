@ReverseForwardFunction

Feature: Temenos T24 Reverse Forward Transaction Feature

Scenario Outline: Temenos T24 Reverse Forward Transaction Test Scenario

Given Input data for ReverseForward "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "ENQ FX.REVERSE"
When title of login page is Reverse Forex Deals
Then user inputs the value in the reverse forex fields "<TestcaseId>" 
And logout from the App
Then user logs into application
Then user input the command in the text box "ENQ TXN.ENTRY.MB"
Then user verifies accouting entries for FxReverse


Examples:
	| TestcaseId | 
	| TC_01  |
 

