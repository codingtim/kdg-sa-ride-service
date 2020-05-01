package be.codingtim.velo.ride.web;

import be.codingtim.velo.ride.domain.exception.EntityNotFound;
import be.codingtim.velo.ride.domain.ride.exception.OnlyStationVehicleCanBeLockedAtStation;
import be.codingtim.velo.ride.domain.station.exception.StationHasNoAvailableVehicle;
import be.codingtim.velo.ride.domain.user.exception.UserHasNoActiveSubscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler
    public ResponseEntity<String> entityNotFound(EntityNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> stationHasNoAvailableVehicle(StationHasNoAvailableVehicle exception) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> userHasNoActiveSubscription(UserHasNoActiveSubscription exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> onlyStationVehicleCanBeLockedAtStation(OnlyStationVehicleCanBeLockedAtStation exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> illegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> illegalStationException(IllegalStateException exception) {
        LOGGER.error("Illegal state present in the application!", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
