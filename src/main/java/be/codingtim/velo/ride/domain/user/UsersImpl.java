package be.codingtim.velo.ride.domain.user;

import be.codingtim.velo.ride.domain.user.exception.UserNotFound;
import org.springframework.stereotype.Service;

@Service
class UsersImpl implements Users {
    private final UserRepository userRepository;

    UsersImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(UserId userId) {
        User user = userRepository.findById(userId.getValue());
        if (user == null) throw new UserNotFound(userId);
        return user;
    }
}
