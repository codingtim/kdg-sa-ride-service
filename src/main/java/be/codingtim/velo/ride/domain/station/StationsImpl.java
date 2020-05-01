package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.station.exception.StationNotFound;
import org.springframework.stereotype.Service;

@Service
class StationsImpl implements Stations {

    private final StationRepository stationRepository;

    StationsImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Station get(StationId stationId) {
        Station station = stationRepository.findById(stationId.getValue());
        if (station == null) throw new StationNotFound(stationId);
        return station;
    }

    @Override
    public Station get(LockId lockId) {
        Station station = stationRepository.findByLockId(lockId.getValue());
        if (station == null) throw new StationNotFound(lockId);
        return station;
    }
}
