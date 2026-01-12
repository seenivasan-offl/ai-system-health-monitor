package com.seeni.systemmonitor.entity;

import jakarta.persistence.*;

@Entity
@Table(name="alert_subscribers")
public class AlertSubscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    public void setId(Long id) {
        this.id = id;
    }

    // getters & setters
    public Long getId(){ return id; }
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email=email; }
}
