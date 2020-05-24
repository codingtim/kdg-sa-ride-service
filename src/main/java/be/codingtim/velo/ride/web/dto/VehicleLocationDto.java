package be.codingtim.velo.ride.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleLocationDto {
    @JsonProperty
    private Integer vehicleId;
    @JsonProperty
    private Double xCoord;
    @JsonProperty
    private Double yCoord;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public Double getXCoord() {
        return xCoord;
    }

    public Double getYCoord() {
        return yCoord;
    }
}
