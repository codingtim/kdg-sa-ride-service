package be.codingtim.velo.ride.domain.station.exception;

import be.codingtim.velo.ride.domain.station.StationId;

import java.text.MessageFormat;

public class StationHasNoAvailableVehicle extends RuntimeException {

    private static final String MESSAGE = "Station with id {0} has no available vehicles.";

    public StationHasNoAvailableVehicle(StationId stationId) {
        super(MessageFormat.format(MESSAGE, stationId.getValue()));
    }

}
