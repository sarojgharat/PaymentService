package com.amazonaws.serverless.payment.service.token;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.amazonaws.serverless.payment.service.utils.SerializationUtils;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.SSLConfig;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

public class TokenAcquirerService {

	public String getToken() {

		String baseURI = "https://api-sandbox.abnamro.com/v1/oauth/token";
		Header header = new Header("Authorization",
				"Basic REttTkp2RzlRNHdIUjdERG9la3FjTnp2cDI5RE5MZEo6eU12QzdCVkZwN1gySURzRw==");
		/*
		 * Response authenticationTokenResponse =
		 * given().config(RestAssured.config().sslConfig( new
		 * SSLConfig().relaxedHTTPSValidation())).header(header).contentType(
		 * "application/x-www-form-urlencoded")
		 * .body("grant_type=client_credential&accountNumber=NL83ABNA0597536138"
		 * ).when().post(baseURI);
		 */
		Response authenticationTokenResponse = given()
				.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).header(header)
				.contentType("application/x-www-form-urlencoded").formParam("grant_type", "client_credentials")
				.formParam("accountNumber", "NL83ABNA0597536138").request().post(baseURI);

		SerializationUtils util = new SerializationUtils();

		System.out.println(authenticationTokenResponse.getBody().asString());

		TokenResponse tokenResponse = null;
		try {
			tokenResponse = (TokenResponse) util.deserializeJson(authenticationTokenResponse, TokenResponse.class);
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

		return tokenResponse.getAccess_token();
	}

}
