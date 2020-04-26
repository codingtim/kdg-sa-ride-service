package be.codingtim.velo.ride.domain.station;

class FreeVehicleAtLock {

    private final int lockId;
    private final int vehicleId;

    FreeVehicleAtLock(int lockId, int vehicleId) {
        this.lockId = lockId;
        this.vehicleId = vehicleId;
    }
}
