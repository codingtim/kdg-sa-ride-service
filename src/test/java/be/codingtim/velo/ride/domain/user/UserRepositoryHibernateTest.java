package be.codingtim.velo.ride.domain.user;

import be.codingtim.velo.ride.database.configuration.DatabaseConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserRepositoryHibernateTest.TestConfiguration.class)
class UserRepositoryHibernateTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void getUser() {
        User user = userRepository.findById(1);
        assertNotNull(user);
        assertEquals(3, user.getSubscriptions().size());
        Subscription subscription = user.getSubscriptions().get(0);
        assertEquals(LocalDate.of(2015, 8, 2), subscription.getValidFrom());
        assertEquals(SubscriptionType.YEAR, subscription.getSubscriptionType());
    }

    @Configuration
    @Import(DatabaseConfiguration.class)
    @ComponentScan(basePackages = "be.codingtim.velo.ride.domain.user")
    static class TestConfiguration {

    }
}