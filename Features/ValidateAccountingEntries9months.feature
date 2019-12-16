@Validation9MonthsDeposit

Feature: Temenos T24 Validation of Accounting Entries for Nine Months Deposit Feature

Scenario Outline: Temenos T24 Validation of Accounting Entries for Nine Months Deposit Scenario

Given Input data for Validation of Accounting Entries for Nine Months Deposit "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "ENQ TXN.ENTRY.MB"
Then user verifies accounting entries for Nine Months Deposit "<TestcaseId>"


Examples:
	| TestcaseId | 
	| TC_07  |
 

