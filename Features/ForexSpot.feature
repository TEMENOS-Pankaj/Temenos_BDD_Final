@ForexSpotFunction

Feature: Temenos T24 Forex spot Feature

Scenario Outline: Temenos T24 Individual customer creation Test Scenario


Given pre_req_for_forexspot_application "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "FOREX,SPOTDEAL.IB I F3"
When title of login page is Forex
Then user inputs the Spot value in the req field"<TestcaseId>"
Then user authorisez the Spot deal"<TestcaseId>"
Then user check Spot accounting entries"<TestcaseId>"


Examples:
	| TestcaseId | 
	| TC_01  |