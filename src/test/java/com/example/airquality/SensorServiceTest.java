package com.example.airquality;

import com.example.airquality.entity.Sensor;
import com.example.airquality.repository.SensorRepository;
import com.example.airquality.service.SensorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SensorServiceTest {

    @InjectMocks
    private SensorService sensorService;

    @Mock
    private SensorRepository sensorRepository;

    @Mock
    private SensorService sensorServiceMock;

    private Sensor sensor;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        sensor = new Sensor(1L, "Test Sensor");
    }

    @Test
    public void testCreateSensor() {

        when(sensorServiceMock.createSensor(sensor)).thenReturn(sensor);

        Sensor createdSensor = sensorService.createSensor(sensor);

        assertEquals(sensor.getId(), createdSensor.getId());
        assertEquals(sensor.getName(), createdSensor.getName());

        verify(sensorServiceMock).createSensor(sensor);
    }

    @Test
    public void testGetSensorById() {
        Sensor sensor = new Sensor(1L, "Temp Sensor");

        when(sensorRepository.findById(1L)).thenReturn(Optional.of(sensor));

        Sensor foundSensor = sensorService.getSensorById(1L);

        assertNotNull(foundSensor);
        assertEquals("Temp Sensor", foundSensor.getName());
    }

    @Test
    public void testDeleteSensor() {
        long sensorId = 1L;
        Sensor sensor = new Sensor(sensorId, "Temperature Sensor");

        when(sensorRepository.existsById(sensorId)).thenReturn(true);

        sensorService.deleteSensor(sensorId);

        verify(sensorRepository, times(1)).deleteById(sensorId);
    }

    @Test
    public void testDeleteSensorNotFound() {
        long sensorId = 1L;

        when(sensorRepository.existsById(sensorId)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            sensorService.deleteSensor(sensorId);
        });
    }
}

