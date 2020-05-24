package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.station.LockId;

public interface StationRideStarted {

    RideId getRideId();

    LockId getStartLockId();

}
