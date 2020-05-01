package be.codingtim.velo.ride.domain.bill.rate;

import be.codingtim.velo.ride.domain.bill.BillAmount;

import java.time.Duration;

public class RideRate {
    private final Integer freeMinutes;
    private final Integer centsPerMinute;

    public RideRate(Integer freeMinutes, Integer centsPerMinute) {
        this.freeMinutes = freeMinutes;
        this.centsPerMinute = centsPerMinute;
    }

    public BillAmount priceOf(Duration rideDuration) {
        Duration duration = rideDuration.minusMinutes(freeMinutes);
        if (duration.isNegative() || duration.isZero()) {
            return new BillAmount(0);
        } else {
            double minutesToBill = Math.ceil(1.0 * duration.toSeconds() / 60);
            return new BillAmount((long) minutesToBill * centsPerMinute);
        }
    }
}
