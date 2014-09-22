package ro.jtonic.handson.jpa2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.jtonic.handson.jpa2.entities.EntityA;

/**
 * Created by pazaran on 08/08/2014.
 */
public class ApplicationStart {

    public static void main(String[] args) {
        ApplicationContext appCtx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        JpaPersister jpaPersister = appCtx.getBean(JpaPersister.class);
        jpaPersister.saveEntityA(new EntityA("Liviu Pazargic"));
    }

}
