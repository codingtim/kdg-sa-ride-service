package be.codingtim.velo.ride.database.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource() {
        //https://github.com/brettwooldridge/HikariCP
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlserver://localhost:1433;databaseName=velosa;user=sa;password=changeMe1;");
        return new HikariDataSource(config);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(@Autowired DataSource dataSource) {
        //https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#orm-hibernate
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.spatial.dialect.sqlserver.SqlServer2008SpatialDialect");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "validate");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan(
                "be.codingtim.velo.ride.domain.station",
                "be.codingtim.velo.ride.domain.user",
                "be.codingtim.velo.ride.domain.ride"
        );
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    //TODO exception translation
    //https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#orm-exception-translation
}
