@TellerCreationFunction

Feature: Temenos T24 Teller Creation Feature

Scenario Outline: Temenos T24 Teller Creation Test Scenario

Given Input data for Teller Creation "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "TELLER.ID,CREATE"
When title of login page is TELLER ID
Then user inputs the value in the Teller fields "<TestcaseId>" 


Examples:
	| TestcaseId | 
	| TC_01  |
 

