package ro.jtonic.handson.jpa2;

import com.google.common.io.ByteStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;
import ro.jtonic.handson.jpa2.entities.FileContent;
import ro.jtonic.handson.jpa2.entities.Part;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    public void testSaveAndGetFileContent() throws Exception {
        long size = Files.size(Paths.get("E:/tmp/handson-jpa2/src/test/resources/image.png"));
        FileContent fileContent = new FileContent("Antonel Pazargic");
        final FileInputStream is = new FileInputStream("E:/tmp/handson-jpa2/src/test/resources/image.png");
        fileContent.setContentFromInputStream(is, size);
        final long fileContentId = persister.saveFileContent1(fileContent);
        final FileContent savedFileContent = persister.getById(fileContentId);
        System.out.println("savedFileContent = " + savedFileContent);
        final InputStream content = savedFileContent.getContentFromInputStream();
        final long retrievedBytesLength = ByteStreams.toByteArray(content).length;
        Assert.assertNotNull(savedFileContent);
        Assert.assertEquals(retrievedBytesLength, size);
        System.out.println("content = " + content);
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
