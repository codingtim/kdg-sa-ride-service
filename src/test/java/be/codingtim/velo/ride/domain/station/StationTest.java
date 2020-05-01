package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.location.GpsPoint;
import be.codingtim.velo.ride.domain.station.exception.StationHasNoAvailableVehicle;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import be.codingtim.velo.ride.domain.vehicle.VehicleData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static be.codingtim.velo.ride.domain.station.StationsData.*;
import static be.codingtim.velo.ride.domain.vehicle.VehicleData.stationVehicle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StationTest {

    @Nested
    class GetAvailableVehicle {

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

    @Nested
    class LockVehicleInStationLock {

        private final Lock halfFullStationLock1 = new Lock(20, 2, 1);
        private final Lock halfFullStationLock2 = new Lock(21, 2, 2);
        private final Station halfFullStation = new Station(2, GpsPoint.of(2.0, 2.1), List.of(
                halfFullStationLock1,
                halfFullStationLock2
        ));

        @BeforeEach
        void setUp() {
            halfFullStation.lockVehicle(stationVehicle(), halfFullStationLock1.getLockId());
        }

        @Test
        void lockVehicle() {
            Station station = halfFullStation;
            Vehicle vehicle = stationVehicle();

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