package com.example.airquality.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MeasurementQuery {
    public List<Long> sensorIds;
    public List<String> metrics;
    public Map<String, String> stats;
    public LocalDateTime from;
    public LocalDateTime to;
}
