package be.codingtim.velo.ride.domain.location;

import be.codingtim.velo.ride.domain.point.GpsPoint;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
class VehicleLocationImpl implements VehicleLocation {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleLocationImpl.class);

    private final ConcurrentMap<VehicleId, GpsPoint> vehicleLocations = new ConcurrentHashMap<>();

    @Override
    public void heartbeat(Instant timestamp, VehicleId vehicleId, GpsPoint gpsPoint) {
        vehicleLocations.put(vehicleId, gpsPoint);
        LOGGER.debug("Vehicle {} location {}.", vehicleId, gpsPoint);
    }

    @Override
    public GpsPoint getLocationOf(VehicleId vehicleId) {
        return vehicleLocations.get(vehicleId);
    }
}
