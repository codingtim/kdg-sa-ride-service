package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.location.GpsPoint;

import java.util.List;

import static be.codingtim.velo.ride.domain.vehicle.VehicleData.*;

public final class StationsData {

    public static final StationId UNKNOWN_STATION_ID = new StationId(-1);

    public static final Lock EMPTY_STATION_LOCK_1 = new Lock(10, 1, 1);
    public static final Lock EMPTY_STATION_LOCK_2 = new Lock(11, 1, 2);
    public static final Station EMPTY_STATION = new Station(1, GpsPoint.of(1.0, 1.1), List.of(
            EMPTY_STATION_LOCK_1,
            EMPTY_STATION_LOCK_2
    ));

    public static final Lock HALF_FULL_STATION_LOCK_1 = new Lock(20, 2, 1);
    public static final Lock HALF_FULL_STATION_LOCK_2 = new Lock(21, 2, 2);
    public static final Station HALF_FULL_STATION = new Station(2, GpsPoint.of(2.0, 2.1), List.of(
            HALF_FULL_STATION_LOCK_1,
            HALF_FULL_STATION_LOCK_2
    ));

    public static final Lock FULL_STATION_LOCK_1 = new Lock(30, 3, 1);
    public static final Lock FULL_STATION_LOCK_2 = new Lock(31, 3, 2);
    public static final Station FULL_STATION = new Station(3, GpsPoint.of(3.0, 3.1), List.of(
            FULL_STATION_LOCK_1,
            FULL_STATION_LOCK_2
    ));

    // I do not like this but do not currently see another way
    static {
        HALF_FULL_STATION.lock(STATION_VEHICLE_1);
        FULL_STATION.lock(STATION_VEHICLE_2);
        FULL_STATION.lock(STATION_VEHICLE_3);
    }

    private StationsData() {
        //private constructor for util class
    }
}
