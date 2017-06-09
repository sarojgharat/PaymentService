package com.amazonaws.serverless.payment.service.mollie;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;

import com.amazonaws.serverless.payment.service.mollie.interfaces.MollieInput;
import com.amazonaws.serverless.payment.service.mollie.interfaces.MollieResponse;
import com.amazonaws.serverless.payment.service.utils.SerializationUtils;
import com.google.zxing.WriterException;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

/**
 *
 * Static constants class
 *
 */
public class MolliePaymentService {

	public MolliePaymentService() {

	} // callers can't instantiate class itself

	public static void main(String args[]) throws ClientProtocolException, IOException, JSONException, WriterException {

		MollieInput mollieInput = new MollieInput();
		mollieInput.setAmount("1");
		mollieInput.setDescription("Joy");
		mollieInput.setRedirectUrl("http://localhost.com");
		mollieInput.setMethod("ideal");
		mollieInput.setIssuer("ideal_ABNANL2A");
		new MolliePaymentService().createMolliePayment(mollieInput);
	}

	public MollieResponse createMolliePayment(MollieInput mollieInput)
			throws ClientProtocolException, IOException, JSONException, WriterException {

		String baseURI = "https://api.mollie.nl/v1/payments";
		System.out.println("baseURI:" + baseURI);
		Header header = new Header("Authorization", "Bearer live_gUckbwynvb8WxEEhW73HJEgPCdt98f");

		Response restAssuredLogonResponse = given().header(header).contentType("application/json").body(mollieInput).when()
				.post(baseURI);
		System.out.println("==>>>>" + restAssuredLogonResponse.getStatusCode());

		String outputJsonForLogonChallenge = restAssuredLogonResponse.getBody().asString();

		System.out.println(outputJsonForLogonChallenge);

		SerializationUtils serializationUtils = new SerializationUtils();

		MollieResponse mollieResponse = null;
		try {
			mollieResponse = (MollieResponse) serializationUtils.deserializeJson(restAssuredLogonResponse,
					MollieResponse.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(mollieResponse);
		System.out.println(mollieResponse.getLinks().getPaymentUrl());
		
		return mollieResponse;

	}

}
