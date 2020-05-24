package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.bill.BillableRide;
import be.codingtim.velo.ride.domain.user.SubscriptionType;
import be.codingtim.velo.ride.domain.user.User;
import be.codingtim.velo.ride.domain.user.UserId;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import be.codingtim.velo.ride.domain.vehicle.VehicleType;

import java.time.Duration;

public class CompletedFreeRide implements BillableRide {
    private final User user;
    private final Vehicle vehicle;
    private final FreeRide ride;

    public CompletedFreeRide(User user, Vehicle vehicle, FreeRide ride) {
        this.user = user;
        this.vehicle = vehicle;
        this.ride = ride;
    }

    @Override
    public UserId getUserId() {
        return user.getUserId();
    }

    @Override
    public VehicleType getVehicleType() {
        return vehicle.getVehicleType();
    }

    @Override
    public SubscriptionType getSubscriptionType() {
        return user.getTypeOf(ride.getSubscriptionId());
    }

    @Override
    public Duration getRideDuration() {
        return ride.getRideDuration();
    }
}
