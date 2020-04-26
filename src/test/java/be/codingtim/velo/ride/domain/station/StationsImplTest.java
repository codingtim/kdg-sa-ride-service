package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.domain.station.exception.StationNotFound;
import org.junit.jupiter.api.Test;

import static be.codingtim.velo.ride.domain.station.StationsData.EMPTY_STATION;
import static be.codingtim.velo.ride.domain.station.StationsData.UNKNOWN_STATION_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StationsImplTest {

    private final StationRepositoryInMemory stationRepository = new StationRepositoryInMemory();

    private final Stations stations = new StationsImpl(stationRepository);

    @Test
    void getStation_whenFound_thenReturnStation() {
        Station station = stations.get(EMPTY_STATION.getStationId());
        assertEquals(EMPTY_STATION, station);
    }

    @Test
    void getStation_whenNotFound_thenException() {
        assertThrows(StationNotFound.class, () -> stations.get(UNKNOWN_STATION_ID));
    }

}