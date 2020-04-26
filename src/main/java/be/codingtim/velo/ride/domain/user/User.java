package be.codingtim.velo.ride.domain.user;

import be.codingtim.velo.ride.domain.user.exception.UserHasNoActiveSubscription;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(
            columnDefinition = "SMALLINT",
            name = "UserId"
    )
    private int userId;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "userId"
    )
    private List<Subscription> subscriptions;

    User() {
        //default constuctor
    }

    User(int userId, List<Subscription> subscriptions) {
        this.userId = userId;
        this.subscriptions = new ArrayList<>(subscriptions);
    }

    List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public ActiveSubscription getActiveSubscription(LocalDate today) {
        return subscriptions.stream()
                .filter(subscription -> subscription.isValidOn(today))
                .map(Subscription::getSubscriptionId)
                .map(ActiveSubscription::new)
                .findFirst()
                .orElseThrow(UserHasNoActiveSubscription::new);
    }
}
