package com.seeni.systemmonitor.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.seeni.systemmonitor.entity.ServerMetric;
import com.seeni.systemmonitor.entity.IncidentTicket;
import com.seeni.systemmonitor.repository.ServerMetricRepository;
import com.seeni.systemmonitor.repository.IncidentTicketRepository;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    private final ServerMetricRepository metricRepo;
    private final IncidentTicketRepository ticketRepo;

    public DashboardController(ServerMetricRepository metricRepo,
                               IncidentTicketRepository ticketRepo) {
        this.metricRepo = metricRepo;
        this.ticketRepo = ticketRepo;
    }

    // 🔹 View all metrics
    @GetMapping("/metrics")
    public List<ServerMetric> getAllMetrics() {
        return metricRepo.findAll();
    }

    // 🔹 View all incidents
    @GetMapping("/incidents")
    public List<IncidentTicket> getAllIncidents() {
        return ticketRepo.findAll();
    }

    // 🔹 View only OPEN incidents
    @GetMapping("/incidents/open")
    public List<IncidentTicket> getOpenIncidents() {
        return ticketRepo.findByStatus("OPEN");
    }
}
