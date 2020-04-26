package be.codingtim.velo.ride.domain.user.exception;

import be.codingtim.velo.ride.domain.exception.EntityNotFound;
import be.codingtim.velo.ride.domain.user.UserId;

public class UserNotFound extends EntityNotFound {
    private UserId userId;

    public UserNotFound(UserId userId) {
        this.userId = userId;
    }
}
