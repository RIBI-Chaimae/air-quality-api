package com.example.airquality.repository;

import com.example.airquality.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    List<Measurement> findBySensorIdAndMetricAndTimestampBetween(Long sensorId, String metric, LocalDateTime start, LocalDateTime end);
    List<Measurement> findBySensorId(Long sensorId);
}
