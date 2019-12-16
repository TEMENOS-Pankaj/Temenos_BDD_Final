@TakeLoanFunction

Feature: Temenos T24 Take Loan creation Feature

Scenario Outline: Temenos T24Take Loan Test Scenario

Given Input data for Take Loan  "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "AA.ARRANGEMENT.ACTIVITY I F3"
Then user inputs the mandatory value for Creation of Loan in the field "<TestcaseId>"
And user verifys the loan is sucessfully completed
And logout from the App
Then user logs into the application as a admin 
Then user Authorises the  Loan Creation

Examples:
	| TestcaseId | 
	| TC_01  |


