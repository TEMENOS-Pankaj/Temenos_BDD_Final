package stepDefinitions;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.BaseClass;

public class GetWeatherSteps extends BaseClass{
	String BaseURL = "http://restapi.demoqa.com/utilities/weather/city";
	RequestSpecification httpRequest;
	Response response;
	String responseBody;
	@Given("City Exist with the CityList")
	public void City_Exist_with_the_CityList (){
		 RestAssured.baseURI =BaseURL; 
	}
	@When("Get the Weather for the City (.*)")
	public void Get_the_Weather_for_the_City(String City){
		 httpRequest=RestAssured.given();
		 response=httpRequest.get(City);
		 responseBody=response.getBody().asString();
		 Re.addStepLog(responseBody +"<br>");
	}
	@Then("Check status code is (\\d+)")
	public void verify_status_code(int statusCode){
		statusCode=response.getStatusCode();
	}
}
