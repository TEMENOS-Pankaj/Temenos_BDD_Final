@FahrenheitToCelsius
Feature: API Test Soap Call - FahrenheitToCelsius
  Scenario: User calls web service to Conver the FahrenheitToCelsius
	Given Get the FahrenheitToCelsius using Web Service
	When  Get the FahrenheitToCelsius for 150
	Then The status code is 200