package be.codingtim.velo.ride.domain.bill;

import be.codingtim.velo.ride.domain.bill.gateway.BillGateway;
import be.codingtim.velo.ride.domain.bill.rate.RideRate;
import be.codingtim.velo.ride.domain.bill.rate.RideRates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class BillsImpl implements Bills {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillsImpl.class);

    private final RideRates rideRates;
    private final BillGateway billGateway;

    BillsImpl(RideRates rideRates, BillGateway billGateway) {
        this.rideRates = rideRates;
        this.billGateway = billGateway;
    }

    @Override
    public void createBillFor(BillableRide billableRide) {
        LOGGER.info("Creating bill for user {}", billableRide.getUserId());
        RideRate rideRate = rideRates.getRateFor(billableRide);
        Bill bill = new Bill(billableRide, rideRate);
        billGateway.handle(bill);
    }
}
