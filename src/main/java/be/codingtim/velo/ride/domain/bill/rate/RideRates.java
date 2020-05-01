package be.codingtim.velo.ride.domain.bill.rate;

import be.codingtim.velo.ride.domain.bill.BillableRide;

public interface RideRates {
    RideRate getRateFor(BillableRide billableRide);
}
