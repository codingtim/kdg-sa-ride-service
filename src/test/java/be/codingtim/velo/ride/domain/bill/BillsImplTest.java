package be.codingtim.velo.ride.domain.bill;

import be.codingtim.velo.ride.domain.bill.gateway.BillGateway;
import be.codingtim.velo.ride.domain.bill.rate.RideRate;
import be.codingtim.velo.ride.domain.bill.rate.RideRates;
import be.codingtim.velo.ride.domain.user.SubscriptionType;
import be.codingtim.velo.ride.domain.user.UserId;
import be.codingtim.velo.ride.domain.vehicle.VehicleType;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BillsImplTest {

    private final RideRate rideRate = new RideRate(2, 10);
    private final RideRates rideRates = rateParameters -> rideRate;
    private final StoreBillGateway billGateway = new StoreBillGateway();
    private final Bills bills = new BillsImpl(rideRates, billGateway);

    @Test
    void createBillFor() {
        DummyBillableRide billableRide = new DummyBillableRide();
        bills.createBillFor(billableRide);
        assertEquals(new Bill(billableRide, rideRate), billGateway.bill);
    }

    private static class DummyBillableRide implements  BillableRide {
        @Override
        public UserId getUserId() {
            return new UserId(1);
        }

        @Override
        public VehicleType getVehicleType() {
            return VehicleType.VELO_BIKE;
        }

        @Override
        public SubscriptionType getSubscriptionType() {
            return SubscriptionType.DAY;
        }

        @Override
        public Duration getRideDuration() {
            return Duration.ofMinutes(5);
        }
    }

    private static class StoreBillGateway implements BillGateway {
        private Bill bill;

        @Override
        public void handle(Bill bill) {
            this.bill = bill;
        }
    }
}