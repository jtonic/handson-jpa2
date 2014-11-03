package ro.jtonic.handson.jpa2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import ro.jtonic.handson.jpa2.entities.FileContent;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
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
        Assert.assertNotNull(savedFileContent);
    }

    @Test
    public void testPersistAndMergeMediumFileContent() throws Exception {
        final String fileName1 = "src/test/resources/image.png";
        final Path filePath1 = Paths.get(System.getProperty("user.dir"), fileName1);
        long size1 = Files.size(filePath1);
        System.out.println("fileName1 = " + fileName1);
        System.out.println("size1 = " + size1);
        final String absoluteFileName1 = filePath1.toAbsolutePath().toString();
        final InputStream is1 = new FileInputStream(absoluteFileName1);

        final String fileName2 = "src/test/resources/DEBUG.mp4";
        final Path filePath2 = Paths.get(System.getProperty("user.dir"), fileName2);
        long size2 = Files.size(filePath2);
        System.out.println("fileName2 = " + fileName2);
        System.out.println("size2 = " + size2);
        final String absoluteFileName2 = filePath2.toAbsolutePath().toString();
        final InputStream is2 = new FileInputStream(absoluteFileName2);

        persister.saveAndMergeFileContent(is1, size1, is2, size2);
        Assert.assertTrue(true);
    }

    @Test
    public void testPersistAndMergeSmallFileContent() throws Exception {
        final byte[] bytes1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".getBytes();
        long size1 = bytes1.length;
        final InputStream is1 = new ByteArrayInputStream(bytes1);

        final byte[] bytes2 = "fffffffffffffffffffffffffffffffffffffffffffffffffff".getBytes();
        long size2 = bytes2.length;
        final InputStream is2 = new ByteArrayInputStream(bytes2);

        persister.saveAndMergeFileContent(is1, size1, is2, size2);
        Assert.assertTrue(true);
    }

}
