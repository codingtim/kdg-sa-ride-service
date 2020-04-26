package be.codingtim.velo.ride.domain.user.exception;

import be.codingtim.velo.ride.domain.exception.EntityNotFound;
import be.codingtim.velo.ride.domain.user.UserId;

import java.text.MessageFormat;

public class UserNotFound extends EntityNotFound {
    private static final String MESSAGE = "No user found with id {0}.";

    public UserNotFound(UserId userId) {
        super(MessageFormat.format(MESSAGE, userId.getValue()));
    }
}
