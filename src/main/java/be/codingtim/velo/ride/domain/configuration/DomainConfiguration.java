package be.codingtim.velo.ride.domain.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
@ComponentScan(
        basePackages = "be.codingtim.velo.ride.domain"
)
public class DomainConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
