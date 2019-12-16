@MoneyMarketFunction

Feature: Temenos T24 Money Market Feature

Scenario Outline: Temenos T24 Individual customer creation Test Scenario

Given pre_req_for_moneymarket_application "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "MM.MONEY.MARKET,PLACE.CALL I F3"
When title of login page is MONEY MARKET
Then user inputs the moneymarket value in the req field "<TestcaseId>"
Then user authorisez moneymarket deal
Then user check moneymarket accounting entries


Examples:
	| TestcaseId | 
	| TC_01  |