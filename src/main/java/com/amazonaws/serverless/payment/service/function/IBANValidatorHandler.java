package com.amazonaws.serverless.payment.service.function;

import org.apache.log4j.Logger;

import com.amazonaws.serverless.payment.service.iban.IBANValidationService;
import com.amazonaws.serverless.payment.service.pojo.IbanValidatorRequest;
import com.amazonaws.serverless.payment.service.pojo.IbanValidatorResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class IBANValidatorHandler implements RequestHandler<IbanValidatorRequest, IbanValidatorResponse> {

	private static final Logger log = Logger.getLogger(IBANValidatorHandler.class);

	private IBANValidationService ibanValidationService = new IBANValidationService();

	public IbanValidatorResponse handleRequest(IbanValidatorRequest ibanValidationRequest, Context context) {
		return ibanValidationService.isValidIban(ibanValidationRequest);
	}

}
