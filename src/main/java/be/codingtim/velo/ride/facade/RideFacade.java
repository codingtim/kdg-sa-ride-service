package be.codingtim.velo.ride.facade;

import be.codingtim.velo.ride.domain.ride.RideId;
import be.codingtim.velo.ride.domain.ride.StationRideStarted;

public interface RideFacade {
    StationRideStarted startStationRide(Integer userId, Integer stationId);

    void endStationRide(Integer userId, Integer lockId);

    RideId startFreeVehicleRide(Integer userId, Integer vehicleId);

}
