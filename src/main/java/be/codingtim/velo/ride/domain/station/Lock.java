package be.codingtim.velo.ride.domain.station;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Locks")
public class Lock {

    @Id
    @Column(
            columnDefinition = "SMALLINT",
            name = "LockId"
    )
    private int lockId;
    @Column(
            columnDefinition = "SMALLINT",
            name = "StationId"
    )
    private int stationId;
    @Column(
            columnDefinition = "TINYINT",
            name = "StationLockNr"
    )
    private int stationLockId;
    @Column(
            columnDefinition = "SMALLINT",
            name = "VehicleId"
    )
    private Integer vehicleId;

    Lock() {
        //default constructor
    }

    public int getLockId() {
        return lockId;
    }

    public int getStationLockId() {
        return stationLockId;
    }

    public int getStationId() {
        return stationId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }
}
