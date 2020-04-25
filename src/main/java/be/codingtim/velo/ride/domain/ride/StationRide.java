package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.station.LockId;

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

    LockId getStartLockId() {
        return new LockId(startLockId);
    }

    LockId getEndLockId() {
        return new LockId(endLockId);
    }
}
