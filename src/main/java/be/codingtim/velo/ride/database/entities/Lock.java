package be.codingtim.velo.ride.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Locks")
public class Lock {

    @Id
    @Column(name = "LockId")
    private String lockId;
    @Column(name = "StationLockNr")
    private String stationLockId;

    Lock() {
        //default constructor
    }

    public String getLockId() {
        return lockId;
    }

    public String getStationLockId() {
        return stationLockId;
    }
}
