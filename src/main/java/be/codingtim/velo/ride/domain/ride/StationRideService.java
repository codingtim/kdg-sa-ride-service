package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.station.StationId;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;

import java.time.Clock;

public interface StationRideService {

    RideId startRide(ActiveSubscription activeSubscription, StationId stationId, Clock clock);
}