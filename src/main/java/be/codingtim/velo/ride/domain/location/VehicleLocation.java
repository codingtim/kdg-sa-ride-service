package be.codingtim.velo.ride.domain.location;

import be.codingtim.velo.ride.domain.point.GpsPoint;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;
import be.codingtim.velo.ride.domain.vehicle.VehicleType;

import java.time.Instant;
import java.util.Optional;

public interface VehicleLocation {

    void heartbeat(Instant timestamp, VehicleId vehicleId, GpsPoint gpsPoint);

    GpsPoint getLocationOf(Vehicle vehicle);

    Optional<NearestVehicle> nearestVehicleTo(GpsPoint gpsPoint, VehicleType vehicleType);
}
