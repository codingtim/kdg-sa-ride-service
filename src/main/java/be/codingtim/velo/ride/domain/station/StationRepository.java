package be.codingtim.velo.ride.domain.station;

interface StationRepository {

    Station findById(int stationId);

    Station findByLockId(int lockId);
}
