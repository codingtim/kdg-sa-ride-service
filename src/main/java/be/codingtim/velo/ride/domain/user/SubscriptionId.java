package be.codingtim.velo.ride.domain.user;

import java.util.Objects;

public class SubscriptionId {

    private int value;

    public SubscriptionId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionId stationId = (SubscriptionId) o;
        return value == stationId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
