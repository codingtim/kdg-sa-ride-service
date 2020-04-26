package be.codingtim.velo.ride.facade;

import be.codingtim.velo.ride.domain.ride.RideId;

public interface RideFacade {
    RideId startRide(Integer userId, Integer stationId);
}
