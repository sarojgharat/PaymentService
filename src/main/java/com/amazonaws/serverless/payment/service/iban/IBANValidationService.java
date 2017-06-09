package com.amazonaws.serverless.payment.service.iban;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.amazonaws.serverless.payment.service.pojo.IbanValidatorRequest;
import com.amazonaws.serverless.payment.service.pojo.IbanValidatorResponse;
import com.amazonaws.serverless.payment.service.token.TokenAcquirerService;
import com.amazonaws.serverless.payment.service.utils.SerializationUtils;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.SSLConfig;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

public class IBANValidationService {

	public static void main(String[] args) {
		IbanValidatorRequest ibanValidationRequest = new IbanValidatorRequest();
		ibanValidationRequest.setIban("NL83ABNA059753613");
		IbanValidatorResponse response = new IBANValidationService().isValidIban(ibanValidationRequest);
		System.out.println("SECOND===" + response.getTikieUrl());
	}

	public IbanValidatorResponse isValidIban(IbanValidatorRequest ibanValidatorRequest) {

		TokenAcquirerService acquirer = new TokenAcquirerService();
		String token = acquirer.getToken();
		Header header = new Header("Authorization", "Bearer " + token);

		String baseURI = "https://api-sandbox.abnamro.com/v1/iban/" + ibanValidatorRequest + "/validity";

		Response ibanValidResponse = given()
				.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).header(header).when()
				.get(baseURI);
		
		
		SerializationUtils util = new SerializationUtils();

		IbanValidatorResponse ibanValidatorResponse = null;
		try {
			ibanValidatorResponse = (IbanValidatorResponse) util.deserializeJson(ibanValidResponse,
					IbanValidatorResponse.class);
			
			System.out.println("First===============" + ibanValidatorResponse);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ibanValidatorResponse.setTikieUrl("https://tikkie.me/pay/ro7ko9mq2e4l74dgp2bi");
		
		//System.out.println(ibanValidatorResponse.getBody().asString());
		return ibanValidatorResponse;

	}

}
