package be.codingtim.velo.ride.domain.ride;


import org.locationtech.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "FreeRide")
@DiscriminatorValue(value = "FreeRide")
public class FreeRide extends Ride {

    @Column(
            columnDefinition = "GEOMETRY",
            name = "StartPoint"
    )
    private Point startPoint;
    @Column(
            columnDefinition = "GEOMETRY",
            name = "EndPoint"
    )
    private Point endPoint;

    FreeRide() {
        //default constructor
    }

    Point getStartPoint() {
        return startPoint;
    }

    Point getEndPoint() {
        return endPoint;
    }
}
