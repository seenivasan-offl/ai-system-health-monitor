package com.seeni.systemmonitor.controller;

import com.seeni.systemmonitor.service.MetricService;
import org.springframework.web.bind.annotation.*;
import com.seeni.systemmonitor.entity.ServerMetric;
import com.seeni.systemmonitor.repository.ServerMetricRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/metrics")
public class MetricController {

    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @PostMapping
    public ServerMetric receiveMetrics(@RequestBody ServerMetric metric) {
        return metricService.saveMetric(metric);
    }
}

