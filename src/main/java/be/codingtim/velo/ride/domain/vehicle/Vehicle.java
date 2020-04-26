package be.codingtim.velo.ride.domain.vehicle;

import be.codingtim.velo.ride.domain.station.LockId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "Vehicles")
public class Vehicle {

    @Id
    @Column(
            columnDefinition = "SMALLINT",
            name = "VehicleId"
    )
    private int vehicleId;

    @Column(
            columnDefinition = "SMALLINT",
            name = "LockId"
    )
    private Integer lockId;

    Vehicle() {
        //default constuctor
    }

    Vehicle(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleId getVehicleId() {
        return new VehicleId(vehicleId);
    }

    Optional<Integer> getLockId() {
        return Optional.ofNullable(lockId);
    }

    //TODO ONLY FOR TESTING, will refine once implementing
    public void lockAt(LockId lockId) {
        if (this.lockId != null) throw new IllegalStateException();
        this.lockId = lockId.getValue();
    }

    //grr I don't like this
    public void startRide() {
        this.lockId = null;
    }
}
