package com.example.airquality.service;

import com.example.airquality.dto.MeasurementQuery;
import com.example.airquality.dto.MeasurementRequest;
import com.example.airquality.entity.Measurement;
import com.example.airquality.entity.Sensor;
import com.example.airquality.repository.MeasurementRepository;
import com.example.airquality.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;
    @Autowired
    private SensorRepository sensorRepository;

    public void addMeasurement(MeasurementRequest req) {
        Sensor sensor = sensorRepository.findById(req.sensorId).orElseThrow();
        Measurement m = new Measurement();
        m.setSensor(sensor);
        m.setMetric(req.metric);
        m.setValue(req.value);
        m.setTimestamp(req.timestamp != null ? req.timestamp : LocalDateTime.now());
        measurementRepository.save(m);
    }

    public Map<String, Double> queryStats(MeasurementQuery query) {
        Map<String, Double> result = new HashMap<>();
        for (Long sensorId : query.sensorIds) {
            for (String metric : query.metrics) {
                List<Measurement> measurements = measurementRepository.findBySensorIdAndMetricAndTimestampBetween(
                    sensorId, metric,
                    query.from != null ? query.from : LocalDateTime.now().minusDays(7),
                    query.to != null ? query.to : LocalDateTime.now()
                );
                List<Double> values = measurements.stream().map(Measurement::getValue).collect(Collectors.toList());
                String stat = query.stats.get(metric);
                double val = switch (stat) {
                    case "min" -> values.stream().min(Double::compare).orElse(0.0);
                    case "max" -> values.stream().max(Double::compare).orElse(0.0);
                    case "sum" -> values.stream().mapToDouble(Double::doubleValue).sum();
                    case "avg" -> values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                    default -> 0.0;
                };
                result.put(metric + "_" + stat + "_sensor" + sensorId, val);
            }
        }
        return result;
    }
}
