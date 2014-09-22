package ro.jtonic.handson.jpa2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.Test;
import ro.jtonic.handson.jpa2.entities.EntityA;

@ContextConfiguration(classes = {ApplicationConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class JpaPersisterTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private JpaPersister jpaPersister;

    @Test
    public void testSaveEntityA() throws Exception {
        EntityA entityA = new EntityA("Mitu Pazargic");
        jpaPersister.saveEntityA(entityA);
    }
}