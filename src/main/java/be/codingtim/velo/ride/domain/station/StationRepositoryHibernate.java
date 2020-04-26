package be.codingtim.velo.ride.domain.station;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
class StationRepositoryHibernate implements StationRepository {

    private final SessionFactory sessionFactory;

    public StationRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Station findById(int stationId) {
        List<Station> stations = sessionFactory.getCurrentSession()
                .createQuery("from Station where stationId = :stationId", Station.class)
                .setParameter("stationId", stationId)
                .getResultList();
        return stations.isEmpty() ? null : stations.get(0);
    }
}
