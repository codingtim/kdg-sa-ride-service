package be.codingtim.velo.ride.domain.user;

public class ActiveSubscription {

    private final SubscriptionId subscriptionId;

    ActiveSubscription(SubscriptionId subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public SubscriptionId getSubscriptionId() {
        return subscriptionId;
    }
}
