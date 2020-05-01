package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.ride.exception.NoActiveRideForUser;
import be.codingtim.velo.ride.domain.ride.exception.OnlyStationVehicleCanBeLockedAtStation;
import be.codingtim.velo.ride.domain.station.*;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.user.UserId;
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
        //TODO do we need to have a business rule that there is only one active ride / user?
        Station station = stations.get(stationId);
        AvailableVehicleAtStation availableVehicleAtStation = station.getAvailableVehicle();
        Vehicle vehicle = vehicles.get(availableVehicleAtStation.getVehicleId());
        StationRide ride = new StationRide(vehicle, availableVehicleAtStation, activeSubscription, clock);
        rideRepository.save(ride);
        return ride.getRideId();
    }

    @Override
    @Transactional
    public void endRide(UserId userId, LockId lockId, Clock clock) {
        Ride activeRide = rideRepository.findActiveRideByUserId(userId.getValue())
                .orElseThrow(() -> new NoActiveRideForUser(userId));
        if(!(activeRide.getType() == RideType.STATION)) throw new OnlyStationVehicleCanBeLockedAtStation();
        StationRide activeStationRide = (StationRide) activeRide;
        Station station = stations.get(lockId);
        Vehicle vehicle = vehicles.get(activeStationRide.getVehicleId());
        VehicleLockedAtStation vehicleLockedAtStation = station.lockVehicle(vehicle, lockId);
        activeStationRide.end(vehicleLockedAtStation, clock);
    }
}
