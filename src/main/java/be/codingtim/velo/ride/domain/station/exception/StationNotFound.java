package be.codingtim.velo.ride.domain.station.exception;

import be.codingtim.velo.ride.domain.exception.EntityNotFound;
import be.codingtim.velo.ride.domain.station.StationId;

public class StationNotFound extends EntityNotFound {

    private StationId stationId;

    public StationNotFound(StationId stationId) {
        this.stationId = stationId;
    }

}
