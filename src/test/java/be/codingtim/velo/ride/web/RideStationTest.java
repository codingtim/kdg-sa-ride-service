package be.codingtim.velo.ride.web;

import be.codingtim.velo.ride.domain.station.Station;
import be.codingtim.velo.ride.domain.station.StationData.FullStation;
import be.codingtim.velo.ride.domain.station.StationData.HalfFullStation;
import be.codingtim.velo.ride.domain.station.StationId;
import be.codingtim.velo.ride.domain.station.Stations;
import be.codingtim.velo.ride.domain.station.exception.StationNotFound;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static be.codingtim.velo.ride.domain.station.StationData.UNKNOWN_STATION_ID;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RideStationTest {

    private final Stations stations = Mockito.mock(Stations.class);
    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(new StationController(stations))
            .setControllerAdvice(new ControllerExceptionAdvice())
            .build();

    @Nested
    class GetFreeLocks {

        @Test
        void ok() throws Exception {
            HalfFullStation halfFullStation = new HalfFullStation();
            Station station = halfFullStation.station;
            whenGetStation(station.getStationId()).thenReturn(station);
            callGetFreeLocks(station.getStationId())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$[0]").value(HalfFullStation.LOCK_ID_2.getValue()))
                    .andExpect(jsonPath("$[1]").doesNotExist())
            ;
        }

        @Test
        void stationHasNoVehicle() throws Exception {
            FullStation emptyStation = new FullStation();
            Station station = emptyStation.station;
            whenGetStation(station.getStationId()).thenReturn(station);
            callGetFreeLocks(station.getStationId())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isEmpty())
            ;
        }

        @Test
        void stationNotFound() throws Exception {
            StationId stationId = UNKNOWN_STATION_ID;
            whenGetStation(stationId).thenThrow(new StationNotFound(stationId));
            callGetFreeLocks(stationId)
                    .andExpect(status().isNotFound())
            ;
        }

        private ResultActions callGetFreeLocks(StationId stationId) throws Exception {
            return mockMvc.perform(get("/api/stations/{stationId}/free-locks", stationId.getValue())
                    .accept(APPLICATION_JSON)
            );
        }

        private OngoingStubbing<Station> whenGetStation(StationId stationId) {
            return when(stations.get(stationId));
        }

    }

}