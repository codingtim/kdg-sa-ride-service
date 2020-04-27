package be.codingtim.velo.ride.domain.vehicle;

import javax.persistence.AttributeConverter;

import static be.codingtim.velo.ride.domain.vehicle.VehicleType.*;

class VehicleTypeAttributeConverter implements AttributeConverter<VehicleType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(VehicleType vehicleType) {
        switch (vehicleType) {
            case VELO_BIKE:
                return 1;
            case VELO_E_BIKE:
                return 2;
            case ROAMING_E_STEP:
                return 3;
            case ROAMING_SCOOTER:
                return 4;
            default:
                throw new IllegalStateException("No mapping specified for vehicleType: " + vehicleType);
        }
    }

    @Override
    public VehicleType convertToEntityAttribute(Integer integer) {
        switch (integer) {
            case 1:
                return VELO_BIKE;
            case 2:
                return VELO_E_BIKE;
            case 3:
                return ROAMING_E_STEP;
            case 4:
                return ROAMING_SCOOTER;
            default:
                throw new IllegalArgumentException("Unexpected value: " + integer);
        }
    }
}
