@Verficication9MonthsDeposit

Feature: Temenos T24 Verification of Nine Months Deposit Feature

Scenario Outline: Temenos T24 Verification of Nine Months Deposit Scenario

Given Input data for Verification of Nine Months Deposit "<TestcaseId>"
Given user logs into application
Then user navigates to the Arrangement Overview Page "<TestcaseId>"
When title of login page is Arrangement Overview Deposits Model Bank
Then user verifies the status of the deposit "<TestcaseId>" 


Examples:
	| TestcaseId | 
	| TC_01  |
 

