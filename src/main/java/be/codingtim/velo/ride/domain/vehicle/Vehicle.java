package be.codingtim.velo.ride.domain.vehicle;

import be.codingtim.velo.ride.domain.station.LockId;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "BikeLotId")
    private Bikelot bikelot;

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

    public VehicleType getVehicleType() {
        return bikelot.getVehicleType();
    }

    //grr I don't like this
    public void lockAt(LockId lockId) {
        if (this.lockId != null) throw new IllegalStateException();
        this.lockId = lockId.getValue();
    }

    //grr I don't like this
    public void startRide() {
        this.lockId = null;
    }

}
