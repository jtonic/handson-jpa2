package ro.jtonic.handson.jpa2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pazaran on 08/08/2014.
 */
@Configuration
public class ComponentsConfig {

    @Bean
    public JpaPersister persistEntityA() {
        return new JpaPersister();
    }

}
