package com.example.airquality.controller;

import com.example.airquality.dto.MeasurementQuery;
import com.example.airquality.dto.MeasurementRequest;
import com.example.airquality.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @PostMapping
    public ResponseEntity<?> addMeasurement(@RequestBody MeasurementRequest request) {
        measurementService.addMeasurement(request);
        return ResponseEntity.ok("Measurement saved");
    }

    @PostMapping("/query")
    public ResponseEntity<Map<String, Double>> queryMeasurements(@RequestBody MeasurementQuery query) {
        return ResponseEntity.ok(measurementService.queryStats(query));
    }
}
