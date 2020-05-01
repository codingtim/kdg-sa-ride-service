package be.codingtim.velo.ride.domain.ride;

import java.util.Optional;

interface RideRepository {

    Ride findById(long rideId);

    Optional<Ride> findActiveRideByUserId(int userId);

    void save(Ride ride);
}
