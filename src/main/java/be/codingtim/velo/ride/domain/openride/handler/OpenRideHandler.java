package be.codingtim.velo.ride.domain.openride.handler;

import be.codingtim.velo.ride.domain.ride.RideId;

import java.util.List;

public interface OpenRideHandler {

    void openRides(List<RideId> openRideIds);
}
