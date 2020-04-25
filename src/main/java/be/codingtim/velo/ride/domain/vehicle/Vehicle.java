package be.codingtim.velo.ride.domain.vehicle;

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

    VehicleId getVehicleId() {
        return new VehicleId(vehicleId);
    }

    Optional<Integer> getLockId() {
        return Optional.ofNullable(lockId);
    }
}
