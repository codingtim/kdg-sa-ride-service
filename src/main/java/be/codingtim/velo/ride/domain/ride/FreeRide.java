package be.codingtim.velo.ride.domain.ride;


import be.codingtim.velo.ride.domain.point.GpsPoint;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Clock;

@Entity(name = "FreeRide")
@DiscriminatorValue(value = "FreeRide")
public class FreeRide extends Ride {

    FreeRide() {
        //default constructor
    }

    public FreeRide(Vehicle vehicle, ActiveSubscription activeSubscription, GpsPoint startPoint, Clock clock) {
        super(vehicle, activeSubscription, startPoint, clock);
    }

    @Override
    RideType getType() {
        return RideType.FREE;
    }
}
