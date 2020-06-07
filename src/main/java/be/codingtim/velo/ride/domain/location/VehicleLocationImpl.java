package be.codingtim.velo.ride.domain.location;

import be.codingtim.velo.ride.domain.point.Distance;
import be.codingtim.velo.ride.domain.point.GpsPoint;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;
import be.codingtim.velo.ride.domain.vehicle.VehicleType;
import be.codingtim.velo.ride.domain.vehicle.Vehicles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
class VehicleLocationImpl implements VehicleLocation {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleLocationImpl.class);

    private final ConcurrentMap<VehicleId, InMemoryVehicleLocation> vehicleLocations = new ConcurrentHashMap<>();
    private final Vehicles vehicles;

    VehicleLocationImpl(Vehicles vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public void heartbeat(Instant timestamp, VehicleId vehicleId, GpsPoint gpsPoint) {
        InMemoryVehicleLocation inMemoryVehicleLocation = vehicleLocations.computeIfAbsent(
                vehicleId,
                id -> new InMemoryVehicleLocation(id, vehicles.get(vehicleId).getVehicleType())
        );
        inMemoryVehicleLocation.gpsPoint = gpsPoint;
        LOGGER.debug("Vehicle {} location {}.", vehicleId, gpsPoint);
    }

    @Override
    public GpsPoint getLocationOf(Vehicle vehicle) {
        return vehicleLocations.get(vehicle.getVehicleId()).gpsPoint;
    }

    @Override
    public Optional<NearestVehicle> nearestVehicleTo(GpsPoint gpsPoint, VehicleType vehicleType) {
        //TODO should we only search for vehicles not currently in a ride?
        return vehicleLocations.values().stream()
                .filter(inMemoryVehicleLocation -> inMemoryVehicleLocation.vehicleType == vehicleType)
                .map(inMemoryVehicleLocation -> inMemoryVehicleLocation.withDistanceTo(gpsPoint))
                .min(InMemoryVehicleLocationWithDistanceTo::compareTo)
                .map(closest -> closest.inMemoryVehicleLocation)
                .map(closest -> new NearestVehicle(closest.id, closest.gpsPoint));
    }

    private static final class InMemoryVehicleLocation {

        private final VehicleId id;
        private final VehicleType vehicleType;
        private GpsPoint gpsPoint;

        public InMemoryVehicleLocation(VehicleId id, VehicleType vehicleType) {
            this.id = id;
            this.vehicleType = vehicleType;
        }

        public InMemoryVehicleLocationWithDistanceTo withDistanceTo(GpsPoint gpsPoint) {
            return new InMemoryVehicleLocationWithDistanceTo(this, gpsPoint);
        }

    }

    private static class InMemoryVehicleLocationWithDistanceTo implements Comparable<InMemoryVehicleLocationWithDistanceTo> {

        private final InMemoryVehicleLocation inMemoryVehicleLocation;
        private final Distance distance;

        public InMemoryVehicleLocationWithDistanceTo(InMemoryVehicleLocation inMemoryVehicleLocation, GpsPoint gpsPoint) {
            this.inMemoryVehicleLocation = inMemoryVehicleLocation;
            this.distance = inMemoryVehicleLocation.gpsPoint.distanceTo(gpsPoint);
        }

        @Override
        public int compareTo(InMemoryVehicleLocationWithDistanceTo o) {
            return distance.compareTo(o.distance);
        }
    }
}
