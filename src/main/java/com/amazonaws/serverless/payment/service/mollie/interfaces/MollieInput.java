package com.amazonaws.serverless.payment.service.mollie.interfaces;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "amount", "description", "redirectUrl", "method", "issuer" })
public class MollieInput {

	@JsonProperty("amount")
	private String amount;
	@JsonProperty("description")
	private String description;
	@JsonProperty("redirectUrl")
	private String redirectUrl;
	@JsonProperty("method")
	private String method;
	@JsonProperty("issuer")
	private String issuer;

	@JsonProperty("amount")
	public String getAmount() {
		return amount;
	}

	@JsonProperty("amount")
	public void setAmount(String amount) {
		this.amount = amount;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("redirectUrl")
	public String getRedirectUrl() {
		return redirectUrl;
	}

	@JsonProperty("redirectUrl")
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	@JsonProperty("method")
	public String getMethod() {
		return method;
	}

	@JsonProperty("method")
	public void setMethod(String method) {
		this.method = method;
	}

	@JsonProperty("issuer")
	public String getIssuer() {
		return issuer;
	}

	@JsonProperty("issuer")
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

}
