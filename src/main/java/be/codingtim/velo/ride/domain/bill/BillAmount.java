package be.codingtim.velo.ride.domain.bill;

import java.util.Objects;

public class BillAmount {
    private final long cents;

    public BillAmount(long cents) {
        this.cents = cents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillAmount that = (BillAmount) o;
        return cents == that.cents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cents);
    }

    @Override
    public String toString() {
        return "BillAmount{" +
                cents + " cents" +
                '}';
    }
}
