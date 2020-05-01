package be.codingtim.velo.ride.domain.bill;

import be.codingtim.velo.ride.domain.bill.rate.RideRate;
import be.codingtim.velo.ride.domain.user.UserId;

import java.time.Duration;
import java.util.Objects;

public class Bill {

    private final UserId userId;
    private final BillAmount amount;

    Bill(BillableRide billableRide, RideRate rideRate) {
        this.userId = billableRide.getUserId();
        Duration rideDuration = billableRide.getRideDuration();
        amount = rideRate.priceOf(rideDuration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(userId, bill.userId) &&
                Objects.equals(amount, bill.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "userId=" + userId +
                ", amount=" + amount +
                '}';
    }
}
