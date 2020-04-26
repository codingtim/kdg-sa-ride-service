package be.codingtim.velo.ride.domain.ride;

interface RideRepository {

    Ride findById(long rideId);

    void save(Ride ride);
}
