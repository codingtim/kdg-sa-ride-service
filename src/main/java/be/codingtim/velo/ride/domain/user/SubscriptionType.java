package be.codingtim.velo.ride.domain.user;

import java.time.Period;

public enum SubscriptionType {

    DAY(Period.ofDays(1)),
    WEEK(Period.ofWeeks(1)),
    YEAR(Period.ofYears(1));

    private Period validPeriod;

    SubscriptionType(Period validPeriod) {
        this.validPeriod = validPeriod;
    }

    public Period getValidPeriod() {
        return validPeriod;
    }
}
