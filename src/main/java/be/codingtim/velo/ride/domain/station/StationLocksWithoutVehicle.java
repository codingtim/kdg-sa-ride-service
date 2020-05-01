package be.codingtim.velo.ride.domain.station;

import java.util.ArrayList;
import java.util.List;

public class StationLocksWithoutVehicle {

    private final List<LockId> lockIds;

    StationLocksWithoutVehicle(List<LockId> lockIds) {
        this.lockIds = new ArrayList<>(lockIds);
    }

    public List<LockId> getLockIds() {
        return lockIds;
    }
}
