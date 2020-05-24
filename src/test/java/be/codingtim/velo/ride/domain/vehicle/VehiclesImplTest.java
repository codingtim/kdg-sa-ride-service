package be.codingtim.velo.ride.domain.vehicle;

import be.codingtim.velo.ride.domain.vehicle.exception.VehicleNotFound;
import org.junit.jupiter.api.Test;

import static be.codingtim.velo.ride.domain.vehicle.VehicleData.STATION_VEHICLE_2;
import static be.codingtim.velo.ride.domain.vehicle.VehicleData.UNKNOWN_VEHICLE_ID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VehiclesImplTest {

    private final VehicleRepositoryInMemory repository = new VehicleRepositoryInMemory();

    private final Vehicles vehicles = new VehiclesImpl(repository);

    @Test
    void getVehicle_whenFound_thenReturnVehicle() {
        Vehicle station = vehicles.get(STATION_VEHICLE_2.getVehicleId());
        assertNotNull(station);
    }

    @Test
    void getVehicle_whenNotFound_thenException() {
        assertThrows(VehicleNotFound.class, () -> vehicles.get(UNKNOWN_VEHICLE_ID));
    }

}