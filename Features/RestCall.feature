@GetWeather
Feature: API Test Rest Call - Get Weather By City
  Scenario: User calls web service to Weather By City
	Given City Exist with the CityList 
	When Get the Weather for the City /Villupuram
	Then Check status code is 200