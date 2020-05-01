package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.station.LockId;
import be.codingtim.velo.ride.domain.station.StationId;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.user.UserId;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;

public interface StationRideService {

    RideId startRide(ActiveSubscription activeSubscription, StationId stationId, Clock clock);

    @Transactional
    void endRide(UserId userId, LockId lockId, Clock clock);
}
