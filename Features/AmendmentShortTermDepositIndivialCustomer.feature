@AmendmentofShortTermDepositForIndivialCustomer

Feature: Temenos T24 Amendment of Short Term Deposit For Indivial Customer Feature

Scenario Outline: Temenos T24Take Loan Test Scenario

Given Input data for Amendment of Short Term Deposit  "<TestcaseId>"
Given user logs into application
Then user navigates into the Amend DepositPage "<TestcaseId>"
Then Input the data to Amend Deposit for short term"<TestcaseId>"
Then user logs into the application as a admin 
Then user Authorises the Short Term deposit creation

Examples:
	| TestcaseId | 
	| TC_01  |


