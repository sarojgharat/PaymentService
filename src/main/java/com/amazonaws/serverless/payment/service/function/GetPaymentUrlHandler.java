package com.amazonaws.serverless.payment.service.function;

import org.apache.log4j.Logger;

import com.amazonaws.serverless.payment.service.mollie.MolliePaymentService;
import com.amazonaws.serverless.payment.service.mollie.interfaces.MollieInput;
import com.amazonaws.serverless.payment.service.mollie.interfaces.MollieResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetPaymentUrlHandler implements RequestHandler<MollieInput, String> {

	private static final Logger log = Logger.getLogger(GetPaymentUrlHandler.class);

	private MolliePaymentService molliePaymentService = new MolliePaymentService();

	public String handleRequest(MollieInput mollieInput, Context context) {

		String qrCodeUrlFromS3 = "";

		try {
			MollieResponse mollieResponse = molliePaymentService.createMolliePayment(mollieInput);
			return mollieResponse.getLinks().getPaymentUrl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return qrCodeUrlFromS3;
	}

}
