package ro.jtonic.handson.jpa2;

import org.hibernate.engine.jdbc.NonContextualLobCreator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.jtonic.handson.jpa2.entities.FileContent;
import ro.jtonic.handson.jpa2.entities.Part;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileInputStream;
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
        Long id = null;
        try (InputStream is = new FileInputStream("E:\\tmp\\handson-jpa2\\src\\test\\resources\\image.png")) {
//            final Blob blob = NonContextualLobCreator.INSTANCE.createBlob(is, ByteStreams.toByteArray(is).length);
            final Blob blob = NonContextualLobCreator.INSTANCE.createBlob(is, 1024);
            fileContent.setContent(blob);
//                    fileContent.setContent(new SerialBlob(ByteStreams.toByteArray(is)));
            em.persist(fileContent);
            id = fileContent.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
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
