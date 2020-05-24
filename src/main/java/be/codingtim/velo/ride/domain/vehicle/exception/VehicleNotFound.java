package be.codingtim.velo.ride.domain.vehicle.exception;

import be.codingtim.velo.ride.domain.exception.EntityNotFound;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;

import java.text.MessageFormat;

public class VehicleNotFound extends EntityNotFound {

    private static final String MESSAGE = "No vehicle found with id {0}.";

    public VehicleNotFound(VehicleId vehicleId) {
        super(MessageFormat.format(MESSAGE, vehicleId.getValue()));
    }

}
