package com.amazonaws.serverless.payment.service.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "isValid", "accountNumber", "tikieUrl" })
public class IbanValidatorResponse {

	@JsonProperty("isValid")
	private Boolean isValid;
	
	@JsonProperty("accountNumber")
	private String accountNumber;
	
	@JsonProperty("tikieUrl")
	private String tikieUrl;

	/**
	 * @return the tikieUrl
	 */
	public String getTikieUrl() {
		return tikieUrl;
	}

	/**
	 * @param tikieUrl the tikieUrl to set
	 */
	public void setTikieUrl(String tikieUrl) {
		this.tikieUrl = tikieUrl;
	}

	@JsonProperty("isValid")
	public Boolean getIsValid() {
		return isValid;
	}

	@JsonProperty("isValid")
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
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