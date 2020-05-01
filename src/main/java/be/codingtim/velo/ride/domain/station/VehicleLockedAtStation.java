package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.location.GpsPoint;

import java.util.Objects;

public class VehicleLockedAtStation {

    private final VehicleLockedAtLock vehicleLockedAtLock;
    private final GpsPoint location;

    public VehicleLockedAtStation(VehicleLockedAtLock vehicleLockedAtLock, GpsPoint location) {
        this.vehicleLockedAtLock = vehicleLockedAtLock;
        this.location = location;
    }

    public GpsPoint getLocation() {
        return location;
    }

    public LockId getLockId() {
        return vehicleLockedAtLock.getLockId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleLockedAtStation that = (VehicleLockedAtStation) o;
        return Objects.equals(vehicleLockedAtLock, that.vehicleLockedAtLock) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleLockedAtLock, location);
    }

    @Override
    public String toString() {
        return "VehicleLockedAtStation{" +
                "vehicleLockedAtLock=" + vehicleLockedAtLock +
                ", location=" + location +
                '}';
    }
}
