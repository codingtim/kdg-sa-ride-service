package be.codingtim.velo.ride.domain.vehicle;

import javax.persistence.*;

@Entity
@Table(name = "Bikelots")
class Bikelot {

    @Id
    @Column(
            columnDefinition = "SMALLINT",
            name = "BikeLotId"
    )
    private int bikeLotId;

    @Convert(converter = VehicleTypeAttributeConverter.class)
    @Column(
            columnDefinition = "TINYINT",
            name = "BikeTypeID"
    )
    private VehicleType vehicleType;

    Bikelot() {
        //default constructor
    }

    Bikelot(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    VehicleType getVehicleType() {
        return vehicleType;
    }
}
