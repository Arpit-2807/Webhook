package com.example.webhook.controller;

import com.example.webhook.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private WebhookService webhookService;

    @GetMapping("/")
    public String dashboard(Model model) {

        model.addAttribute("events",
                webhookService.getAllEvents());
        model.addAttribute("totalCount",
                webhookService.getTotalCount());
        model.addAttribute("successCount",
                webhookService.getSuccessCount());
        model.addAttribute("failedCount",
                webhookService.getFailedCount());

        // Debug lines - check IntelliJ console
        System.out.println("=== DASHBOARD DEBUG ===");
        System.out.println("TOTAL COUNT: " + webhookService.getTotalCount());
        System.out.println("SUCCESS COUNT: " + webhookService.getSuccessCount());
        System.out.println("FAILED COUNT: " + webhookService.getFailedCount());
        System.out.println("ALL EVENTS: " + webhookService.getAllEvents());
        System.out.println("=======================");

        return "dashboard";
    }

    @GetMapping("/logs")
    public String webhookLogs(Model model) {

        model.addAttribute("events",
                webhookService.getAllEvents());

        return "webhook-log";
    }
}