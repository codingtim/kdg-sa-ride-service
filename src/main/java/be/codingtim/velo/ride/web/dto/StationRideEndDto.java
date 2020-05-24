package be.codingtim.velo.ride.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StationRideEndDto {
    @JsonProperty
    private Integer userId;
    @JsonProperty
    private Integer lockId;

    public Integer getUserId() {
        return userId;
    }

    public Integer getLockId() {
        return lockId;
    }
}
