package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.station.AvailableVehicleAtStation;
import be.codingtim.velo.ride.domain.station.Station;
import be.codingtim.velo.ride.domain.station.StationId;
import be.codingtim.velo.ride.domain.station.Stations;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import be.codingtim.velo.ride.domain.vehicle.Vehicles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;

@Service
class RideService implements StationRideService {

    private final RideRepository rideRepository;
    private final Stations stations;
    private final Vehicles vehicles;

    RideService(RideRepository rideRepository, Stations stations, Vehicles vehicles) {
        this.rideRepository = rideRepository;
        this.stations = stations;
        this.vehicles = vehicles;
    }

    @Override
    @Transactional
    public RideId startRide(ActiveSubscription activeSubscription, StationId stationId, Clock clock) {
        Station station = stations.get(stationId);
        AvailableVehicleAtStation availableVehicleAtStation = station.getAvailableVehicle();
        Vehicle vehicle = vehicles.get(availableVehicleAtStation.getVehicleId());
        StationRide ride = new StationRide(vehicle, availableVehicleAtStation, activeSubscription, clock);
        rideRepository.save(ride);
        return ride.getRideId();
    }
}
