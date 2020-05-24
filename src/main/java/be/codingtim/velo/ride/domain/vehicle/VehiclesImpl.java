package be.codingtim.velo.ride.domain.vehicle;

import be.codingtim.velo.ride.domain.vehicle.exception.VehicleNotFound;
import org.springframework.stereotype.Service;

@Service
class VehiclesImpl implements Vehicles {

    private final VehicleRepository vehicleRepository;

    VehiclesImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle get(VehicleId vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId.getValue());
        if(vehicle == null) throw new VehicleNotFound(vehicleId);
        return vehicle;
    }
}
