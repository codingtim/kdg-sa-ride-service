package be.codingtim.velo.ride.domain.station;

import java.util.Objects;

public class StationId {

    private int value;

    public StationId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationId stationId = (StationId) o;
        return value == stationId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
