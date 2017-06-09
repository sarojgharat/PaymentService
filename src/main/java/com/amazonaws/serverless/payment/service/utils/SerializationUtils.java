package com.amazonaws.serverless.payment.service.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.jayway.restassured.response.Response;

public class SerializationUtils {
	public Object deserializeJson(Response restAssuredResponse, Class<?> cls)
			throws JsonParseException, JsonMappingException, IOException {

		String outputJson = restAssuredResponse.getBody().asString();

		org.codehaus.jackson.map.ObjectMapper objectMapper = new org.codehaus.jackson.map.ObjectMapper();
		Object resource = objectMapper.readValue(outputJson, cls);
		return resource;

	}
}
