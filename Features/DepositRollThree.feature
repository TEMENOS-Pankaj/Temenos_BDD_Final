@DepositRollThreeFunction

Feature: Temenos T24 Three month Roll over Feature

Scenario Outline: Temenos T24 Individual customer creation Test Scenario

Given pre_req_for_threerollover_application "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "ENQ AA.FIND.ARRANGEMENT.AD"
When title of login page is Find Deposit Arrangements
Then user inputs the ROThree value in the req field "<TestcaseId>"
Then user authorisez the ROThree deal "<TestcaseId>"
Then user check ROThree accounting entries "<TestcaseId>"


Examples:
	| TestcaseId | 
	| TC_01  |