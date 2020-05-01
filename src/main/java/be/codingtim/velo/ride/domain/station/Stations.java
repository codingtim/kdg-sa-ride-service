package be.codingtim.velo.ride.domain.station;

public interface Stations {
    Station get(StationId stationId);

    Station get(LockId lockId);
}
