package be.codingtim.velo.ride.facade;

import be.codingtim.velo.ride.domain.bill.Bills;
import be.codingtim.velo.ride.domain.ride.*;
import be.codingtim.velo.ride.domain.station.LockId;
import be.codingtim.velo.ride.domain.station.StationId;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.user.User;
import be.codingtim.velo.ride.domain.user.UserId;
import be.codingtim.velo.ride.domain.user.Users;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;

@Service
class RideFacadeImpl implements RideFacade {

    private final Users users;
    private final RideService rideService;
    private final Bills bills;
    private final Clock clock;

    RideFacadeImpl(Users users, RideService rideService, Bills bills, Clock clock) {
        this.users = users;
        this.rideService = rideService;
        this.bills = bills;
        this.clock = clock;
    }

    @Override
    public StationRideStarted startStationRide(Integer userId, Integer stationId) {
        User user = users.get(new UserId(userId));
        ActiveSubscription activeSubscription = user.getActiveSubscription(LocalDate.now(clock));
        return rideService.startRide(activeSubscription, new StationId(stationId), clock);
    }

    @Override
    public void endStationRide(Integer userId, Integer lockId) {
        User user = users.get(new UserId(userId));
        CompletedStationRide completedStationRide = rideService.endRide(user, new LockId(lockId), clock);
        bills.createBillFor(completedStationRide);
    }

    @Override
    public RideId startFreeVehicleRide(Integer userId, Integer vehicleId) {
        User user = users.get(new UserId(userId));
        ActiveSubscription activeSubscription = user.getActiveSubscription(LocalDate.now(clock));
        return rideService.startRide(activeSubscription, new VehicleId(vehicleId), clock);
    }

    @Override
    public void endFreeVehicleRide(Integer userId, Integer vehicleId) {
        User user = users.get(new UserId(userId));
        CompletedFreeRide completedFreeRide = rideService.endRide(user, new VehicleId(vehicleId), clock);
        bills.createBillFor(completedFreeRide);
    }
}
