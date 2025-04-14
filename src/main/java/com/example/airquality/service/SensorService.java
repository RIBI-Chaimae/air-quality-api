package com.example.airquality.service;

import com.example.airquality.entity.Sensor;
import com.example.airquality.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorService sensorService;

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public Sensor getSensorById(Long id) {
        return sensorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sensor not found"));
    }

    @PostMapping
    public Sensor createSensor(@RequestBody Sensor sensor) {
        return sensorService.createSensor(sensor);
    }

    public Sensor updateSensor(long id, Sensor updatedSensor) {
        Optional<Sensor> existingSensorOpt = sensorRepository.findById(id);

        if (existingSensorOpt.isPresent()) {
            Sensor existingSensor = existingSensorOpt.get();
            existingSensor.setName(updatedSensor.getName());

            return sensorRepository.save(existingSensor);
        } else {
            return null;
        }
    }

    public void deleteSensor(long id) {
        if (!sensorRepository.existsById(id)) {
            throw new IllegalArgumentException("Sensor with ID " + id + " not found.");
        }
        sensorRepository.deleteById(id);
    }
}
