@LoanDisbursementFunction

Feature: Temenos T24 Loan Disbursement Feature

Scenario Outline: Temenos T24 Loan Disbursement  Test Scenario

Given Input data for Loan Disbursement "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "FUNDS.TRANSFER I F3"
Then user inputs the mandatory value for Loan disbursement "<TestcaseId>"
And user verifys the loan is disbursed sucessfully completed
And logout from the App
Then user logs into the application as a admin
Then user Authorises the  Loan Disbursement "<TestcaseId>"



Examples:
	| TestcaseId | 
	| TC_01  |


