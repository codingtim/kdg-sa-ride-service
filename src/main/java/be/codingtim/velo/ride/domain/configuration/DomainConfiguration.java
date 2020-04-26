package be.codingtim.velo.ride.domain.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = "be.codingtim.velo.ride.domain"
)
public class DomainConfiguration {

}
