package be.codingtim.velo.ride.domain.openride.time;

import be.codingtim.velo.ride.domain.openride.OpenRideDetection;
import be.codingtim.velo.ride.domain.openride.handler.OpenRideHandler;
import be.codingtim.velo.ride.domain.ride.RideId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
class TimeBasedOpenRideDetection implements OpenRideDetection {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeBasedOpenRideDetection.class);

    private final Duration durationBeforeConsideredOpenEnded;
    private final RidesActiveLongerThanQuery ridesActiveLongerThanQuery;
    private final OpenRideHandler openRideHandler;
    private final Clock clock;

    TimeBasedOpenRideDetection(
            @Value("${open.ride.time.duration}") String durationBeforeConsideredOpenEnded,
            RidesActiveLongerThanQuery ridesActiveLongerThanQuery,
            OpenRideHandler openRideHandler,
            Clock clock
    ) {
        this.durationBeforeConsideredOpenEnded = Duration.parse(durationBeforeConsideredOpenEnded);
        this.ridesActiveLongerThanQuery = ridesActiveLongerThanQuery;
        this.openRideHandler = openRideHandler;
        this.clock = clock;
    }

    @Override
    public void run() {
        LOGGER.info("Looking for open ride, longer than {}", durationBeforeConsideredOpenEnded);
        Instant activeSince = Instant.now(clock).minus(durationBeforeConsideredOpenEnded);
        List<RideId> openRides = ridesActiveLongerThanQuery.findAllActiveBefore(activeSince);
        LOGGER.info("Time based open ride detection found {} open rides", openRides.size());
        LOGGER.debug("Time based open ride detection found open rides with id {}", openRides);
        openRideHandler.openRides(openRides);
    }
}
