package com.amazonaws.serverless.payment.service.mollie.interfaces;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
"paymentUrl",
"redirectUrl"
})
public class Links {

@JsonProperty("paymentUrl")
private String paymentUrl;
@JsonProperty("redirectUrl")
private String redirectUrl;

@JsonProperty("paymentUrl")
public String getPaymentUrl() {
return paymentUrl;
}

@JsonProperty("paymentUrl")
public void setPaymentUrl(String paymentUrl) {
this.paymentUrl = paymentUrl;
}

@JsonProperty("redirectUrl")
public String getRedirectUrl() {
return redirectUrl;
}

@JsonProperty("redirectUrl")
public void setRedirectUrl(String redirectUrl) {
this.redirectUrl = redirectUrl;
}

}
