package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.database.configuration.DatabaseConfiguration;
import be.codingtim.velo.ride.domain.station.LockId;
import be.codingtim.velo.ride.domain.user.SubscriptionId;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RideRepositoryHibernateTest.TestConfiguration.class)
class RideRepositoryHibernateTest {

    @Autowired
    private RideRepository rideRepository;

    @Test
    void getStationRide() {
        long stationRideId = 5796188;
        Ride ride = rideRepository.findById(stationRideId);
        assertNotNull(ride);
        assertEquals(new RideId(stationRideId), ride.getRideId());
        assertTrue(ride instanceof StationRide);
        StationRide stationRide = (StationRide) ride;
        assertEquals(new LockId(2685), stationRide.getStartLockId());
        assertEquals(new LockId(4069), stationRide.getEndLockId());
        assertEquals(new VehicleId(510), stationRide.getVehicleId());
        assertEquals(new SubscriptionId(1449), stationRide.getSubscriptionId());
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING) , 4326);
        Point expectedStartPoint = geometryFactory.createPoint(new CoordinateXY(51.22210579324455, 4.40467399900234));
        assertEquals(expectedStartPoint, stationRide.getStartPoint());
        Point expectedEndPoint = geometryFactory.createPoint(new CoordinateXY(51.233335518955535, 4.413911263197972));
        assertEquals(expectedEndPoint, stationRide.getEndPoint());
    }

    @Test
    void getFreeRide() {
        long stationRideId = 5801269;
        Ride ride = rideRepository.findById(stationRideId);
        assertNotNull(ride);
        assertEquals(new RideId(stationRideId), ride.getRideId());
        assertTrue(ride instanceof FreeRide);
        FreeRide freeRide = (FreeRide) ride;
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING) , 4326);
        Point expectedStartPoint = geometryFactory.createPoint(new CoordinateXY(51.2193609594494, 4.415196431967605));
        assertEquals(expectedStartPoint, freeRide.getStartPoint());
        Point expectedEndPoint = geometryFactory.createPoint(new CoordinateXY(51.21716749138686, 4.4137251742369195));
        assertEquals(expectedEndPoint, freeRide.getEndPoint());
    }

    @Configuration
    @Import(DatabaseConfiguration.class)
    @ComponentScan(basePackages = "be.codingtim.velo.ride.domain.ride")
    static class TestConfiguration {

    }
}