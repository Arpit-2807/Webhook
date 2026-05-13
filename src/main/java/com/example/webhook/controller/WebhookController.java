package com.example.webhook.controller;

import com.example.webhook.model.PaymentEvent;
import com.example.webhook.model.WebhookPayload;
import com.example.webhook.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    private WebhookService webhookService;

    //Main webhook endpoint - receives POST requests
    @PostMapping
    public ResponseEntity<PaymentEvent> receiveWebhook(@RequestBody WebhookPayload payload) {
        PaymentEvent savedEvent = webhookService.processWebhook(payload);
        return ResponseEntity.status(HttpStatus.OK).body(savedEvent);
    }

    //Test endpoint - manually trigger a test webhook
    @GetMapping("/test")
    public ResponseEntity<PaymentEvent> testWebhook() {

        //Create a dummy payload for testing
        WebhookPayload testPayload = new WebhookPayload();
        testPayload.setEvent("payment.success");
        testPayload.setPaymentId("pay_TEST123");
        testPayload.setAmount(999.00);
        testPayload.setCurrency("INR");
        testPayload.setStatus("success");

        PaymentEvent savedEvent = webhookService.processWebhook(testPayload);
        return ResponseEntity.status(HttpStatus.OK).body(savedEvent);
    }
}



