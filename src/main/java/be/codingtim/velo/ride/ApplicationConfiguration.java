package be.codingtim.velo.ride;

import be.codingtim.velo.ride.database.configuration.DatabaseConfiguration;
import be.codingtim.velo.ride.domain.configuration.DomainConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@PropertySource("classpath:application.properties")
@Import(value = {
        DatabaseConfiguration.class,
        DomainConfiguration.class
})
@ComponentScan(basePackages = {
        "be.codingtim.velo.ride.facade",
        "be.codingtim.velo.ride.job"
})
@EnableScheduling
public class ApplicationConfiguration {

}
