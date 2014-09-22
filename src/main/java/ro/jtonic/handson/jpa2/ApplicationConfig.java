package ro.jtonic.handson.jpa2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by pazaran on 08/08/2014.
 */
@Configuration
@Import({PersistenceJPAConfig.class, ComponentsConfig.class})
public class ApplicationConfig {
}
