package stepDefinitions;

import java.util.HashMap;
import java.util.Map;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.xml.XmlPath;
import utility.BaseClass;

public class FahrenheitToCelsius extends BaseClass{
	String BaseURL1 = "https://www.w3schools.com/xml";
	RequestSpecification httpRequest1;
	Response response1;
	String responseBody1;
	@Given("Get the FahrenheitToCelsius using Web Service")
	public void Get_the_FahrenheitToCelsius_using_Web_Service (){
		 RestAssured.baseURI =BaseURL1; 
	}
	@When("Get the FahrenheitToCelsius for (\\d+)")
	public void Get_the_FahrenheitToCelsius(int Far){
		String validationKey = "FahrenheitToCelsiusResult";
		String actualVal = "";
		String myEnvelope = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
				+ "<soap12:Body>" + "<FahrenheitToCelsius xmlns=\"https://www.w3schools.com/xml/\">"
				+ "<Fahrenheit>"+Far+"</Fahrenheit>" + "</FahrenheitToCelsius>" + "</soap12:Body>" + "</soap12:Envelope>";
		RestAssured.baseURI = BaseURL1;
		Map<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "text/xml; charset=UTF-8");
		headermap.put("SOAPAction", "https://www.w3schools.com/xml/FahrenheitToCelsius");
		headermap.put("Connection", "keep-alive");
		response1 = RestAssured.given().log().all().headers(headermap).body(myEnvelope)
				.post("https://www.w3schools.com/xml/tempconvert.asmx");
		XmlPath jsXpath = new XmlPath(response1.asString());
		actualVal = jsXpath.getString(validationKey);
		//System.out.println(actualVal);
		Re.addStepLog("FahrenheitToCelsius for "+Far+" is "+actualVal );
	}
	@Then("The status code is (\\d+)")
	public void verify_status_code(int statusCode){
		statusCode=response1.getStatusCode();
	}
}
