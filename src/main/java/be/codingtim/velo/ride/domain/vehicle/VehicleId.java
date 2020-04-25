package be.codingtim.velo.ride.domain.vehicle;

import java.util.Objects;

public class VehicleId {
    private int value;

    public VehicleId(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleId vehicleId = (VehicleId) o;
        return value == vehicleId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
