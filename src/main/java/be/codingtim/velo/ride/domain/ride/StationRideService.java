package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.station.LockId;
import be.codingtim.velo.ride.domain.station.StationId;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.user.User;

import java.time.Clock;

public interface StationRideService {

    StationRideStarted startRide(ActiveSubscription activeSubscription, StationId stationId, Clock clock);

    CompletedStationRide endRide(User user, LockId lockId, Clock clock);
}
