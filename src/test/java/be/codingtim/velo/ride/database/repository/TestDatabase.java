package be.codingtim.velo.ride.database.repository;

import be.codingtim.velo.ride.database.configuration.DatabaseConfiguration;
import be.codingtim.velo.ride.domain.station.Lock;
import be.codingtim.velo.ride.domain.station.Station;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestDatabase.TestConfiguration.class)
public class TestDatabase {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void getLock() {
        try (Session session = sessionFactory.openSession()) {
            List<Lock> resultList = session
                    .createQuery("from Lock where id = 1", Lock.class)
                    .getResultList();
            assertEquals(resultList.size(), 1);
            assertEquals(resultList.get(0).getLockId(), 1);
            assertEquals(resultList.get(0).getStationId(), 1);
            assertEquals(resultList.get(0).getStationLockId(), 1);
            assertEquals(resultList.get(0).getVehicleId(), 1856);
        }
    }
    @Test
    void getStation() {
        try (Session session = sessionFactory.openSession()) {
            Station station = session
                    .createQuery("from Station where id = 1", Station.class)
                    .getSingleResult();
            assertNotNull(station);
        }
    }

    @Configuration
    @Import(DatabaseConfiguration.class)
    static class TestConfiguration {

    }
}
