package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.vehicle.VehicleId;

import java.util.Objects;

class VehicleLockedAtLock {

    private final LockId lockId;
    private final VehicleId vehicleId;

    VehicleLockedAtLock(LockId lockId, VehicleId vehicleId) {
        this.lockId = lockId;
        this.vehicleId = vehicleId;
    }

    LockId getLockId() {
        return lockId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleLockedAtLock that = (VehicleLockedAtLock) o;
        return Objects.equals(lockId, that.lockId) &&
                Objects.equals(vehicleId, that.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lockId, vehicleId);
    }

    @Override
    public String toString() {
        return "VehicleLockedAtLock{" +
                "lockId=" + lockId +
                ", vehicleId=" + vehicleId +
                '}';
    }
}
