package be.codingtim.velo.ride.domain.bill.rate;

import be.codingtim.velo.ride.domain.bill.BillableRide;
import be.codingtim.velo.ride.domain.user.SubscriptionType;
import be.codingtim.velo.ride.domain.vehicle.VehicleType;
import be.kdg.sa.priceservice.PriceInfo;
import be.kdg.sa.priceservice.Proxy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class RideRatesProxyAdapter implements RideRates {

    private final Proxy proxy = new Proxy();

    @Override
    public RideRate getRateFor(BillableRide billableRide) {
        try {
            PriceInfo priceInfo = proxy.get(typeIdOf(billableRide.getSubscriptionType()), typeIdOf(billableRide.getVehicleType()));
            return new RideRate(priceInfo.getFreeMinutes(), priceInfo.getCentsPerMinute());
        } catch (IOException e) {
            //TODO error handling
            throw new RuntimeException(e);
        }
    }

    private int typeIdOf(VehicleType vehicleType) {
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
