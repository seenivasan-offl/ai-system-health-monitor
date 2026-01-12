package com.seeni.systemmonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.seeni.systemmonitor.entity.AlertSubscriber;

public interface AlertSubscriberRepository
        extends JpaRepository<AlertSubscriber,Long> {
}
