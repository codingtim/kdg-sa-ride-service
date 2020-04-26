package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.station.FreeVehicleAtStation;
import be.codingtim.velo.ride.domain.station.LockId;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "StationRide")
@DiscriminatorValue(value = "StationRide")
public class StationRide extends Ride {

    @Column(
            columnDefinition = "SMALLINT",
            name = "StartlockId"
    )
    private int startLockId;

    @Column(
            columnDefinition = "SMALLINT",
            name = "EndlockId"
    )
    private int endLockId;

    StationRide() {
        //default constructor
    }

    public StationRide(Vehicle vehicle, FreeVehicleAtStation freeVehicle, ActiveSubscription activeSubscription) {
        super(vehicle, activeSubscription, freeVehicle.getLocation());
        this.startLockId = freeVehicle.getLockId().getValue();
    }

    LockId getStartLockId() {
        return new LockId(startLockId);
    }

    LockId getEndLockId() {
        return new LockId(endLockId);
    }
}
