package ro.jtonic.handson.jpa2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;
import ro.jtonic.handson.jpa2.entities.FileContent;
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

}
