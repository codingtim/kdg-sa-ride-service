package be.codingtim.velo.ride.domain.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static be.codingtim.velo.ride.domain.user.SubscriptionType.DAY;
import static be.codingtim.velo.ride.domain.user.SubscriptionType.WEEK;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubscriptionTest {

    private static final LocalDate TODAY = LocalDate.of(2020, 04, 26);
    private static final LocalDate TOMORROW = LocalDate.of(2020, 04, 27);
    private static final LocalDate TODAY_PLUS_6_DAYS = LocalDate.of(2020, 05, 2);
    private static final LocalDate TODAY_PLUS_WEEK = LocalDate.of(2020, 05, 3);
    private static final LocalDate TODAY_PLUS_WEEK_AND_DAY = LocalDate.of(2020, 05, 4);

    @Test
    void daySubscription_isValidToday() {
        assertTrue(subscriptionFromTodayWithType(DAY).isValidOn(TODAY));
    }

    @Test
    void daySubscription_isNotValidTomorrow() {
        assertFalse(subscriptionFromTodayWithType(DAY).isValidOn(TOMORROW));
    }

    @Test
    void weekSubscription_isValidToday() {
        assertTrue(subscriptionFromTodayWithType(WEEK).isValidOn(TODAY));
    }

    @Test
    void weekSubscription_isValidTomorrow() {
        assertTrue(subscriptionFromTodayWithType(WEEK).isValidOn(TOMORROW));
    }

    @Test
    void weekSubscription_isValidFor6Days() {
        assertTrue(subscriptionFromTodayWithType(WEEK).isValidOn(TODAY_PLUS_6_DAYS));
    }

    @Test
    void weekSubscription_isNotValidForAWeek() {
        assertFalse(subscriptionFromTodayWithType(WEEK).isValidOn(TODAY_PLUS_WEEK));
    }

    @Test
    void weekSubscription_isNotValidForAWeekPlusADay() {
        assertFalse(subscriptionFromTodayWithType(WEEK).isValidOn(TODAY_PLUS_WEEK_AND_DAY));
    }

    private Subscription subscriptionFromTodayWithType(SubscriptionType subscriptionType) {
        return new Subscription(1, TODAY, subscriptionType);
    }
}