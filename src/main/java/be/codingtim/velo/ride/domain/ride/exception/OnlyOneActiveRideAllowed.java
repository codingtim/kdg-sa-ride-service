package be.codingtim.velo.ride.domain.ride.exception;

public class OnlyOneActiveRideAllowed extends RuntimeException {
    public OnlyOneActiveRideAllowed() {
        super("Only one ride allowed.");
    }
}
