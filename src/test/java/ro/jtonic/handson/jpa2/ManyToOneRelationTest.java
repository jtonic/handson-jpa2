package ro.jtonic.handson.jpa2;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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

    @Test
    public void testGetOrgEntityByIdWithCorrectOrgEntTypeRelationAndParents() {
        OrgEntity orgEntity = persister.getOrgEntityById(7L);
        System.out.println("===============================================================");
        System.out.println("orgEntity = " + orgEntity);
        System.out.println("===============================================================");
        Assert.assertNotNull(orgEntity);
    }

    @DataProvider(name = "ORG_ENTITY_IDS_DP")
    public Object[][] getOrgEntityIds() {
        Long[][] data = new Long[5][1];
        data[0] = new Long[]{1L};
        data[1] = new Long[]{2L};
        data[2] = new Long[]{3L};
        data[3] = new Long[]{6L};
        data[4] = new Long[]{7L};
        return data;
    }

    @Test(dataProvider = "ORG_ENTITY_IDS_DP")
    public void testGetOrgEntityByIdWithParentAndChildren(Long id) {
        OrgEntity orgEntity = persister.getOrgEntityById(id);
        System.out.println("===============================================================");
        System.out.println("orgEntity = " + orgEntity);
        System.out.println("===============================================================");
        Assert.assertNotNull(orgEntity);

        if (id == 7) {
            MatcherAssert.assertThat(orgEntity.getChildOrgEntities(), Matchers.hasSize(0));
            final OrgEntity parent1 = orgEntity.getParent();
            Assert.assertNotNull(parent1);
            final OrgEntity parent2 = parent1.getParent();
            Assert.assertNotNull(parent2);
            final OrgEntity parent3 = parent2.getParent();
            Assert.assertNotNull(parent3);
            final OrgEntity parent4 = parent3.getParent();
            Assert.assertNull(parent4);
        }
    }
}
