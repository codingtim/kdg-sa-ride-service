package be.codingtim.velo.ride.facade;

import be.codingtim.velo.ride.domain.ride.StationRideStarted;

public interface RideFacade {
    StationRideStarted startRide(Integer userId, Integer stationId);

    void endRide(Integer userId, Integer lockId);
}
