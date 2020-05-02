package be.codingtim.velo.ride.domain.bill.rate;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Primary
class RideRatesCache implements RideRates {

    private static final Logger LOGGER = LoggerFactory.getLogger(RideRatesCache.class);

    private final RideRates rideRates;

    private final LoadingCache<RideRateParameters, RideRate> cache = Caffeine.newBuilder()
            .maximumSize(50)
            .refreshAfterWrite(5, TimeUnit.MINUTES)
            .build(this::getRateForCache);

    RideRatesCache(RideRates rideRates) {
        this.rideRates = rideRates;
    }

    @Override
    public RideRate getRateFor(RideRateParameters rideRateParameters) {
        LOGGER.info("Getting rate from the cache {}", rideRateParameters);
        return cache.get(rideRateParameters);
    }

    private RideRate getRateForCache(RideRateParameters rideRateParameters) {
        LOGGER.info("Getting rate for cache from the supplier {}", rideRateParameters);
        return rideRates.getRateFor(rideRateParameters);
    }

}
