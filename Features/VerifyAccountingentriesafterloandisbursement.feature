@VerifyingAccountEntriesAfterloanDisbursement

Feature: Temenos T24 Verifying Account Entries After loan Disbursement

Scenario Outline: Temenos T24  to Verifying Accoun Entries After loan Disbursement Test Scenario

Given Input data for Verifying Account Entries After Loan Disbursement  "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "ENQ TXN.ENTRY.MB"
Then user Verify the Account Entries  Loan disbursement "<TestcaseId>"

Examples:
	| TestcaseId | 
	| TC_01  |


