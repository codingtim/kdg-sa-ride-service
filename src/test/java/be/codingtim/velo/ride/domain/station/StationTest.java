package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.station.exception.StationHasNoAvailableVehicle;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static be.codingtim.velo.ride.domain.station.StationData.EmptyStation;
import static be.codingtim.velo.ride.domain.station.StationData.HalfFullStation;
import static be.codingtim.velo.ride.domain.vehicle.VehicleData.stationVehicle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StationTest {

    @Nested
    class GetAvailableVehicle {

        @Test
        void vehicleAvailable() {
            HalfFullStation halfFullStation = new HalfFullStation();
            Station station = halfFullStation.station;
            AvailableVehicleAtStation availableVehicleAtStation = station.getAvailableVehicle();
            AvailableVehicleAtStation expected = new AvailableVehicleAtStation(
                    new AvailableVehicleAtLock(HalfFullStation.LOCK_ID_1, halfFullStation.vehicle.getVehicleId()),
                    station.getLocation()
            );
            assertEquals(expected, availableVehicleAtStation);
        }

        @Test
        void noVehicleAvailable() {
            Station station = new EmptyStation().station;
            assertThrows(StationHasNoAvailableVehicle.class, station::getAvailableVehicle);
        }
    }

    @Nested
    class LockVehicleInStationLock {

        private final Station station = new HalfFullStation().station;
        private final Vehicle vehicle = stationVehicle();

        @Test
        void lockVehicle() {
            StationLocksWithoutVehicle locksWithoutVehicle = station.getLocksWithoutVehicle();
            assertEquals(1, locksWithoutVehicle.getLockIds().size());
            LockId lockId = locksWithoutVehicle.getLockIds().get(0);

            VehicleLockedAtStation vehicleLockedAtStation = station.lockVehicle(vehicle, lockId);
            assertEquals(lockId, vehicleLockedAtStation.getLockId());
            assertEquals(station.getLocation(), vehicleLockedAtStation.getLocation());

            locksWithoutVehicle = station.getLocksWithoutVehicle();
            assertEquals(0, locksWithoutVehicle.getLockIds().size());
        }

    }
}