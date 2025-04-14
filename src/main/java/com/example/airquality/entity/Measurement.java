package com.example.airquality.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sensor sensor;

    private String metric;
    @Column(name = "measurement_value")
    private Double value;
    private LocalDateTime timestamp;

    public Measurement() {
    }

    public Measurement(Sensor sensor, String metric, double value, LocalDateTime timestamp) {
        this.sensor = sensor;
        this.metric = metric;
        this.value = value;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Sensor getSensor() { return sensor; }
    public void setSensor(Sensor sensor) { this.sensor = sensor; }

    public String getMetric() { return metric; }
    public void setMetric(String metric) { this.metric = metric; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
