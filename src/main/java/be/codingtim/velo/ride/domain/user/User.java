package be.codingtim.velo.ride.domain.user;

import javax.persistence.*;
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

    List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    //https://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html#entity-inheritance-discriminator-formula
    //private List Rides;
}
