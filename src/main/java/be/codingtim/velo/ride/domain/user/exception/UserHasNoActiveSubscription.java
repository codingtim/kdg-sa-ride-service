package be.codingtim.velo.ride.domain.user.exception;

public class UserHasNoActiveSubscription extends RuntimeException {

    public UserHasNoActiveSubscription() {
        super("No valid subscription found.");
    }
}
