package be.codingtim.velo.ride.domain.station;

import java.util.Objects;

class FreeVehicleAtLock {

    private final int lockId;
    private final int vehicleId;

    FreeVehicleAtLock(int lockId, int vehicleId) {
        this.lockId = lockId;
        this.vehicleId = vehicleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeVehicleAtLock that = (FreeVehicleAtLock) o;
        return lockId == that.lockId &&
                vehicleId == that.vehicleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lockId, vehicleId);
    }
}
