package ro.jtonic.handson.jpa2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import ro.jtonic.handson.jpa2.entities.OrgEntityType;

import java.util.List;

/**
 * Antonel Pazargic (pazaran)
 * <p>Date: 04/09/2014
 * <p>Time: 08:46
 */
@ContextConfiguration(classes = {ApplicationConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class NativeSqlTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private JpaPersister persister;

    @Test
    public void testNativeSql() throws Exception {
        final String name = "ORG ENTITY TYPE 1";
        List<OrgEntityType> vos = persister.getOrgEntTypeByNativeSql(name);
        Assert.assertFalse(vos.isEmpty());
    }

}
