package be.codingtim.velo.ride.domain.station;

public class LockWithAvailableVehicle {

    private int lockId;
    private int vehicleId;

    public LockWithAvailableVehicle(int lockId, int vehicleId) {
        this.lockId = lockId;
        this.vehicleId = vehicleId;
    }
}
