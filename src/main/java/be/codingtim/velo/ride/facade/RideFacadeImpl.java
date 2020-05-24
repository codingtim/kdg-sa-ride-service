package be.codingtim.velo.ride.facade;

import be.codingtim.velo.ride.domain.bill.Bills;
import be.codingtim.velo.ride.domain.ride.CompletedStationRide;
import be.codingtim.velo.ride.domain.ride.StationRideService;
import be.codingtim.velo.ride.domain.ride.StationRideStarted;
import be.codingtim.velo.ride.domain.station.LockId;
import be.codingtim.velo.ride.domain.station.StationId;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.user.User;
import be.codingtim.velo.ride.domain.user.UserId;
import be.codingtim.velo.ride.domain.user.Users;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;

@Service
class RideFacadeImpl implements RideFacade {

    private final Users users;
    private final StationRideService stationRideService;
    private final Bills bills;
    private final Clock clock;

    RideFacadeImpl(Users users, StationRideService stationRideService, Bills bills, Clock clock) {
        this.users = users;
        this.stationRideService = stationRideService;
        this.bills = bills;
        this.clock = clock;
    }

    @Override
    public StationRideStarted startStationRide(Integer userId, Integer stationId) {
        User user = users.get(new UserId(userId));
        ActiveSubscription activeSubscription = user.getActiveSubscription(LocalDate.now(clock));
        return stationRideService.startRide(activeSubscription, new StationId(stationId), clock);
    }

    @Override
    public void endStationRide(Integer userId, Integer lockId) {
        User user = users.get(new UserId(userId));
        CompletedStationRide completedStationRide = stationRideService.endRide(user, new LockId(lockId), clock);
        bills.createBillFor(completedStationRide);
    }
}
