package be.codingtim.velo.ride.domain.vehicle;

import java.util.Random;

import static be.codingtim.velo.ride.domain.vehicle.VehicleType.VELO_BIKE;

public final class VehicleData {

    public static final Vehicle STATION_VEHICLE_1 = new Vehicle(1, VELO_BIKE);
    public static final Vehicle STATION_VEHICLE_2 = new Vehicle(2, VELO_BIKE);
    public static final Vehicle STATION_VEHICLE_3 = new Vehicle(3, VELO_BIKE);

    private VehicleData() {
        //private constructor for util class
    }

    public static Vehicle stationVehicle() {
        return new Vehicle(new Random().nextInt(), VELO_BIKE);
    }
}
