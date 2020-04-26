package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.location.GpsPoint;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Stations")
public class Station {

    @Id
    @Column(
            columnDefinition = "SMALLINT",
            name = "StationId"
    )
    private int stationId;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "stationId"
    )
    private List<Lock> locks;

    @Column(
            columnDefinition = "GEOMETRY",
            name = "GPSCoord"
    )
    private Point location;

    Station() {
        //default constructor
    }

    StationId getStationId() {
        return new StationId(stationId);
    }

    List<Lock> getLocks() {
        return locks;
    }

    GpsPoint getLocation() {
        return GpsPoint.of(location);
    }

    public LockWithAvailableVehicle getAvailableVehicle() {
        Lock lockWithVehicleAvailable = locks.stream()
                .filter(Lock::hasVehicle)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        return lockWithVehicleAvailable.removeVehicle();
    }

}
