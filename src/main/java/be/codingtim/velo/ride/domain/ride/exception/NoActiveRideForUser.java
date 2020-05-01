package be.codingtim.velo.ride.domain.ride.exception;

import be.codingtim.velo.ride.domain.exception.EntityNotFound;
import be.codingtim.velo.ride.domain.user.UserId;

import java.text.MessageFormat;

public class NoActiveRideForUser extends EntityNotFound {

    private static final String MESSAGE = "No active ride found for user with id {0}.";

    public NoActiveRideForUser(UserId userId) {
        super(MessageFormat.format(MESSAGE, userId.getValue()));
    }
}
