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

    VehicleType getVehicleType() {
        return vehicleType;
    }
}
