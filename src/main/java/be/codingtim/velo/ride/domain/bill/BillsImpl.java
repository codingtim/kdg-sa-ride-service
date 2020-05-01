package be.codingtim.velo.ride.domain.bill;

import be.codingtim.velo.ride.domain.bill.gateway.BillGateway;
import be.codingtim.velo.ride.domain.bill.rate.RideRate;
import be.codingtim.velo.ride.domain.bill.rate.RideRates;
import org.springframework.stereotype.Service;

@Service
class BillsImpl implements Bills {

    private final RideRates rideRates;
    private final BillGateway billGateway;

    BillsImpl(RideRates rideRates, BillGateway billGateway) {
        this.rideRates = rideRates;
        this.billGateway = billGateway;
    }

    @Override
    public void createBillFor(BillableRide billableRide) {
        RideRate rideRate = rideRates.getRateFor(billableRide);
        Bill bill = new Bill(billableRide, rideRate);
        billGateway.handle(bill);
    }
}
