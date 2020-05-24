package be.codingtim.velo.ride.domain.ride.exception;

public class OnlyFreeRideVehicleCanBeLockedAnywhere extends RuntimeException {
    public OnlyFreeRideVehicleCanBeLockedAnywhere() {
        super("Only free ride vehicles can be locked at any location.");
    }
}
