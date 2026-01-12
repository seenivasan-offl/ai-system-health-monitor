package com.seeni.systemmonitor.controller;

import org.springframework.web.bind.annotation.*;
import com.seeni.systemmonitor.repository.AlertSubscriberRepository;
import com.seeni.systemmonitor.entity.AlertSubscriber;

import java.util.Map;

@RestController
@RequestMapping("/api/subscribe")
@CrossOrigin
public class SubscriptionController {

    private final AlertSubscriberRepository repo;

    public SubscriptionController(AlertSubscriberRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Map<String, String> addSubscriber(@RequestBody AlertSubscriber sub) {
        repo.save(sub);
        return Map.of("message", "Subscribed Successfully");
    }

}
