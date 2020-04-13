package be.codingtim.velo.ride.database.repository;

import be.codingtim.velo.ride.database.configuration.DatabaseConfiguration;
import be.codingtim.velo.ride.database.entities.Lock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    void getWithMapping() {
        try (Session session = sessionFactory.openSession()) {
            List<Lock> resultList = session
                    .createQuery("from Lock where id = 1", Lock.class)
                    .getResultList();
            assertEquals(resultList.size(), 1);
            assertEquals(resultList.get(0).getLockId(), 1);
            assertEquals(resultList.get(0).getStationLockId(), 1);
        }
    }

    @Configuration
    @Import(DatabaseConfiguration.class)
    static class TestConfiguration {

    }
}
