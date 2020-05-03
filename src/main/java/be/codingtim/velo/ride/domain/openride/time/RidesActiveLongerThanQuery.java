package be.codingtim.velo.ride.domain.openride.time;

import be.codingtim.velo.ride.domain.ride.RideId;

import java.time.Instant;
import java.util.List;

public interface RidesActiveLongerThanQuery {
    List<RideId> findAllActiveBefore(Instant activeSince);
}
