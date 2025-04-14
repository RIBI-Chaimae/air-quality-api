package com.example.airquality.dto;

import java.time.LocalDateTime;

public class MeasurementRequest {
    public Long sensorId;
    public String metric;
    public Double value;
    public LocalDateTime timestamp;
}
