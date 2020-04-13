package be.codingtim.velo.ride.domain.station;

import be.codingtim.velo.ride.database.configuration.DatabaseConfiguration;
import be.codingtim.velo.ride.database.repository.TestDatabase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    private SessionFactory sessionFactory;

    @Autowired
    private StationRepository stationRepository;

    @Test
    void getStation() {
        Station station = stationRepository.findById(1);
        assertNotNull(station);
        List<Lock> locks = station.getLocks();
        assertEquals(locks.size(), 18);
    }

    @Configuration
    @Import(DatabaseConfiguration.class)
    @ComponentScan(basePackages = "be.codingtim.velo.ride.domain.station")
    static class TestConfiguration {

    }
}