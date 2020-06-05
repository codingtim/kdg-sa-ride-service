package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.station.LockId;
import be.codingtim.velo.ride.domain.station.StationId;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.user.User;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;

import java.time.Clock;

public interface RideService {

    StationRideStarted startRide(ActiveSubscription activeSubscription, StationId stationId, Clock clock);

    CompletedStationRide endRide(User user, LockId lockId, Clock clock);

    RideId startRide(ActiveSubscription activeSubscription, VehicleId vehicleId, Clock clock);

    CompletedFreeRide endRide(User user, VehicleId vehicleId, Clock clock);
}
