package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.database.configuration.DatabaseConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StationRepositoryHibernateTest.TestConfiguration.class)
class StationRepositoryHibernateTest {

    @Autowired
    private StationRepository stationRepository;

    @Test
    void getStation() {
        Station station = stationRepository.findById(1);
        assertNotNull(station);
        assertEquals(station.getStationId(), new StationId(1));
        List<Lock> locks = station.getLocks();
        assertEquals(locks.size(), 18);
        assertEquals(locks.get(0).getLockId(), new LockId(1));
    }

    @Configuration
    @Import(DatabaseConfiguration.class)
    @ComponentScan(basePackages = "be.codingtim.velo.ride.domain.station")
    static class TestConfiguration {

    }
}