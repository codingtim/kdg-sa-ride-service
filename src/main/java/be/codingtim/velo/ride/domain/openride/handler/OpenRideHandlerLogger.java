package be.codingtim.velo.ride.domain.openride.handler;

import be.codingtim.velo.ride.domain.ride.RideId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class OpenRideHandlerLogger implements OpenRideHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenRideHandlerLogger.class);

    @Override
    public void openRides(List<RideId> openRideIds) {
        LOGGER.warn("Handled open rides with ids {} by logging", openRideIds);
    }
}
