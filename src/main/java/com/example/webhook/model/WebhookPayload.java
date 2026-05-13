package com.example.webhook.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookPayload{

    @JsonProperty("event")
    private String event;

    @JsonProperty("paymentId")
    private String paymentId;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("status")
    private String status;

    //Getter and Setter
    public String getEvent() { return event; }
    public void setEvent(String event) { this.event = event; }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}