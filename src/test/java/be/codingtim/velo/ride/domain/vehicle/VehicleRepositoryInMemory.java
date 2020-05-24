package be.codingtim.velo.ride.domain.vehicle;

import java.util.ArrayList;
import java.util.List;

class VehicleRepositoryInMemory implements VehicleRepository {

    private final List<Vehicle> vehicles = new ArrayList<>();

    public VehicleRepositoryInMemory() {
        vehicles.add(VehicleData.STATION_VEHICLE_1);
        vehicles.add(VehicleData.STATION_VEHICLE_2);
        vehicles.add(VehicleData.STATION_VEHICLE_3);
        vehicles.add(VehicleData.FREE_VEHICLE_1);
    }

    @Override
    public Vehicle findById(int vehicleId) {
        return vehicles.stream()
                .filter(station -> station.getVehicleId().getValue() == vehicleId)
                .findFirst()
                .orElse(null);
    }

}
