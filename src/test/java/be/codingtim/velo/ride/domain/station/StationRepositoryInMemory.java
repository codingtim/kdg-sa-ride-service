package be.codingtim.velo.ride.domain.station;

import java.util.ArrayList;
import java.util.List;

import static be.codingtim.velo.ride.domain.station.StationData.*;

class StationRepositoryInMemory implements StationRepository {

    private final List<Station> stations = new ArrayList<>();

    public StationRepositoryInMemory() {
        stations.add(new EmptyStation().station);
        stations.add(new HalfFullStation().station);
        stations.add(new FullStation().station);
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
