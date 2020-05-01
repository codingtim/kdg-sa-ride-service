package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.location.GpsPoint;
import be.codingtim.velo.ride.domain.station.exception.StationHasNoAvailableVehicle;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Stations")
public class Station {

    @Id
    @Column(
            columnDefinition = "SMALLINT",
            name = "StationId"
    )
    private int stationId;

    @Column(
            columnDefinition = "GEOMETRY",
            name = "GPSCoord"
    )
    private Point location;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "stationId"
    )
    private List<Lock> locks;

    Station() {
        //default constructor
    }

    Station(int stationId, GpsPoint location, List<Lock> locks) {
        this.stationId = stationId;
        this.location = location.getPoint();
        this.locks = new ArrayList<>(locks);
    }

    StationId getStationId() {
        return new StationId(stationId);
    }

    GpsPoint getLocation() {
        return GpsPoint.of(location);
    }

    List<Lock> getLocks() {
        return locks;
    }

    public AvailableVehicleAtStation getAvailableVehicle() {
        Lock lockWithVehicleAvailable = locks.stream()
                .filter(Lock::hasVehicle)
                .findFirst()
                .orElseThrow(() -> new StationHasNoAvailableVehicle(getStationId()));
        return new AvailableVehicleAtStation(lockWithVehicleAvailable.removeVehicle(), getLocation());
    }

    public FreeStationLocks getFreeLocks() {
        return new FreeStationLocks(locks.stream()
                .filter(Lock::isFree)
                .map(Lock::getLockId)
                .collect(Collectors.toList()));
    }

    //TODO ONLY FOR TESTING, will refine once implementing
    void lock(Vehicle vehicle) {
        Lock freeLock = locks.stream()
                .filter(lock -> !lock.hasVehicle())
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        freeLock.lock(vehicle);
    }
}
