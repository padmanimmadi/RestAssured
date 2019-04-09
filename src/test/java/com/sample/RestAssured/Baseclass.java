package com.sample.RestAssured;

import org.omg.CORBA.Request;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Baseclass {
	
	RequestSpecification request;
	@BeforeClass
	public void setup()
	{
		//read from property file
		RestAssured.baseURI="http://pqalngwos303.corp.intuit.net:8080/";
		
	}
	@BeforeMethod
	public void createRequest()
	{
		request=RestAssured.given();
		request.header("intuit_offeringid","6");
		request.header("intuit_providerid","6");
		request.header("intuit_tid","NGWI-PFM-sanity-020");
		request.header("Authorization","Authorization");
		request.header("intuit_ficds_fi_url","http://avatar.corp.intuit.net/SuperMock/data/edit/1095A-test.jsp");
		request.header("intuit_appid","ngwi");
		request.header("intuit_app_secret","ngwisecretkey");
		request.header("Content-Type","application/json");
		request.header("intuit_fds_agent_config_url","https://financialprovider.platform.intuit.net/v1/providers/995c9bc3-a155-498f-9e3b-bf06e8d0e331/channels/webIntegration/41b267ee-fcd7-4bc3-8757-dae4fc43223e?agentType=NGWI");
		
	}
	
	public void execute_Request()
	{
		String reqBody="{  \n" + 
				"   \"Accounts\":{  \n" + 
				"      \"Login\":{  \n" + 
				"         \"ExtraNVPairs\":[\n" + 
				"            {\n" + 
				"               \"Name\":\"Banking Userid\",\n" + 
				"               \"Value\":\"SANITY_TEST\"\n" + 
				"            },\n" + 
				"            {\n" + 
				"               \"Name\":\"Banking Password\",\n" + 
				"               \"Value\":\"ihp\"\n" + 
				"            }\n" + 
				"         ]\n" + 
				"      },\n" + 
				"      \"ExecutionData\":{  \n" + 
				"         \"CustomerId\":123456,\n" + 
				"         \"InstitutionId\":200000,\n" + 
				"         \"DataMode\":\"PFM\",\n" + 
				"         \"ExtraNVPairs\":[],\n" + 
				"         \"ScriptName\":\"ngwitestbank.scr\"\n" + 
				"      }\n" + 
				"   }\n" + 
				"}";
		
		
		request.body(reqBody);
		String url="nextgenweb/nxgen/wi/1.0/accounts?action=discovery";
		postRequest(request,url);
		
	}
	
	public void postRequest(RequestSpecification request,String url)
	{
		Response rsp=request.post(url);
		
		//Assert the requests
		System.out.println("status code "+rsp.getStatusCode());
		System.out.println("Response body "+rsp.asString());
		System.out.println("**********************");
		System.out.println(rsp.jsonPath().get("Accounts.Error.ErrorCode"));
		System.out.println(rsp.jsonPath().get("Accounts.Error.ErrorMessage"));
		System.out.println(rsp.jsonPath().get("Accounts.Error.TypeOfError"));
	}
	
	
	@AfterMethod
	public void teardown()
	{
		request=null;
	}
	
	public void getRequest(String url)
	{
		
	}

}
