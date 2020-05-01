package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.station.AvailableVehicleAtStation;
import be.codingtim.velo.ride.domain.station.Station;
import be.codingtim.velo.ride.domain.station.StationsData.HalfFullStation;
import be.codingtim.velo.ride.domain.station.VehicleLockedAtStation;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.user.User;
import be.codingtim.velo.ride.domain.user.UserData;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StationRideTest {

    @Nested
    class RideDuration {

        private final User userWithActiveSubscription = UserData.USER_WITH_ACTIVE_SUBSCRIPTION;
        private final HalfFullStation halfFullStation = new HalfFullStation();
        private final Vehicle vehicle = halfFullStation.vehicle;
        private final Station station = halfFullStation.station;
        private final Clock clockAtStartTime = Clock.fixed(Instant.parse("2020-05-01T10:45:12.200Z"), ZoneId.of("UTC"));
        private final Clock clockAtEndTime = Clock.fixed(Instant.parse("2020-05-01T10:56:18.350Z"), ZoneId.of("UTC"));

        @Test
        void rideDuration() {
            AvailableVehicleAtStation availableVehicle = station.getAvailableVehicle();
            ActiveSubscription activeSubscription = userWithActiveSubscription.getActiveSubscription(LocalDate.now(Clock.systemUTC()));
            StationRide stationRide = new StationRide(vehicle, availableVehicle, activeSubscription, clockAtStartTime);

            VehicleLockedAtStation vehicleLockedAtStation = station.lockVehicle(vehicle, station.getLocksWithoutVehicle().getLockIds().get(0));
            stationRide.end(vehicleLockedAtStation, clockAtEndTime);

            Duration rideDuration = stationRide.getRideDuration();
            assertEquals(Duration.parse("PT11M6.15S"), rideDuration);
        }
    }

}