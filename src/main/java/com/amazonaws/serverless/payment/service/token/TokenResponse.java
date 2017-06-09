package com.amazonaws.serverless.payment.service.token;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "access_token", "expires_in", "scope", "token_type", "accountNumber" })
public class TokenResponse {

	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("expires_in")
	private String expiresIn;
	@JsonProperty("scope")
	private String scope;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("accountNumber")
	private String accountNumber;

	@JsonProperty("access_token")
	public String getAccess_token() {
		return accessToken;
	}

	@JsonProperty("access_token")
	public void setAccess_token(String accessToken) {
		this.accessToken = accessToken;
	}

	@JsonProperty("expires_in")
	public String getExpires_in() {
		return expiresIn;
	}

	@JsonProperty("expires_in")
	public void setExpires_in(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	@JsonProperty("scope")
	public String getScope() {
		return scope;
	}

	@JsonProperty("scope")
	public void setScope(String scope) {
		this.scope = scope;
	}

	@JsonProperty("token_type")
	public String getToken_type() {
		return tokenType;
	}

	@JsonProperty("token_type")
	public void setToken_type(String tokenType) {
		this.tokenType = tokenType;
	}

	@JsonProperty("accountNumber")
	public String getAccountNumber() {
		return accountNumber;
	}

	@JsonProperty("accountNumber")
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}