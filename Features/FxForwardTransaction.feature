@FxForwardFunction

Feature: Temenos T24 FxForward Transaction Feature

Scenario Outline: Temenos T24 FxForward Transaction Test Scenario

Given Input data for FxForward "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "FOREX,FORWARDDEAL.IB I F3"
When title of login page is FOREX
Then user inputs the value in the forex fields "<TestcaseId>" 
And logout from the App
Then user logs into the application as a admin 
Then user authorises the deal for FxForward
And logout from the App
Then user logs into application
Then user input the command in the text box "ENQ TXN.ENTRY.MB"
Then user verifies accouting entries for FxForward "<TestcaseId>"

Examples:
	| TestcaseId | 
	| TC_01  |
 

