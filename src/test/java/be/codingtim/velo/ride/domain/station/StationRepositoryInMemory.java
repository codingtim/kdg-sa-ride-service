package be.codingtim.velo.ride.domain.station;

import java.util.ArrayList;
import java.util.List;

import static be.codingtim.velo.ride.domain.station.StationsData.*;

class StationRepositoryInMemory implements StationRepository {

    private final List<Station> stations = new ArrayList<>();

    public StationRepositoryInMemory() {
        stations.add(EMPTY_STATION);
        stations.add(HALF_FULL_STATION);
        stations.add(FULL_STATION);
    }

    @Override
    public Station findById(int stationId) {
        return stations.stream()
                .filter(station -> station.getStationId().getValue() == stationId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Station findByLockId(int lockId) {
        return stations.stream()
                .filter(station -> station.getLocks().stream()
                        .map(Lock::getLockId)
                        .map(LockId::getValue)
                        .anyMatch(id -> lockId == id))
                .findFirst()
                .orElse(null);
    }
}
