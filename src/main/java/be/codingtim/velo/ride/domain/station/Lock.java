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

    Lock(int lockId, int stationId, int stationLockId) {
        this.lockId = lockId;
        this.stationId = stationId;
        this.stationLockId = stationLockId;
    }

    LockId getLockId() {
        return new LockId(lockId);
    }

    boolean hasVehicle() {
        return vehicleId != null;
    }

    FreeVehicleAtLock removeVehicle() {
        FreeVehicleAtLock freeVehicleAtLock = new FreeVehicleAtLock(lockId, vehicleId);
        vehicleId = null;
        return freeVehicleAtLock;
    }
}
