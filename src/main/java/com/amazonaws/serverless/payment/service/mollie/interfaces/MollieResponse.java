package com.amazonaws.serverless.payment.service.mollie.interfaces;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
"resource",
"id",
"mode",
"createdDatetime",
"status",
"expiryPeriod",
"amount",
"description",
"method",
"metadata",
"details",
"issuer",
"profileId",
"links"
})
public class MollieResponse {

@JsonProperty("resource")
private String resource;
@JsonProperty("id")
private String id;
@JsonProperty("mode")
private String mode;
@JsonProperty("createdDatetime")
private String createdDatetime;
@JsonProperty("status")
private String status;
@JsonProperty("expiryPeriod")
private String expiryPeriod;
@JsonProperty("amount")
private String amount;
@JsonProperty("description")
private String description;
@JsonProperty("method")
private String method;
@JsonProperty("metadata")
private Object metadata;
@JsonProperty("details")
private Object details;
@JsonProperty("issuer")
private String issuer;
@JsonProperty("profileId")
private String profileId;
@JsonProperty("links")
private Links links;

@JsonProperty("resource")
public String getResource() {
return resource;
}

@JsonProperty("resource")
public void setResource(String resource) {
this.resource = resource;
}

@JsonProperty("id")
public String getId() {
return id;
}

@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

@JsonProperty("mode")
public String getMode() {
return mode;
}

@JsonProperty("mode")
public void setMode(String mode) {
this.mode = mode;
}

@JsonProperty("createdDatetime")
public String getCreatedDatetime() {
return createdDatetime;
}

@JsonProperty("createdDatetime")
public void setCreatedDatetime(String createdDatetime) {
this.createdDatetime = createdDatetime;
}

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonProperty("expiryPeriod")
public String getExpiryPeriod() {
return expiryPeriod;
}

@JsonProperty("expiryPeriod")
public void setExpiryPeriod(String expiryPeriod) {
this.expiryPeriod = expiryPeriod;
}

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

@JsonProperty("method")
public String getMethod() {
return method;
}

@JsonProperty("method")
public void setMethod(String method) {
this.method = method;
}

@JsonProperty("metadata")
public Object getMetadata() {
return metadata;
}

@JsonProperty("metadata")
public void setMetadata(Object metadata) {
this.metadata = metadata;
}

@JsonProperty("details")
public Object getDetails() {
return details;
}

@JsonProperty("details")
public void setDetails(Object details) {
this.details = details;
}

@JsonProperty("issuer")
public String getIssuer() {
return issuer;
}

@JsonProperty("issuer")
public void setIssuer(String issuer) {
this.issuer = issuer;
}

@JsonProperty("profileId")
public String getProfileId() {
return profileId;
}

@JsonProperty("profileId")
public void setProfileId(String profileId) {
this.profileId = profileId;
}

@JsonProperty("links")
public Links getLinks() {
return links;
}

@JsonProperty("links")
public void setLinks(Links links) {
this.links = links;
}

}