package be.codingtim.velo.ride.domain.ride;

import java.util.Objects;

public class RideId {

    private long value;

    public RideId(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RideId stationId = (RideId) o;
        return value == stationId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }
}
