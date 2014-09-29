package ro.jtonic.handson.jpa2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;
import ro.jtonic.handson.jpa2.entities.FileContent;
import ro.jtonic.handson.jpa2.entities.OrgEntity;
import ro.jtonic.handson.jpa2.entities.Part;

@ContextConfiguration(classes = {ApplicationConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class JpaPersisterTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private JpaPersister persister;

    @Test
    public void testSaveFileContent() throws Exception {
        FileContent fileContent = new FileContent("Antonel Pazargic");
        persister.saveFileContent(fileContent);
    }

    @Test
    @Transactional
    public void testSaveAndRetrievePart() throws Exception {
        persister.savePart(new Part("Part 2"));
    }

    @Test
    @Transactional
    public void testSaveAndRetrievePartWithContent() throws Exception {
        final Part p = new Part("Part 2");
        final FileContent fc = new FileContent("Filecontent 1");
        //fixme continue from here with blob and input stream support in JPA2 and Hibernate
        // fc.setContent();
        p.setFileContent(fc);
        persister.savePart(p);
    }

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
