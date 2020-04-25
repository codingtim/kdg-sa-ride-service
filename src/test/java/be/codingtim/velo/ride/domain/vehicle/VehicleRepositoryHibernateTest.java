package be.codingtim.velo.ride.domain.vehicle;

import be.codingtim.velo.ride.database.configuration.DatabaseConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = VehicleRepositoryHibernateTest.TestConfiguration.class)
class VehicleRepositoryHibernateTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void getStationRide() {
        int vehicleId = 1;
        Vehicle vehicle = vehicleRepository.findById(vehicleId);
        assertNotNull(vehicle);
        assertEquals(new VehicleId(vehicleId), vehicle.getVehicleId());
        assertEquals(Optional.of(5615), vehicle.getLockId());
    }

    @Test
    void getVehicleWithoutLock() {
        int vehicleId = 6970;
        Vehicle vehicle = vehicleRepository.findById(vehicleId);
        assertNotNull(vehicle);
        assertEquals(new VehicleId(vehicleId), vehicle.getVehicleId());
        assertEquals(Optional.empty(), vehicle.getLockId());
    }

    @Test
    void getUnknownVehicle() {
        int vehicleId = -1;
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> vehicleRepository.findById(vehicleId));
    }

    @Configuration
    @Import(DatabaseConfiguration.class)
    @ComponentScan(basePackages = "be.codingtim.velo.ride.domain.vehicle")
    static class TestConfiguration {

    }
}