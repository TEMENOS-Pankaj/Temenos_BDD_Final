@AmendmentofThreeMonthsDepositForIndivialCustomer

Feature: Temenos T24 Amendment of Three Months Deposit For Indivial Customer Feature

Scenario Outline: Temenos T24Take Loan Test Scenario

Given Input data for Amendment of Three Months Deposit  "<TestcaseId>"
Given user logs into application
Then user navigates into the Amend Three Month Deposit Page "<TestcaseId>"
Then Input the data to Amend Deposit for Three Months"<TestcaseId>"
Then user logs into the application as a admin 
Then user Authorises the Three Month deposit creation

Examples:
	| TestcaseId | 
	| TC_01  |


