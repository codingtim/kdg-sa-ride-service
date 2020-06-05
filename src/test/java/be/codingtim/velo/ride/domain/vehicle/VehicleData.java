package be.codingtim.velo.ride.domain.vehicle;

import java.util.Random;

import static be.codingtim.velo.ride.domain.vehicle.VehicleType.*;

public final class VehicleData {

    public static final VehicleId UNKNOWN_VEHICLE_ID = new VehicleId(-1);

    public static final Vehicle STATION_VEHICLE_1 = new Vehicle(1, VELO_BIKE);
    public static final Vehicle STATION_VEHICLE_2 = new Vehicle(2, VELO_BIKE);
    public static final Vehicle STATION_VEHICLE_3 = new Vehicle(3, VELO_BIKE);
    public static final Vehicle FREE_VEHICLE_1 = new Vehicle(4, ROAMING_E_STEP);
    public static final Vehicle FREE_VEHICLE_2 = new Vehicle(5, ROAMING_E_STEP);
    public static final Vehicle FREE_VEHICLE_3 = new Vehicle(6, ROAMING_SCOOTER);
    public static final Vehicle FREE_VEHICLE_4 = new Vehicle(7, ROAMING_SCOOTER);

    private VehicleData() {
        //private constructor for util class
    }

    public static Vehicle stationVehicle() {
        return new Vehicle(new Random().nextInt(), VELO_BIKE);
    }
}
