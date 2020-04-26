package be.codingtim.velo.ride.domain.station.exception;

import be.codingtim.velo.ride.domain.exception.EntityNotFound;
import be.codingtim.velo.ride.domain.station.StationId;

public class StationHasNoAvailableVehicle extends EntityNotFound {

    private StationId stationId;

    public StationHasNoAvailableVehicle(StationId stationId) {
        this.stationId = stationId;
    }

}
