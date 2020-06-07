package be.codingtim.velo.ride.domain.point;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GpsPointTest {

    @Test
    void gpsPointCoordinates() {
        double x = 1.2;
        double y = 1.3;
        GpsPoint gpsPoint = GpsPoint.of(x, y);
        assertEquals(x, gpsPoint.getX());
        assertEquals(y, gpsPoint.getY());
    }
}