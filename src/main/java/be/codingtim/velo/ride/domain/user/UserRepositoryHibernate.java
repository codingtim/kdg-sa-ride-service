package be.codingtim.velo.ride.domain.user;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
class UserRepositoryHibernate implements UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User findById(int userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from User where userId = :userId", User.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }
}
