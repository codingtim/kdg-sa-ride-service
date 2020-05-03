package be.codingtim.velo.ride.domain.openride.time;

import be.codingtim.velo.ride.domain.openride.handler.OpenRideHandler;
import be.codingtim.velo.ride.domain.ride.RideId;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeBasedOpenRideDetectionTest {
    private final Clock clock = Clock.fixed(Instant.now(), ZoneId.of("UTC"));
    private final Duration durationBeforeConsideredOpenEnded = Duration.ofHours(1);
    private final List<RideId> rideIds = List.of(new RideId(1), new RideId(2));

    private final RidesActiveLongerThanQueryDummy ridesActiveLongerThanQuery = new RidesActiveLongerThanQueryDummy(rideIds);
    private final OpenRideHandlerDummy openRideHandler = new OpenRideHandlerDummy();

    private final TimeBasedOpenRideDetection timeBasedOpenRideDetection = new TimeBasedOpenRideDetection(
            durationBeforeConsideredOpenEnded.toString(), ridesActiveLongerThanQuery,
            openRideHandler, clock
    );

    @Test
    void run() {
        timeBasedOpenRideDetection.run();
        assertEquals(Instant.now(clock).minus(durationBeforeConsideredOpenEnded), ridesActiveLongerThanQuery.activeSince);
        assertEquals(rideIds, openRideHandler.openRideIds);
    }

    private static class OpenRideHandlerDummy implements OpenRideHandler {

        private List<RideId> openRideIds;

        @Override
        public void openRides(List<RideId> openRideIds) {
            this.openRideIds = openRideIds;
        }
    }

    private static class RidesActiveLongerThanQueryDummy implements RidesActiveLongerThanQuery {

        private List<RideId> rideIds;
        private Instant activeSince;

        public RidesActiveLongerThanQueryDummy(List<RideId> rideIds) {
            this.rideIds = rideIds;
        }

        @Override
        public List<RideId> findAllActiveBefore(Instant activeSince) {
            this.activeSince = activeSince;
            return rideIds;
        }
    }
}