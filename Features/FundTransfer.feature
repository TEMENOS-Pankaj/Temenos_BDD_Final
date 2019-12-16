@FundTransfer

Feature: Fund transfer from one account to saving account


@FundTransferAccToAcc_TC1
Scenario Outline: Temenos T24 Fund transfer from one account to saving account
Given Input data for Fund Transfer Acc To Acc "<TestcaseId>"
Given user logs into application
And Initial Balance of Credit and Debit Account "<TestcaseId>"
Then fund transfer Between Accounts "<TestcaseId>"
And logout from the App
Then user logs into the application as a admin 
And Authorize the transaction by Admin
And logout from the App
Given user logs into application 
Then user input the command in the text box "ENQ STMT.ENT.TODAY"
And Final Balance of Credit Account and Debit Account

Examples:
	| TestcaseId | 
	| TC_01  |
 
@FundTransferAccToAcc_TC2
Scenario Outline: Temenos T24 Fund transfer from one account to saving account
Given Input data for Fund Transfer Acc To Acc "<TestcaseId>"
Given user logs into application
And Initial Balance of Credit and Debit Account "<TestcaseId>"
Then fund transfer Between Accounts "<TestcaseId>"
And logout from the App
Then user logs into the application as a admin 
And Authorize the transaction by Admin
And logout from the App
Given user logs into application 
Then user input the command in the text box "ENQ STMT.ENT.TODAY"
And Final Balance of Credit Account and Debit Account

Examples:
	| TestcaseId | 
	| TC_01  |
	
	
	
	
@FundTransferAccToAcc2
Scenario Outline: Temenos T24 Fund transfer from one account to saving account
Given Input data for Fund Transfer Acc To Acc "<TestcaseId>"
Given user logs into application
And Initial Balance of Credit and Debit Account "<TestcaseId>"
Then fund transfer Between Accounts "<TestcaseId>"
And logout from the App
Then user logs into the application as a admin 
And Authorize the transaction by Admin
And logout from the App
Given user logs into application 
Then user input the command in the text box "ENQ STMT.ENT.TODAY"
And Final Balance of Credit Account and Debit Account

Examples:
	| TestcaseId | 
	| TC_01  |