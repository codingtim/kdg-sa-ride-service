package be.codingtim.velo.ride.domain.vehicle;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
class VehicleRepositoryHibernate implements VehicleRepository {

    private final SessionFactory sessionFactory;

    public VehicleRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Vehicle findById(int vehicleId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select v from Vehicle v where v.vehicleId = :vehicleId", Vehicle.class)
                .setParameter("vehicleId", vehicleId)
                .getSingleResult();
    }
}
