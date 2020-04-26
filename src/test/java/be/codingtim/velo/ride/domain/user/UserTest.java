package be.codingtim.velo.ride.domain.user;

import be.codingtim.velo.ride.domain.user.exception.UserHasNoActiveSubscription;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static be.codingtim.velo.ride.domain.user.UserData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    void getActiveSubscription_whenActive_thenReturn() {
        ActiveSubscription activeSubscription = USER_WITH_ACTIVE_SUBSCRIPTION.getActiveSubscription(LocalDate.now());
        assertEquals(activeSubscription.getSubscriptionId(), USER_WITH_ACTIVE_SUBSCRIPTION_SUBSCRIPTION_2.getSubscriptionId());
    }

    @Test
    void getActiveSubscription_whenNoActive_thenException() {
        assertThrows(UserHasNoActiveSubscription.class, () ->
                USER_WITHOUT_ACTIVE_SUBSCRIPTION.getActiveSubscription(LocalDate.now())
        );
    }
}