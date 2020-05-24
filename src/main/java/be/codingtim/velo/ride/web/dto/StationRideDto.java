package be.codingtim.velo.ride.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StationRideDto {
    @JsonProperty
    private Integer userId;
    @JsonProperty
    private Integer stationId;

    public Integer getUserId() {
        return userId;
    }

    public Integer getStationId() {
        return stationId;
    }
}
