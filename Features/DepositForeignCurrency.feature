@DepositForeignCurrency

Feature: Temenos T24 Deposit Foreign Currency creation Feature

Scenario Outline: Temenos T24 Deposit Foreign Currency Test Scenario

Given Input data for Deposit Foreign Currency "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "TELLER,LCY.CASHIN.FCY.ACCT I F3"
When title of login page is TELLER
Then user inputs the value in the Deposit Foreign Currency fields "<TestcaseId>" 
And logout from the App
Then user logs into the application as a admin 
Then user authorises the deposit created
And logout from the App
Then user logs into application
Then user input the command in the text box "ENQ TXN.ENTRY.MB"
Then user verifies accouting entries for Deposit Foreign Currency 


Examples:
	| TestcaseId | 
	| TC_01  |
 

