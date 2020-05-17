package be.codingtim.velo.ride.domain.location;

import be.codingtim.velo.ride.domain.point.GpsPoint;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;

import java.time.Instant;

public interface VehicleLocation {

    void heartbeat(Instant timestamp, VehicleId vehicleId, GpsPoint gpsPoint);

    GpsPoint getLocationOf(VehicleId vehicleId);
}
