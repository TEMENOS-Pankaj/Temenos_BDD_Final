@FundTransferAccFunction

Feature: Temenos T24 Fund Transfer Deposit Feature

Scenario Outline: Temenos T24 Individual customer creation Test Scenario

Given pre req for FundTransfer "<TestcaseId>"
Given user logs into application
Then user input the command in the fund transfer text box "FT,ACTR.FTHP I F3"
When title of login page is FUNDS.TRANSFER
Then user input FUND TRANSFER detail "<TestcaseId>"
Then user authorises Fund Transfer

Examples:
	| TestcaseId | 
	| TC_01  |