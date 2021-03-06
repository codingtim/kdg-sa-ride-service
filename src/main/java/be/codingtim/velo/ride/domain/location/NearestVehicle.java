package be.codingtim.velo.ride.domain.location;

import be.codingtim.velo.ride.domain.point.GpsPoint;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;

import java.util.Objects;

public class NearestVehicle {
    private final VehicleId vehicleId;
    private final GpsPoint location;

    public NearestVehicle(VehicleId vehicleId, GpsPoint location) {
        this.vehicleId = vehicleId;
        this.location = location;
    }

    public VehicleId getVehicleId() {
        return vehicleId;
    }

    public GpsPoint getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NearestVehicle that = (NearestVehicle) o;
        return Objects.equals(vehicleId, that.vehicleId) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, location);
    }
}
