package be.codingtim.velo.ride.database.entities;

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
            columnDefinition = "TINYINT",
            name = "StationLockNr"
    )
    private int stationLockId;

    Lock() {
        //default constructor
    }

    public int getLockId() {
        return lockId;
    }

    public int getStationLockId() {
        return stationLockId;
    }
}
