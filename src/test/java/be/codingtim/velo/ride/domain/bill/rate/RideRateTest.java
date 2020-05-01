package be.codingtim.velo.ride.domain.bill.rate;

import be.codingtim.velo.ride.domain.bill.BillAmount;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RideRateTest {

    @Test
    void priceOf_allFree_thenFree() {
        RideRate rideRate = new RideRate(5, 10);
        BillAmount billAmount = rideRate.priceOf(Duration.ofMinutes(2));
        assertEquals(new BillAmount(0), billAmount);
    }

    @Test
    void priceOf_exactAllFree_thenFree() {
        RideRate rideRate = new RideRate(5, 10);
        BillAmount billAmount = rideRate.priceOf(Duration.ofMinutes(5));
        assertEquals(new BillAmount(0), billAmount);
    }

    @Test
    void priceOf_exactNotAllFree_thenNotFree() {
        RideRate rideRate = new RideRate(5, 10);
        BillAmount billAmount = rideRate.priceOf(Duration.parse("PT5M2.5S"));
        assertEquals(new BillAmount(10), billAmount);
    }

    @Test
    void priceOf_someNotFree_thenNotFree() {
        RideRate rideRate = new RideRate(5, 10);
        BillAmount billAmount = rideRate.priceOf(Duration.parse("PT7M"));
        assertEquals(new BillAmount(20), billAmount);
    }

    @Test
    void priceOf_aLotNotFree_thenNotFree() {
        RideRate rideRate = new RideRate(5, 10);
        BillAmount billAmount = rideRate.priceOf(Duration.parse("PT1H7M"));
        assertEquals(new BillAmount(620), billAmount);
    }
}