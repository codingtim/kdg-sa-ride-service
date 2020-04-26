package be.codingtim.velo.ride.domain.payment;

import be.codingtim.velo.ride.domain.user.SubscriptionType;
import be.codingtim.velo.ride.domain.vehicle.VehicleType;
import be.kdg.sa.priceservice.PriceInfo;
import be.kdg.sa.priceservice.Proxy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ProxyContractTest {

    private final Proxy proxy = new Proxy();

    @ParameterizedTest
    @CsvSource({
            "DAY, VELO_BIKE, true",
            "DAY, VELO_E_BIKE, true",
            "DAY, ROAMING_E_STEP, true",
            "DAY, ROAMING_SCOOTER, true",
            "WEEK, VELO_BIKE, true",
            "WEEK, VELO_E_BIKE, true",
            "WEEK, ROAMING_E_STEP, true",
            "WEEK, ROAMING_SCOOTER, true",
            "YEAR, VELO_BIKE, true",
            "YEAR, VELO_E_BIKE, true",
            "YEAR, ROAMING_E_STEP, true",
            "YEAR, ROAMING_SCOOTER, false",
    })
    void getPriceInfo(SubscriptionType subscriptionType, VehicleType vehicleType, boolean success) {
        try {
            PriceInfo priceInfo = proxy.get(typeIdOf(subscriptionType), typeIfOf(vehicleType));
            assertNotNull(priceInfo);

            Integer freeMinutes = priceInfo.getFreeMinutes();
            assertNotNull(freeMinutes);
            assertTrue(freeMinutes >= 0);

            Integer centsPerMinute = priceInfo.getCentsPerMinute();
            assertNotNull(centsPerMinute);
            assertTrue(centsPerMinute > 0);
            if (!success) fail("Expected fail but succeeded with subscription " + subscriptionType + " and vehicle " + vehicleType);
        } catch (IOException e) {
            if (success) fail("Expected success but error with subscription " + subscriptionType + " and vehicle " + vehicleType, e);
        }
    }

    private int typeIfOf(VehicleType vehicleType) {
        switch (vehicleType) {
            case VELO_BIKE:
                return 0;
            case VELO_E_BIKE:
                return 1;
            case ROAMING_E_STEP:
                return 2;
            case ROAMING_SCOOTER:
                return 3;
        }
        throw new IllegalArgumentException("Invalid vehicle type " + vehicleType);
    }

    private int typeIdOf(SubscriptionType subscriptionType) {
        switch (subscriptionType) {
            case DAY:
                return 0;
            case WEEK:
                return 1;
            case YEAR:
                return 2;
        }
        throw new IllegalArgumentException("Invalid subscription type " + subscriptionType);
    }


}
