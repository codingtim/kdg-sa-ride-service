package be.codingtim.velo.ride.web;

import be.codingtim.velo.ride.domain.station.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final Stations stations;

    public StationController(Stations stations) {
        this.stations = stations;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{stationId}/free-locks", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Integer>> getFreeLocks(@PathVariable int stationId) {
        Station station = stations.get(new StationId(stationId));
        StationLocksWithoutVehicle stationLocksWithoutVehicle = station.getLocksWithoutVehicle();
        return ResponseEntity.ok()
                .body(stationLocksWithoutVehicle.getLockIds().stream().map(LockId::getValue).collect(Collectors.toList()));
    }

}
