package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.database.configuration.DatabaseConfiguration;
import be.codingtim.velo.ride.domain.location.GpsPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StationRepositoryHibernateTest.TestConfiguration.class)
class StationRepositoryHibernateTest {

    @Autowired
    private StationRepository stationRepository;

    @Test
    void findStation() {
        Station station = stationRepository.findById(1);
        assertNotNull(station);
        assertEquals(station.getStationId(), new StationId(1));
        List<Lock> locks = station.getLocks();
        assertEquals(locks.size(), 18);
        assertEquals(locks.get(0).getLockId(), new LockId(1));
        assertEquals(GpsPoint.of(51.228085001372236, 4.408997273922094), station.getLocation());
    }

    @Test
    void findStationUnknown() {
        Station station = stationRepository.findById(-1);
        assertNull(station);
    }

    @Test
    void findStationByLockId() {
        Station station = stationRepository.findByLockId(2);
        assertNotNull(station);
        assertEquals(station.getStationId(), new StationId(1));
    }

    @Test
    void findStationByLockIdUnknown() {
        Station station = stationRepository.findByLockId(-1);
        assertNull(station);
    }

    @Configuration
    @Import(DatabaseConfiguration.class)
    @ComponentScan(basePackages = "be.codingtim.velo.ride.domain.station")
    static class TestConfiguration {

    }
}