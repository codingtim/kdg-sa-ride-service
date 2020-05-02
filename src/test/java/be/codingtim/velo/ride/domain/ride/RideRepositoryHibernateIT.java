package be.codingtim.velo.ride.domain.ride;

import be.codingtim.velo.ride.database.configuration.DatabaseConfiguration;
import be.codingtim.velo.ride.domain.location.GpsPoint;
import be.codingtim.velo.ride.domain.station.LockId;
import be.codingtim.velo.ride.domain.user.SubscriptionId;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RideRepositoryHibernateIT.TestConfiguration.class)
class RideRepositoryHibernateIT {

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
        assertEquals(Optional.of(new LockId(4069)), stationRide.getEndLockId());
        assertEquals(new VehicleId(510), stationRide.getVehicleId());
        assertEquals(new SubscriptionId(1449), stationRide.getSubscriptionId());
        assertEquals(GpsPoint.of(51.22210579324455, 4.40467399900234), stationRide.getStartPoint());
        assertEquals(GpsPoint.of(51.233335518955535, 4.413911263197972), stationRide.getEndPoint());
        assertEquals(Instant.parse("2019-09-09T17:59:18.000Z"), stationRide.getStartTime());
        assertEquals(Instant.parse("2019-09-09T18:06:24.000Z"), stationRide.getEndTime());
    }

    @Test
    void getFreeRide() {
        long freeRideId = 5801269;
        Ride ride = rideRepository.findById(freeRideId);
        assertNotNull(ride);
        assertEquals(new RideId(freeRideId), ride.getRideId());
        assertTrue(ride instanceof FreeRide);
        FreeRide freeRide = (FreeRide) ride;
        assertEquals(GpsPoint.of(51.2193609594494, 4.415196431967605), freeRide.getStartPoint());
        assertEquals(GpsPoint.of(51.21716749138686, 4.4137251742369195), freeRide.getEndPoint());
        assertEquals(Instant.parse("2019-09-12T07:37:34.000Z"), freeRide.getStartTime());
        assertEquals(Instant.parse("2019-09-12T07:38:40.000Z"), freeRide.getEndTime());
    }

    @Test
    void getActiveRide() {
        Optional<Ride> activeRideByUserId = rideRepository.findActiveRideByUserId(19534);
        assertTrue(activeRideByUserId.isPresent());
    }

    @Test
    void getActiveRideNotPresent() {
        Optional<Ride> activeRideByUserId = rideRepository.findActiveRideByUserId(1118);
        assertFalse(activeRideByUserId.isPresent());
    }

    @Configuration
    @Import(DatabaseConfiguration.class)
    @ComponentScan(
            basePackages = "be.codingtim.velo.ride.domain.ride",
            excludeFilters = @ComponentScan.Filter(Service.class)
    )
    static class TestConfiguration {

    }
}