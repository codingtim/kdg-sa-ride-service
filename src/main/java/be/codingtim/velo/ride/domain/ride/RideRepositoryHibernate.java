package be.codingtim.velo.ride.domain.ride;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
class RideRepositoryHibernate implements RideRepository {

    private final SessionFactory sessionFactory;

    public RideRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Ride findById(long rideId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select r from Ride r where r.rideId = :rideId", Ride.class)
                .setParameter("rideId", rideId)
                .getSingleResult();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<Ride> findActiveRideByUserId(int userId) {
        List<Ride> rides = sessionFactory.getCurrentSession()
                .createQuery("select r from Ride r where r.endTime is NULL and r.subscriptionId in (select subscriptionId from Subscription where userId = :userId)", Ride.class)
                .setParameter("userId", userId)
                .getResultList();
        return rides.stream().findFirst();
    }

    @Override
    public void save(Ride ride) {
        sessionFactory.getCurrentSession().save(ride);
    }
}
