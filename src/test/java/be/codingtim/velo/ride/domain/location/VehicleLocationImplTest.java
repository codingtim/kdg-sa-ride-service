package be.codingtim.velo.ride.domain.location;

import be.codingtim.velo.ride.domain.point.GpsPoint;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import be.codingtim.velo.ride.domain.vehicle.VehicleData;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleLocationImplTest {

    private final VehicleLocation vehicleLocation = new VehicleLocationImpl();

    @Test
    void getLocationOfReturnsLastLocation() {
        Vehicle vehicle = VehicleData.FREE_VEHICLE_1;
        VehicleId vehicleId = vehicle.getVehicleId();
        vehicleLocation.heartbeat(Instant.now(), vehicleId, GpsPoint.of(1.2, 2.3));
        vehicleLocation.heartbeat(Instant.now(), vehicleId, GpsPoint.of(1.2, 2.4));
        GpsPoint locationOf = vehicleLocation.getLocationOf(vehicle);
        assertEquals(GpsPoint.of(1.2, 2.4), locationOf);
    }
}