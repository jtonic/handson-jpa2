package ro.jtonic.handson.jpa2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import ro.jtonic.handson.jpa2.entities.OrgEntity;

/**
 * Antonel Pazargic (pazaran)
 * <p>Date: 04/09/2014
 * <p>Time: 08:46
 */
@ContextConfiguration(classes = {ApplicationConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class ManyToOneRelationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private JpaPersister persister;

    @Test
        /*This tests a relation between entities w/o a DB FK but with correct value for "FK" and PK*/
        public void testGetOrgEntityByIdWithCorrectOrgEntTypeRelation() {
            OrgEntity orgEntity = persister.getOrgEntityById(1L);
            System.out.println("===============================================================");
            System.out.println("orgEntity = " + orgEntity);
            System.out.println("===============================================================");
            Assert.assertNotNull(orgEntity);
        }

        @Test
        /*This tests a relation between entities w/o a DB FK but with null "FK"*/
        public void testGetOrgEntityByIdWithNullForOrgEntTypeFkValue() {
            OrgEntity orgEntity = persister.getOrgEntityById(4L);
            System.out.println("===============================================================");
            System.out.println("orgEntity = " + orgEntity);
            System.out.println("===============================================================");
            Assert.assertNotNull(orgEntity);
        }

        @Test
        /*This tests a relation between entities w/o a DB FK but with incorrect value for "FK". There is no entry in ORG_ENT_TYPE table for the value of the "FK"*/
        public void testGetOrgEntityByIdWithIncorrectOrgEntTypeRelation() {
            OrgEntity orgEntity = persister.getOrgEntityById(6L);
            System.out.println("===============================================================");
            System.out.println("orgEntity = " + orgEntity);
            System.out.println("===============================================================");
            Assert.assertNotNull(orgEntity);
        }

}
