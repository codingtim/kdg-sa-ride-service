package be.codingtim.velo.ride.domain.user;

import java.util.Objects;

public class ActiveSubscription {

    private final UserId userId;
    private final SubscriptionId subscriptionId;

    ActiveSubscription(UserId userId, SubscriptionId subscriptionId) {
        this.userId = userId;
        this.subscriptionId = subscriptionId;
    }

    public UserId getUserId() {
        return userId;
    }

    public SubscriptionId getSubscriptionId() {
        return subscriptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiveSubscription that = (ActiveSubscription) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(subscriptionId, that.subscriptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, subscriptionId);
    }
}
