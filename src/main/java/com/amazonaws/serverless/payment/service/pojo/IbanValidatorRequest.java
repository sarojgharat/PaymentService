package com.amazonaws.serverless.payment.service.pojo;

public class IbanValidatorRequest {
	
	private String iban;

	/**
	 * @return the iban
	 */
	public String getIban() {
		return iban;
	}

	/**
	 * @param iban the iban to set
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}

}
