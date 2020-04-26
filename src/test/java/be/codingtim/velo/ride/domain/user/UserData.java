package be.codingtim.velo.ride.domain.user;

import java.time.LocalDate;
import java.util.List;

import static be.codingtim.velo.ride.domain.user.SubscriptionType.WEEK;

public final class UserData {
    private static final LocalDate TODAY = LocalDate.now();

    public static final Subscription USER_WITH_ACTIVE_SUBSCRIPTION_SUBSCRIPTION_1 = new Subscription(10, TODAY.minusDays(14), WEEK);
    public static final Subscription USER_WITH_ACTIVE_SUBSCRIPTION_SUBSCRIPTION_2 = new Subscription(11, TODAY.minusDays(2), WEEK);
    public static final User USER_WITH_ACTIVE_SUBSCRIPTION = new User(1, List.of(
            USER_WITH_ACTIVE_SUBSCRIPTION_SUBSCRIPTION_1,
            USER_WITH_ACTIVE_SUBSCRIPTION_SUBSCRIPTION_2
    ));

    public static final Subscription USER_WITHOUT_ACTIVE_SUBSCRIPTION_SUBSCRIPTION_1 = new Subscription(20, TODAY.minusDays(10), WEEK);
    public static final User USER_WITHOUT_ACTIVE_SUBSCRIPTION = new User(2, List.of(
            USER_WITHOUT_ACTIVE_SUBSCRIPTION_SUBSCRIPTION_1)
    );

    private UserData() {
        //private constructor for util class
    }
}
