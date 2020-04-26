package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.location.GpsPoint;

import java.util.Objects;

public class FreeVehicleAtStation {

    private final FreeVehicleAtLock freeVehicleAtLock;
    private final GpsPoint location;

    FreeVehicleAtStation(FreeVehicleAtLock freeVehicleAtLock, GpsPoint location) {
        this.freeVehicleAtLock = freeVehicleAtLock;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeVehicleAtStation that = (FreeVehicleAtStation) o;
        return Objects.equals(freeVehicleAtLock, that.freeVehicleAtLock) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(freeVehicleAtLock, location);
    }
}
