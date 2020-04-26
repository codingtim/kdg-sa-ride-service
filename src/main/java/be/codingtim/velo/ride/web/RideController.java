package be.codingtim.velo.ride.web;

import be.codingtim.velo.ride.domain.ride.RideId;
import be.codingtim.velo.ride.facade.RideFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    //TODO logging
    private static final Logger LOGGER = LoggerFactory.getLogger(RideController.class);

    private final RideFacade rideFacade;

    public RideController(RideFacade rideFacade) {
        this.rideFacade = rideFacade;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> startStationRide(@RequestBody StationRideDto dto) {
        RideId rideId = rideFacade.startRide(dto.getUserId(), dto.getStationId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/api/rides/" + rideId.getValue())
                .build();
    }

    //TODO exception translation
}
