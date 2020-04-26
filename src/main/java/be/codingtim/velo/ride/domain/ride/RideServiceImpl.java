package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.station.FreeVehicleAtStation;
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
class RideServiceImpl implements StationRideService {

    private final RideRepository rideRepository;
    private final Stations stations;
    private final Vehicles vehicles;

    RideServiceImpl(RideRepository rideRepository, Stations stations, Vehicles vehicles) {
        this.rideRepository = rideRepository;
        this.stations = stations;
        this.vehicles = vehicles;
    }

    @Override
    @Transactional
    public RideId startRide(ActiveSubscription activeSubscription, StationId stationId, Clock clock) {
        Station station = stations.get(stationId);
        FreeVehicleAtStation freeVehicleAtStation = station.getFreeVehicle();
        Vehicle vehicle = vehicles.get(freeVehicleAtStation.getVehicleId());
        StationRide ride = new StationRide(vehicle, freeVehicleAtStation, activeSubscription, clock);
        rideRepository.save(ride);
        return ride.getRideId();
    }
}
