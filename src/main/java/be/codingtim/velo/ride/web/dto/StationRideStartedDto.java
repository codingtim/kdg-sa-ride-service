package be.codingtim.velo.ride.web.dto;

import be.codingtim.velo.ride.domain.ride.StationRideStarted;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StationRideStartedDto {
    @JsonProperty
    private Integer lockId;

    public StationRideStartedDto(StationRideStarted stationRide) {
        this.lockId = stationRide.getStartLockId().getValue();
    }
}
