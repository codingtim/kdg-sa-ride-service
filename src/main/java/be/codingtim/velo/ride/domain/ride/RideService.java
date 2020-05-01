package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.ride.exception.NoActiveRideForUser;
import be.codingtim.velo.ride.domain.ride.exception.OnlyStationVehicleCanBeLockedAtStation;
import be.codingtim.velo.ride.domain.station.*;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.user.User;
import be.codingtim.velo.ride.domain.user.UserId;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import be.codingtim.velo.ride.domain.vehicle.Vehicles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;

@Service
class RideService implements StationRideService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RideService.class);

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
    public CompletedStationRide endRide(User user, LockId lockId, Clock clock) {
        StationRide activeRide = getActiveRideOfUser(user);
        Station station = stations.get(lockId);
        Vehicle vehicle = vehicles.get(activeRide.getVehicleId());
        VehicleLockedAtStation vehicleLockedAtStation = station.lockVehicle(vehicle, lockId);
        activeRide.end(vehicleLockedAtStation, clock);
        LOGGER.info("Ended ride {} for user {} at station {} lock {}", activeRide.getRideId(), user.getUserId(), station.getStationId(), lockId);
        return new CompletedStationRide(user, vehicle, activeRide);
    }

    private StationRide getActiveRideOfUser(User user) {
        UserId userId = user.getUserId();
        Ride activeRide = rideRepository.findActiveRideByUserId(userId.getValue())
                .orElseThrow(() -> new NoActiveRideForUser(userId));
        if(!(activeRide.getType() == RideType.STATION)) throw new OnlyStationVehicleCanBeLockedAtStation();
        return (StationRide) activeRide;
    }
}
