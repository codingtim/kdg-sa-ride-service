package be.codingtim.velo.ride.domain.user;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @Id
    @Column(
            columnDefinition = "SMALLINT",
            name = "SubscriptionId"
    )
    private int subscriptionId;

    @Column(
            columnDefinition = "TINYINT",
            name = "UserId"
    )
    private int userId;

    @Column(
            name = "ValidFrom"
    )
    private LocalDate validFrom;


    @Convert(converter = SubscriptionTypeAttributeConverter.class)
    @Column(
            columnDefinition = "TINYINT",
            name = "SubscriptionTypeId"
    )
    private SubscriptionType subscriptionType;

    Subscription() {
        //default constructor
    }

    LocalDate getValidFrom() {
        return validFrom;
    }

    SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }
}
