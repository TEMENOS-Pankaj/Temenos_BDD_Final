@SecurityBuyOrderFunction

Feature: Temenos T24 Security Buy Order Feature

Scenario Outline: Temenos T24 Security Buy Order Test Scenario

Given Input data for Security Buy Order "<TestcaseId>"
Given user logs into application 
Then user onboards the customer "<TestcaseId>"
And logout from the App
Then user logs into the application as a admin 
Then user authorizes the onboarded customer 
And logout from the App
Then user logs into application 
Then user creates Portfolio for the customer
And logout from the App
Given user logs into the application as a admin 
Then user input the command in the text box "SOO,BUY.STP I F3"
Then user creates Buy Order
And logout from the App
Given user logs into the application as a admin 
Then user executes the order
And logout from the App
Given user logs into application
Then user authorizes the order "<TestcaseId>"
And logout from the App
Then user logs into application
Then user completes the trade


Examples:
	| TestcaseId | 
	| TC_01  |


