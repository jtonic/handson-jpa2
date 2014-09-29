package ro.jtonic.handson.jpa2;

import org.hibernate.engine.jdbc.NonContextualLobCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;
import ro.jtonic.handson.jpa2.entities.FileContent;
import ro.jtonic.handson.jpa2.entities.Part;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;

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
    public void testSaveFileContent1() throws Exception {
        long size = Files.size(Paths.get("E:/tmp/handson-jpa2/src/test/resources/image.png"));
        FileContent fileContent = new FileContent("Antonel Pazargic");
        final FileInputStream is = new FileInputStream("E:/tmp/handson-jpa2/src/test/resources/image.png");
        final Blob blob = NonContextualLobCreator.INSTANCE.createBlob(is, size);
        fileContent.setContent(blob);
        persister.saveFileContent1(fileContent);
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
