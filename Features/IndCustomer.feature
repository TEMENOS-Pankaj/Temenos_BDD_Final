@IndCustomerFunction

Feature: Temenos T24 Individual customer creation Feature

Scenario Outline: Temenos T24 Individual customer creation Test Scenario

Given Input data for Ind Customer "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "CUSTOMER,INPUT F3"
When title of login page is CUSTOMER
Then user inputs the value in the field "<TestcaseId>" 
And logout from the App
Then user logs into the application as a admin 
Then user authorises the deal

Examples:
	| TestcaseId | 
	| TC_01  |
 

