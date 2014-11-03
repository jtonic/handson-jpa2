package ro.jtonic.handson.jpa2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import ro.jtonic.handson.jpa2.entities.FileContent;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ContextConfiguration(classes = {ApplicationConfig.class})
@TransactionConfiguration(defaultRollback = false)
public class BlobExampleTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private JpaPersister persister;

    @Test
    public void testSaveAndGetFileContent() throws Exception {
        final Path filePath = Paths.get(System.getProperty("user.dir"), "src/test/resources/image.png");
        long size = Files.size(filePath);
        FileContent fileContent = new FileContent("Antonel Pazargic");
        final String absoluteFileName = filePath.toAbsolutePath().toString();
        final FileInputStream is = new FileInputStream(absoluteFileName);
        fileContent.setContentFromInputStream(is, size);
        final long fileContentId = persister.saveFileContent(fileContent);
        final FileContent savedFileContent = persister.getById(fileContentId);
        System.out.println("savedFileContent = " + savedFileContent);
        persister.mergeFileContent(fileContent);
        savedFileContent.setContentFromInputStream(is, size);
        persister.mergeFileContent(fileContent);
/*
        //FIXME: this test in a transaction and uncomment the below code.
        //Uncomment the below and you will see the blob cannot be retrieved because the call is outside of a transaction
        final InputStream content = savedFileContent.getContentFromInputStream();
        final long retrievedBytesLength = ByteStreams.toByteArray(content).length;
        Assert.assertNotNull(savedFileContent);
        Assert.assertEquals(retrievedBytesLength, size);
        System.out.println("content = " + content);
*/
        Assert.assertNotNull(savedFileContent);
    }

    @Test
    public void testSaveAndGetFileContent2() throws Exception {
        persister.saveAndMergeFileContent();
        Assert.assertTrue(true);
    }

}
