package be.codingtim.velo.ride.domain.station;

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

    Station() {
        //default constructor
    }

    public int getStationId() {
        return stationId;
    }

    //TODO restrict access
    public List<Lock> getLocks() {
        return locks;
    }
}
