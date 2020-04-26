package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.location.GpsPoint;

import java.util.List;

public final class StationsData {

    public static final StationId UNKNOWN_STATION_ID = new StationId(-1);

    public static final Station EMPTY_STATION = new Station(1, GpsPoint.of(1.0, 1.1), List.of(
            new Lock(10, 1, 1),
            new Lock(11, 1, 2)
    ));
    public static final Station HALF_FULL_STATION = new Station(2, GpsPoint.of(2.0, 2.1), List.of(
            new Lock(20, 2, 1),
            new Lock(21, 2, 2)
    ));
    public static final Station FULL_STATION = new Station(3, GpsPoint.of(3.0, 3.1), List.of(
            new Lock(30, 3, 1),
            new Lock(31, 3, 2)
    ));

    private StationsData() {
        //private constructor for util class
    }
}
