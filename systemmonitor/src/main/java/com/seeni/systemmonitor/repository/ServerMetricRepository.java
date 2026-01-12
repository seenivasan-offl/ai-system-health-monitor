package com.seeni.systemmonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.seeni.systemmonitor.entity.ServerMetric;

public interface ServerMetricRepository
        extends JpaRepository<ServerMetric, Long> {
}
