package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.location.VehicleLocation;
import be.codingtim.velo.ride.domain.point.GpsPoint;
import be.codingtim.velo.ride.domain.ride.exception.NoActiveRideForUser;
import be.codingtim.velo.ride.domain.ride.exception.OnlyFreeRideVehicleCanBeLockedAnywhere;
import be.codingtim.velo.ride.domain.ride.exception.OnlyOneActiveRideAllowed;
import be.codingtim.velo.ride.domain.ride.exception.OnlyStationVehicleCanBeLockedAtStation;
import be.codingtim.velo.ride.domain.station.*;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.user.User;
import be.codingtim.velo.ride.domain.user.UserId;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;
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
    private final VehicleLocation vehicleLocation;

    RideService(RideRepository rideRepository, Stations stations, Vehicles vehicles, VehicleLocation vehicleLocation) {
        this.rideRepository = rideRepository;
        this.stations = stations;
        this.vehicles = vehicles;
        this.vehicleLocation = vehicleLocation;
    }

    @Override
    @Transactional
    public StationRideStarted startRide(ActiveSubscription activeSubscription, StationId stationId, Clock clock) {
        if (userHasACurrentActiveRide(activeSubscription.getUserId())) throw new OnlyOneActiveRideAllowed();
        Station station = stations.get(stationId);
        AvailableVehicleAtStation availableVehicleAtStation = station.getAvailableVehicle();
        Vehicle vehicle = vehicles.get(availableVehicleAtStation.getVehicleId());
        StationRide ride = new StationRide(vehicle, availableVehicleAtStation, activeSubscription, clock);
        rideRepository.save(ride);
        return ride;
    }

    private boolean userHasACurrentActiveRide(UserId userId) {
        return rideRepository.findActiveRideByUserId(userId.getValue()).isPresent();
    }

    @Override
    @Transactional
    public CompletedStationRide endRide(User user, LockId lockId, Clock clock) {
        StationRide activeRide = getActiveStationRideOfUser(user);
        Station station = stations.get(lockId);
        Vehicle vehicle = vehicles.get(activeRide.getVehicleId());
        VehicleLockedAtStation vehicleLockedAtStation = station.lockVehicle(vehicle, lockId);
        activeRide.end(vehicleLockedAtStation, clock);
        LOGGER.info("Ended ride {} for user {} at station {} lock {}", activeRide.getRideId(), user.getUserId(), station.getStationId(), lockId);
        return new CompletedStationRide(user, vehicle, activeRide);
    }

    private StationRide getActiveStationRideOfUser(User user) {
        UserId userId = user.getUserId();
        Ride activeRide = rideRepository.findActiveRideByUserId(userId.getValue())
                .orElseThrow(() -> new NoActiveRideForUser(userId));
        if (!(activeRide.getType() == RideType.STATION)) throw new OnlyStationVehicleCanBeLockedAtStation();
        return (StationRide) activeRide;
    }

    @Override
    @Transactional
    public RideId startRide(ActiveSubscription activeSubscription, VehicleId vehicleId, Clock clock) {
        if (userHasACurrentActiveRide(activeSubscription.getUserId())) throw new OnlyOneActiveRideAllowed();
        Vehicle vehicle = vehicles.get(vehicleId);
        GpsPoint locationOfVehicle = vehicleLocation.getLocationOf(vehicle);
        FreeRide freeRide = new FreeRide(vehicle, activeSubscription, locationOfVehicle, clock);
        rideRepository.save(freeRide);
        return freeRide.getRideId();
    }

    @Override
    @Transactional
    public CompletedFreeRide endRide(User user, VehicleId vehicleId, Clock clock) {
        FreeRide activeRide = getActiveFreeRideOfUser(user);
        Vehicle vehicle = vehicles.get(vehicleId);
        GpsPoint locationOfVehicle = vehicleLocation.getLocationOf(vehicle);
        activeRide.end(locationOfVehicle, clock);
        LOGGER.info("Ended ride {} for user {} at location {}", activeRide.getRideId(), user.getUserId(), locationOfVehicle);
        return new CompletedFreeRide(user, vehicle, activeRide);
    }

    private FreeRide getActiveFreeRideOfUser(User user) {
        UserId userId = user.getUserId();
        Ride activeRide = rideRepository.findActiveRideByUserId(userId.getValue())
                .orElseThrow(() -> new NoActiveRideForUser(userId));
        if (activeRide.getType() != RideType.FREE) throw new OnlyFreeRideVehicleCanBeLockedAnywhere();
        return (FreeRide) activeRide;
    }
}
