package be.codingtim.velo.ride.job;

import be.codingtim.velo.ride.domain.openride.OpenRideDetection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class TimeBasedOpenRideDetectionJob {

    private OpenRideDetection openRideDetection;

    TimeBasedOpenRideDetectionJob(@Qualifier("timeBasedOpenRideDetection") OpenRideDetection openRideDetection) {
        this.openRideDetection = openRideDetection;
    }

    @Scheduled(cron = "0 0/5 * * * *")
    public void run() {
        openRideDetection.run();
    }
}
