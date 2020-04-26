package be.codingtim.velo.ride;

import be.codingtim.velo.ride.database.configuration.DatabaseConfiguration;
import be.codingtim.velo.ride.domain.configuration.DomainConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        DatabaseConfiguration.class,
        DomainConfiguration.class
})
@ComponentScan(basePackages = {
        "be.codingtim.velo.ride.facade"
})
public class ApplicationConfiguration {

}
