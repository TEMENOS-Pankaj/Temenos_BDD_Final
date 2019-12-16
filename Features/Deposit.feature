@DepositFunction

Feature: Temenos T24 Deposit creation Feature

Scenario Outline: Temenos T24 Deposit creation Test Scenario

Given Input data for Deposit "<TestcaseId>"
Given user logs into application
Then user navigates to Deposit page
When title of page is AA ARRANGEMENT ACTIVITY
Then user inputs Deposit values in fields "<TestcaseId>"
And logout from the App
Then user logs into the application as a admin 
Then user input the command in the text box "ENQ AA.ARRANGEMENT.ACTIVITY-NAU"
Then user authorises the deposit created "<TestcaseId>"
And logout from the App
Then user logs into the application as a admin 
Then user validates the deposit "<TestcaseId>"
And logout from the App
Given user logs into application
Then user input the command in the text box "ENQ TXN.ENTRY.MB"
Then user verifies transaction entries "<TestcaseId>"

Examples:
	| TestcaseId | 
	| TC_01  |


