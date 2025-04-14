package com.example.airquality;

import com.example.airquality.entity.Measurement;
import com.example.airquality.entity.Sensor;
import com.example.airquality.repository.MeasurementRepository;
import com.example.airquality.repository.SensorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class AirQualityApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AirQualityApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedData(SensorRepository sensorRepo, MeasurementRepository measurementRepo) {
        return args -> {
            // Création de capteurs
            Sensor sensor1 = new Sensor();
            sensor1.setName("Sensor A");
            Sensor sensor2 = new Sensor();
            sensor2.setName("Sensor B");

            sensorRepo.save(sensor1);
            sensorRepo.save(sensor2);

            // Ajout de mesures pour Sensor A
            measurementRepo.save(new Measurement(sensor1, "CO2", 420.5, LocalDateTime.now().minusDays(1)));
            measurementRepo.save(new Measurement(sensor1, "PM2.5", 12.3, LocalDateTime.now().minusDays(2)));

            // Ajout de mesures pour Sensor B
            measurementRepo.save(new Measurement(sensor2, "CO2", 390.0, LocalDateTime.now().minusDays(3)));
            measurementRepo.save(new Measurement(sensor2, "PM2.5", 9.8, LocalDateTime.now().minusDays(4)));
        };
    }
}
