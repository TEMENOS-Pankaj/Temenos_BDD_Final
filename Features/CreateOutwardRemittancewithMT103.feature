@CreateOutwardRemittancewithMT103

Feature: Temenos T24 Create Outward Remittance with MT103 Feature

Scenario Outline: Temenos T24  to Create Saving Account Test Scenario

Given Input data for Create Outward Remittance with MTonezerothree  "<TestcaseId>"
Given user logs into application
Then user input the command in the text box "FUNDS.TRANSFER,OT102 I  F3"
Then user inputs the mandatory value for Creation of Outward Remittance with MTonezerothree
And logout from the App
Then user logs into the application as a admin 
Then user Authorises the created Created Outward Remittance with MTonezerothree

Examples:
	| TestcaseId | 
	| TC_01  |


