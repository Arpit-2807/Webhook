package com.example.webhook.service;

import com.example.webhook.model.PaymentEvent;
import com.example.webhook.model.WebhookPayload;
import com.example.webhook.repository.PaymentEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WebhookService {

    @Autowired
    private PaymentEventRepository paymentEventRepository;

    // Process incoming webhook payload
    public PaymentEvent processWebhook(WebhookPayload payload) {

        // Convert webhook payload to payment event

        PaymentEvent event = new PaymentEvent();
        event.setEventType(payload.getEvent());
        event.setPaymentId(payload.getPaymentId());
        event.setAmount(payload.getAmount());
        event.setCurrency(payload.getCurrency());
        event.setStatus(payload.getStatus());

        // Save to database and return
        return paymentEventRepository.save(event);
    }

    //Get all payment event
    public List<PaymentEvent> getAllEvents() {
        return paymentEventRepository.findAll();
    }

    //Get event by status
    public List<PaymentEvent> getEventsByStatus(String status) { return paymentEventRepository.findByStatus(status);}

    //Get total count
    public long getTotalCount() { return paymentEventRepository.count();}

    //Get success count
    public long getSuccessCount() {  return (long) paymentEventRepository.findByStatus("success").size();}

    //Get failed count
    public long getFailedCount(){ return (long) paymentEventRepository.findByStatus("failed").size();}
}



