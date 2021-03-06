package be.codingtim.velo.ride.web;

import be.codingtim.velo.ride.domain.location.NearestVehicle;
import be.codingtim.velo.ride.domain.location.VehicleLocation;
import be.codingtim.velo.ride.domain.point.GpsPoint;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;
import be.codingtim.velo.ride.domain.vehicle.VehicleType;
import be.codingtim.velo.ride.web.dto.NearestVehicleQueryDto;
import be.codingtim.velo.ride.web.dto.VehicleLocationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.Instant;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleLocation vehicleLocation;
    private final Clock clock;

    public VehicleController(VehicleLocation vehicleLocation, Clock clock) {
        this.vehicleLocation = vehicleLocation;
        this.clock = clock;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/location", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> vehicleLocation(@RequestBody VehicleLocationDto dto) {
        vehicleLocation.heartbeat(Instant.now(clock), new VehicleId(dto.getVehicleId()), GpsPoint.of(dto.getXCoord(), dto.getYCoord()));
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/nearest", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleLocationDto> findNearest(@RequestBody NearestVehicleQueryDto dto) {
        return vehicleLocation.nearestVehicleTo(GpsPoint.of(dto.getXCoord(), dto.getYCoord()), VehicleType.valueOf(dto.getVehicleType()))
                .map(this::toResponse)
                .orElseGet(this::notFound);
    }

    private ResponseEntity<VehicleLocationDto> notFound() {
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<VehicleLocationDto> toResponse(NearestVehicle nearestVehicle) {
        return ResponseEntity.ok(new VehicleLocationDto(
                nearestVehicle.getVehicleId().getValue(),
                nearestVehicle.getLocation().getX(),
                nearestVehicle.getLocation().getY()
        ));
    }

}
