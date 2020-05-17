package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.point.GpsPoint;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;

import java.util.Objects;

public class AvailableVehicleAtStation {

    private final AvailableVehicleAtLock availableVehicleAtLock;
    private final GpsPoint location;

    AvailableVehicleAtStation(AvailableVehicleAtLock availableVehicleAtLock, GpsPoint location) {
        this.availableVehicleAtLock = availableVehicleAtLock;
        this.location = location;
    }

    public GpsPoint getLocation() {
        return location;
    }

    public LockId getLockId() {
        return availableVehicleAtLock.getLockId();
    }

    public VehicleId getVehicleId() {
        return availableVehicleAtLock.getVehicleId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableVehicleAtStation that = (AvailableVehicleAtStation) o;
        return Objects.equals(availableVehicleAtLock, that.availableVehicleAtLock) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(availableVehicleAtLock, location);
    }

    @Override
    public String toString() {
        return "FreeVehicleAtStation{" +
                "freeVehicleAtLock=" + availableVehicleAtLock +
                ", location=" + location +
                '}';
    }
}
