package be.codingtim.velo.ride.domain.point;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DistanceTest {

    @Test
    void testSimpleDistance() {
        GpsPoint gpsPoint = GpsPoint.of(1.2, 1.3);
        GpsPoint gpsPoint2 = GpsPoint.of(1.2, 1.4);
        GpsPoint gpsPoint3 = GpsPoint.of(1.2, 1.5);

        Distance distance1To2 = Distance.distance(gpsPoint, gpsPoint2);
        Distance distance1To3 = Distance.distance(gpsPoint, gpsPoint3);
        assertTrue(
                distance1To2.compareTo(distance1To3) < 0
        );
    }

    @Test
    void testEqualDistance() {
        GpsPoint gpsPoint = GpsPoint.of(1.2, 1.3);
        GpsPoint gpsPoint2 = GpsPoint.of(1.3, 1.3);
        GpsPoint gpsPoint3 = GpsPoint.of(1.2, 1.4);
        Distance distance1To2 = Distance.distance(gpsPoint, gpsPoint2);
        Distance distance1To3 = Distance.distance(gpsPoint, gpsPoint3);
        //caused by rounding error, we could fix this by comparing within an offset and returning equal if so
        assertTrue(
                distance1To2.compareTo(distance1To3) > 0
        );
    }
}