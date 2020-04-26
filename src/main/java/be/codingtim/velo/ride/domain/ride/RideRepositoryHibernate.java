package be.codingtim.velo.ride.domain.ride;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
                .createQuery("from Ride where rideId = :rideId", Ride.class)
                .setParameter("rideId", rideId)
                .getSingleResult();
    }

    @Override
    public void save(Ride ride) {
        sessionFactory.getCurrentSession().save(ride);
    }
}
