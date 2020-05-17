package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.point.GpsPoint;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import be.codingtim.velo.ride.domain.vehicle.VehicleData;

import java.util.List;

public final class StationData {

    public static final StationId UNKNOWN_STATION_ID = new StationId(-1);
    public static final LockId UNKNOWN_LOCK_ID = new LockId(-1);

    private StationData() {
        //private constructor for util class
    }

    public static class EmptyStation {
        public static final StationId STATION_ID = new StationId(1);
        public static final LockId LOCK_ID_1 = new LockId(10);
        public static final LockId LOCK_ID_2 = new LockId(11);
        public final Lock lock1 = new Lock(LOCK_ID_1.getValue(), 1, 1);
        public final Lock lock2 = new Lock(LOCK_ID_2.getValue(), 1, 2);
        public final Station station = new Station(STATION_ID.getValue(), GpsPoint.of(1.0, 1.1), List.of(lock1, lock2));
    }

    public static class HalfFullStation {
        public static final StationId STATION_ID = new StationId(2);
        public static final LockId LOCK_ID_1 = new LockId(20);
        public static final LockId LOCK_ID_2 = new LockId(21);
        public final Lock lock1 = new Lock(LOCK_ID_1.getValue(), 2, 1);
        public final Lock lock2 = new Lock(LOCK_ID_2.getValue(), 2, 2);
        public final Station station = new Station(STATION_ID.getValue(), GpsPoint.of(2.0, 2.1), List.of(lock1, lock2));
        public final Vehicle vehicle = VehicleData.stationVehicle();

        public HalfFullStation() {
            station.lockVehicle(vehicle, lock1.getLockId());
        }
    }

    public static class FullStation {

        public final Lock lock1 = new Lock(30, 3, 1);
        public final Lock lock2 = new Lock(31, 3, 2);
        public final Station station = new Station(3, GpsPoint.of(3.0, 3.1), List.of(lock1, lock2));
        public final Vehicle vehicle1 = VehicleData.stationVehicle();
        public final Vehicle vehicle2 = VehicleData.stationVehicle();

        public FullStation() {
            station.lockVehicle(vehicle1, lock1.getLockId());
            station.lockVehicle(vehicle2, lock2.getLockId());
        }
    }
}
