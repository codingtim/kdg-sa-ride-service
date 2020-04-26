package be.codingtim.velo.ride.domain.vehicle;

import org.springframework.stereotype.Service;

@Service
class VehiclesImpl implements Vehicles {

    private final VehicleRepository vehicleRepository;

    VehiclesImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle get(VehicleId vehicleId) {
        return vehicleRepository.findById(vehicleId.getValue());
    }
}
