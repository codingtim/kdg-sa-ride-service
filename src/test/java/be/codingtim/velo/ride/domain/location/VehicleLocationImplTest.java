package be.codingtim.velo.ride.domain.location;

import be.codingtim.velo.ride.domain.point.GpsPoint;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;
import be.codingtim.velo.ride.domain.vehicle.Vehicles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

import static be.codingtim.velo.ride.domain.vehicle.VehicleData.*;
import static be.codingtim.velo.ride.domain.vehicle.VehicleType.ROAMING_E_STEP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VehicleLocationImplTest {

    private final Vehicles vehicles = mock(Vehicles.class);
    private final VehicleLocation vehicleLocation = new VehicleLocationImpl(vehicles);

    @BeforeEach
    void setUp() {
        registerVehicle(FREE_VEHICLE_1, FREE_VEHICLE_2, FREE_VEHICLE_3, FREE_VEHICLE_4);
    }

    private void registerVehicle(Vehicle... vehicles) {
        for (Vehicle vehicle : vehicles) {
            when(this.vehicles.get(vehicle.getVehicleId())).thenReturn(vehicle);
        }
    }

    @Test
    void getLocationOfReturnsLastLocation() {
        Vehicle vehicle = FREE_VEHICLE_1;
        VehicleId vehicleId = vehicle.getVehicleId();
        vehicleLocation.heartbeat(Instant.now(), vehicleId, GpsPoint.of(1.2, 2.3));
        vehicleLocation.heartbeat(Instant.now(), vehicleId, GpsPoint.of(1.2, 2.4));
        GpsPoint locationOf = vehicleLocation.getLocationOf(vehicle);
        assertEquals(GpsPoint.of(1.2, 2.4), locationOf);
        verifyVehicleDateRetrievedOnce(vehicleId);
    }


    private void verifyVehicleDateRetrievedOnce(VehicleId vehicleId) {
        verify(vehicles, times(1)).get(vehicleId);
        verifyNoMoreInteractions(vehicles);
    }

    @Test
    void getClosestVehicleOfType() {
        GpsPoint gpsPoint = GpsPoint.of(1.2, 2.0);
        vehicleLocation.heartbeat(Instant.now(), FREE_VEHICLE_1.getVehicleId(), GpsPoint.of(1.2, 2.3));
        vehicleLocation.heartbeat(Instant.now(), FREE_VEHICLE_2.getVehicleId(), GpsPoint.of(1.2, 2.2));
        vehicleLocation.heartbeat(Instant.now(), FREE_VEHICLE_3.getVehicleId(), GpsPoint.of(1.2, 2.1));
        vehicleLocation.heartbeat(Instant.now(), FREE_VEHICLE_4.getVehicleId(), GpsPoint.of(1.2, 2.4));
        Optional<ClosestVehicle> closestVehicle = vehicleLocation.closestVehicleTo(gpsPoint, ROAMING_E_STEP);
        assertEquals(closestVehicle, Optional.of(new ClosestVehicle(FREE_VEHICLE_2.getVehicleId(), GpsPoint.of(1.2, 2.2))));
    }

    @Test
    void getClosestVehicleOfType_noneFoundOfType() {
        GpsPoint gpsPoint = GpsPoint.of(1.2, 2.0);
        vehicleLocation.heartbeat(Instant.now(), FREE_VEHICLE_3.getVehicleId(), GpsPoint.of(1.2, 2.1));
        vehicleLocation.heartbeat(Instant.now(), FREE_VEHICLE_4.getVehicleId(), GpsPoint.of(1.2, 2.4));
        Optional<ClosestVehicle> closestVehicle = vehicleLocation.closestVehicleTo(gpsPoint, ROAMING_E_STEP);
        assertEquals(closestVehicle, Optional.empty());
    }
}