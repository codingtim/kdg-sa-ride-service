package be.codingtim.velo.ride.domain.ride;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "FreeRide")
@DiscriminatorValue(value = "FreeRide")
public class FreeRide extends Ride {

    FreeRide() {
        //default constructor
    }

    @Override
    RideType getType() {
        return RideType.FREE;
    }
}
