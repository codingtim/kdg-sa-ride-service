package be.codingtim.velo.ride.domain.station.exception;

import be.codingtim.velo.ride.domain.exception.EntityNotFound;
import be.codingtim.velo.ride.domain.station.LockId;
import be.codingtim.velo.ride.domain.station.StationId;

import java.text.MessageFormat;

public class StationNotFound extends EntityNotFound {

    private static final String MESSAGE = "No station found with id {0}.";
    private static final String MESSAGE_LOCK = "No station found with lock id {0}.";

    public StationNotFound(StationId stationId) {
        super(MessageFormat.format(MESSAGE, stationId.getValue()));
    }

    public StationNotFound(LockId lockId) {
        super(MessageFormat.format(MESSAGE_LOCK, lockId.getValue()));
    }
}
