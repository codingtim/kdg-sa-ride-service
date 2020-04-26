package be.codingtim.velo.ride.facade;

import be.codingtim.velo.ride.domain.ride.RideId;
import be.codingtim.velo.ride.domain.ride.StationRideService;
import be.codingtim.velo.ride.domain.station.StationId;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.user.User;
import be.codingtim.velo.ride.domain.user.UserId;
import be.codingtim.velo.ride.domain.user.Users;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
class RideFacadeImpl implements RideFacade {

    private final Users users;
    private final StationRideService stationRideService;

    RideFacadeImpl(Users users, StationRideService stationRideService) {
        this.users = users;
        this.stationRideService = stationRideService;
    }

    @Override
    public RideId startRide(Integer userId, Integer stationId) {
        User user = users.get(new UserId(userId));
        ActiveSubscription activeSubscription = user.getActiveSubscription(LocalDate.now());
        return stationRideService.startRide(activeSubscription, new StationId(stationId));
    }
}
