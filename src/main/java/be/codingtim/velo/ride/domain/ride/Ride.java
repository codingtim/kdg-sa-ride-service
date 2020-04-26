package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.domain.location.GpsPoint;
import be.codingtim.velo.ride.domain.user.ActiveSubscription;
import be.codingtim.velo.ride.domain.user.SubscriptionId;
import be.codingtim.velo.ride.domain.vehicle.Vehicle;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;
import org.hibernate.annotations.DiscriminatorFormula;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;

@Entity(name = "Ride")
@Table(name = "Rides")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula(
        "case when StartlockId is not null " +
                "then 'StationRide' " +
                "else 'FreeRide' " +
                "end "
)
public class Ride {

    @Id
    @Column(
            columnDefinition = "BIGINT",
            name = "RideId"
    )
    private long rideId;

    @Column(
            columnDefinition = "SMALLINT",
            name = "VehicleId"
    )
    private int vehicleId;

    @Column(
            columnDefinition = "TINYINT",
            name = "SubscriptionId"
    )
    private int subscriptionId;

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

    Ride() {
        //default constructor
    }

    public Ride(Vehicle vehicle, ActiveSubscription activeSubscription, GpsPoint startPoint) {
        vehicleId = vehicle.getVehicleId().getValue();
        vehicle.startRide();
        subscriptionId = activeSubscription.getSubscriptionId().getValue();
        this.startPoint = startPoint.getPoint();
    }

    RideId getRideId() {
        return new RideId(rideId);
    }

    VehicleId getVehicleId() {
        return new VehicleId(vehicleId);
    }

    SubscriptionId getSubscriptionId() {
        return new SubscriptionId(subscriptionId);
    }

    GpsPoint getStartPoint() {
        return GpsPoint.of(startPoint);
    }

    GpsPoint getEndPoint() {
        return GpsPoint.of(endPoint);
    }
}
