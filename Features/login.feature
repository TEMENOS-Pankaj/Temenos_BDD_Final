@loginFunction

Feature: Temenos T24 Login Feature

Scenario Outline: Temenos T24 Login Test Scenario

Given user Login Page
When title of login page is Temenos
Then user enters "<username>" and "<password>"
Then user clicks on login button
Then user is on home page
Then Close the browser


Examples:
	| username | password |
	| LTIUSER10  | 123456 |
	
	
