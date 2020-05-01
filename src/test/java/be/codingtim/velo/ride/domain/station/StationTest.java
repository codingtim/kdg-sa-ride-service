package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.station.exception.StationHasNoAvailableVehicle;
import be.codingtim.velo.ride.domain.vehicle.VehicleData;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static be.codingtim.velo.ride.domain.station.StationsData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StationTest {

    @Nested
    class GetFreeVehicle {

        @Test
        void vehicleAvailable() {
            Station station = HALF_FULL_STATION;
            AvailableVehicleAtStation availableVehicleAtStation = station.getAvailableVehicle();
            AvailableVehicleAtStation expected = new AvailableVehicleAtStation(
                    new AvailableVehicleAtLock(HALF_FULL_STATION_LOCK_1.getLockId(), VehicleData.STATION_VEHICLE_1.getVehicleId()),
                    station.getLocation()
            );
            assertEquals(expected, availableVehicleAtStation);
        }

        @Test
        void noVehicleAvailable() {
            assertThrows(StationHasNoAvailableVehicle.class, EMPTY_STATION::getAvailableVehicle);
        }
    }
}