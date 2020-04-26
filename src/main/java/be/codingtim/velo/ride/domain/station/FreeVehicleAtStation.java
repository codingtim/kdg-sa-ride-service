package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.location.GpsPoint;

public class FreeVehicleAtStation {

    private final FreeVehicleAtLock freeVehicleAtLock;
    private final GpsPoint location;

    public FreeVehicleAtStation(FreeVehicleAtLock freeVehicleAtLock, GpsPoint location) {

        this.freeVehicleAtLock = freeVehicleAtLock;
        this.location = location;
    }
}
