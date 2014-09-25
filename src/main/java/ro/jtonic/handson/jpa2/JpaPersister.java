package ro.jtonic.handson.jpa2;

import com.google.common.io.ByteStreams;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.jtonic.handson.jpa2.entities.FileContent;
import ro.jtonic.handson.jpa2.entities.Part;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.rowset.serial.SerialBlob;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by pazaran on 08/08/2014.
 */
@Component
public class JpaPersister {

    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    @Transactional
    public Long saveFileContent(FileContent fileContent) {
        try {
            try(InputStream is = new FileInputStream("E:\\tmp\\handson-jpa2\\src\\test\\resources\\image.png")) {
                try {
                    fileContent.setContent(new SerialBlob(ByteStreams.toByteArray(is)));
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        em.persist(fileContent);
        return fileContent.getId();
    }

    @Transactional(readOnly = true)
    public FileContent getById(Long id) throws SQLException {
        final FileContent saved = em.find(FileContent.class, id);
        final Blob content = saved.getContent();
        final int contentSize = (int) content.length();
        final byte[] is = content.getBytes(1, contentSize);
        return saved;
    }

    @Transactional
    public Long savePart(Part p) {
        getEm().persist(p);
        return p.getId();
    }

    @Transactional(readOnly = true)
    public Part getPartBy(Long id) {
        final Part part = getEm().find(Part.class, id);
        return part;
    }
}
