package be.codingtim.velo.ride.domain.user;

interface UserRepository {

    User findById(int userId);
}
