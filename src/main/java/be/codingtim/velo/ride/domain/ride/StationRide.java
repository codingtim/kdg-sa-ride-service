package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.station.AvailableVehicleAtStation;
import be.codingtim.velo.ride.domain.station.LockId;
import be.codingtim.velo.ride.domain.station.VehicleLockedAtStation;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Clock;
import java.util.Optional;

@Entity(name = "StationRide")
@DiscriminatorValue(value = "StationRide")
public class StationRide extends Ride implements StationRideStarted {

    @Column(
            columnDefinition = "SMALLINT",
            name = "StartlockId"
    )
    private int startLockId;

    @Column(
            columnDefinition = "SMALLINT",
            name = "EndlockId"
    )
    private Integer endLockId;

    StationRide() {
        //default constructor
    }

    @Override
    RideType getType() {
        return RideType.STATION;
    }

    StationRide(Vehicle vehicle, AvailableVehicleAtStation freeVehicle, ActiveSubscription activeSubscription, Clock clock) {
        super(vehicle, activeSubscription, freeVehicle.getLocation(), clock);
        this.startLockId = freeVehicle.getLockId().getValue();
    }

    @Override
    public RideId getRideId() {
        return super.getRideId();
    }

    @Override
    public LockId getStartLockId() {
        return new LockId(startLockId);
    }

    Optional<LockId> getEndLockId() {
        return endLockId == null ? Optional.empty() : Optional.of(new LockId(endLockId));
    }

    void end(VehicleLockedAtStation vehicleLockedAtStation, Clock clock) {
        super.end(vehicleLockedAtStation.getLocation(), clock);
        this.endLockId = vehicleLockedAtStation.getLockId().getValue();
    }
}
