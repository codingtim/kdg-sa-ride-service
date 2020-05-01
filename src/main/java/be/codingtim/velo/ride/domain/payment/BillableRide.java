package be.codingtim.velo.ride.domain.payment;

import be.codingtim.velo.ride.domain.user.SubscriptionType;
import be.codingtim.velo.ride.domain.user.UserId;
import be.codingtim.velo.ride.domain.vehicle.VehicleType;

import java.time.Duration;

public interface BillableRide {

    UserId getUserId();
    VehicleType getVehicleType();
    SubscriptionType getSubscriptionType();
    Duration getRideDuration();

}
