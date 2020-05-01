package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.payment.BillableRide;
import be.codingtim.velo.ride.domain.user.SubscriptionType;
import be.codingtim.velo.ride.domain.user.User;
import be.codingtim.velo.ride.domain.user.UserId;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import be.codingtim.velo.ride.domain.vehicle.VehicleType;

import java.time.Duration;

public class CompletedStationRide implements BillableRide {
    private final User user;
    private final Vehicle vehicle;
    private final StationRide stationRide;

    CompletedStationRide(User user, Vehicle vehicle, StationRide stationRide) {
        this.user = user;
        this.vehicle = vehicle;
        this.stationRide = stationRide;
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
        return user.getTypeOf(stationRide.getSubscriptionId());
    }

    @Override
    public Duration getRideDuration() {
        return stationRide.getRideDuration();
    }
}
