package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.station.exception.StationNotFound;
import org.junit.jupiter.api.Test;

import static be.codingtim.velo.ride.domain.station.StationData.*;
import static org.junit.jupiter.api.Assertions.*;

class StationsImplTest {

    private final StationRepositoryInMemory stationRepository = new StationRepositoryInMemory();

    private final Stations stations = new StationsImpl(stationRepository);

    @Test
    void getStation_whenFound_thenReturnStation() {
        Station station = stations.get(EmptyStation.STATION_ID);
        assertNotNull(station);
    }

    @Test
    void getStation_whenNotFound_thenException() {
        assertThrows(StationNotFound.class, () -> stations.get(UNKNOWN_STATION_ID));
    }

    @Test
    void getStationByLockId_whenFound_thenReturnStation() {
        Station station = stations.get(EmptyStation.LOCK_ID_2);
        assertEquals(EmptyStation.STATION_ID, station.getStationId());
    }

    @Test
    void getStationByLockId_whenNotFound_thenException() {
        assertThrows(StationNotFound.class, () -> stations.get(UNKNOWN_LOCK_ID));
    }

}