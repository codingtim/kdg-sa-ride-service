package be.codingtim.velo.ride.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleLocationDto {
    @JsonProperty
    private Integer vehicleId;
    @JsonProperty
    private Double xCoord;
    @JsonProperty
    private Double yCoord;

    public VehicleLocationDto() {
        //constructor for jackson
    }

    public VehicleLocationDto(Integer vehicleId, Double xCoord, Double yCoord) {
        this.vehicleId = vehicleId;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

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
