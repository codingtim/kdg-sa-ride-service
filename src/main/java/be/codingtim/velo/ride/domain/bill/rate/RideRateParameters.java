package be.codingtim.velo.ride.domain.bill.rate;

import be.codingtim.velo.ride.domain.user.SubscriptionType;
import be.codingtim.velo.ride.domain.vehicle.VehicleType;

import java.util.Objects;

public class RideRateParameters {
    private final SubscriptionType subscriptionType;
    private final VehicleType vehicleType;

    public RideRateParameters(SubscriptionType subscriptionType, VehicleType vehicleType) {
        this.subscriptionType = subscriptionType;
        this.vehicleType = vehicleType;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RideRateParameters that = (RideRateParameters) o;
        return subscriptionType == that.subscriptionType &&
                vehicleType == that.vehicleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriptionType, vehicleType);
    }

    @Override
    public String toString() {
        return "RideRateParameters{" +
                "subscriptionType=" + subscriptionType +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
