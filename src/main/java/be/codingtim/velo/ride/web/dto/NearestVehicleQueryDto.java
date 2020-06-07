package be.codingtim.velo.ride.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NearestVehicleQueryDto {
    @JsonProperty
    private String vehicleType;
    @JsonProperty
    private Double xCoord;
    @JsonProperty
    private Double yCoord;

    public String getVehicleType() {
        return vehicleType;
    }

    public Double getXCoord() {
        return xCoord;
    }

    public Double getYCoord() {
        return yCoord;
    }
}
