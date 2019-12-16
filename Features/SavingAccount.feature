@SavingAccountFunction

Feature: Temenos T24 Saving Account creation Feature

Scenario Outline: Temenos T24  to Create Saving Account Test Scenario

Given Input data for Create Saving Account  "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "ACCOUNT,SB.OPEN I F3"
When title of page is ACCOUNT
Then user inputs the mandatory value for Saving Account Creation in the field "<TestcaseId>"
And logout from the App
Then user logs into the application as a admin 
Then user Authorises the created Saving Account

Examples:
	| TestcaseId | 
	| TC_01  |


