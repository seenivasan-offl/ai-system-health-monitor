package com.seeni.systemmonitor.service;

import org.springframework.stereotype.Service;
import com.seeni.systemmonitor.repository.ServerMetricRepository;
import com.seeni.systemmonitor.repository.IncidentTicketRepository;
import com.seeni.systemmonitor.entity.ServerMetric;
import com.seeni.systemmonitor.entity.IncidentTicket;

@Service
public class MetricService {

    private final ServerMetricRepository metricRepository;
    private final IncidentTicketRepository ticketRepository;
    private final AiReportService aiReportService;
    private final EmailAlertService emailAlertService;

    public MetricService(ServerMetricRepository metricRepository,
                         IncidentTicketRepository ticketRepository,
                         AiReportService aiReportService,
                         EmailAlertService emailAlertService) {
        this.metricRepository = metricRepository;
        this.ticketRepository = ticketRepository;
        this.aiReportService = aiReportService;
        this.emailAlertService = emailAlertService;
    }

    public ServerMetric saveMetric(ServerMetric metric) {

        // 1️⃣ Save raw metrics
        ServerMetric savedMetric = metricRepository.save(metric);

        // 2️⃣ Decide priority
        String priority = decidePriority(metric);

        // 3️⃣ If LOW → Auto close open incidents & stop
        if (priority.equals("LOW")) {

            ticketRepository.findByStatus("OPEN").forEach(ticket -> {
                ticket.setStatus("CLOSED");
                ticketRepository.save(ticket);
            });

            System.out.println("System Normal → All open incidents closed");
            return savedMetric; // ❗ No new incident created
        }

        // 4️⃣ Generate AI Summary only for MEDIUM / HIGH / CRITICAL
        String aiSummary = aiReportService.generateIncidentReport(
                metric.getCpuUsage(),
                metric.getRamUsage(),
                metric.getDiskUsage()
        );

        // 5️⃣ Save Incident Ticket
        IncidentTicket ticket = new IncidentTicket();
        ticket.setIssueSummary(aiSummary);
        ticket.setPriority(priority);
        ticket.setStatus("OPEN");
        ticketRepository.save(ticket);

        System.out.println("Incident Created → Priority: " + priority);

        // 6️⃣ Send Email only for HIGH & CRITICAL
        if (priority.equals("HIGH") || priority.equals("CRITICAL")) {
            emailAlertService.sendIncidentEmail(aiSummary, priority);
        }

        return savedMetric;
    }

    // 🎯 Priority Rule Engine
    private String decidePriority(ServerMetric metric) {

        float cpu = metric.getCpuUsage();
        float ram = metric.getRamUsage();
        float disk = metric.getDiskUsage();

        if (cpu >= 90 || ram >= 90 || disk >= 95) return "CRITICAL";
        if (cpu >= 80 || ram >= 80 || disk >= 90) return "HIGH";
        if (cpu >= 65 || ram >= 65 || disk >= 80) return "MEDIUM";

        return "LOW";
    }
}
