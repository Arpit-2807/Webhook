package com.example.webhook.repository;

import com.example.webhook.model.PaymentEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentEventRepository extends JpaRepository<PaymentEvent, Long> {

    //Find payment by status
    List<PaymentEvent> findByStatus(String status);

    //Find payment by event
    List<PaymentEvent> fingByEventType(String eventType);
}
