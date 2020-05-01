package be.codingtim.velo.ride.domain.ride.exception;

public class OnlyStationVehicleCanBeLockedAtStation extends RuntimeException {
    public OnlyStationVehicleCanBeLockedAtStation() {
        super("Only station vehicles can be locked at a station.");
    }
}
