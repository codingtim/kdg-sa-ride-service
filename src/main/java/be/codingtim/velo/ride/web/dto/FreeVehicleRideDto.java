package be.codingtim.velo.ride.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FreeVehicleRideDto {
    @JsonProperty
    private Integer userId;
    @JsonProperty
    private Integer vehicleId;

    public Integer getUserId() {
        return userId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }
}
