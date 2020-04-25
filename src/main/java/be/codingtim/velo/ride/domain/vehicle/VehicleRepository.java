package be.codingtim.velo.ride.domain.vehicle;

interface VehicleRepository {

    Vehicle findById(int vehicleId);
}
