package com.seeni.systemmonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.seeni.systemmonitor.entity.IncidentTicket;

import java.util.List;

public interface IncidentTicketRepository extends JpaRepository<IncidentTicket, Long> {
    List<IncidentTicket> findByStatus(String status);
}
