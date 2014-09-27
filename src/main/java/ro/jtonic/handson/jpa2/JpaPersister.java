package ro.jtonic.handson.jpa2;

import org.hibernate.engine.jdbc.NonContextualLobCreator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.jtonic.handson.jpa2.entities.FileContent;
import ro.jtonic.handson.jpa2.entities.Part;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public void saveFileContent(FileContent fileContent) {
        long size = 1024L;
        try {
            size = Files.size(Paths.get(URI.create("E:\\tmp\\handson-jpa2\\src\\test\\resources\\image.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            final FileInputStream is = new FileInputStream("E:\\tmp\\handson-jpa2\\src\\test\\resources\\image.png");
//            final Session session = getEm().unwrap(Session.class);
//            final Blob blob = session.getLobHelper().createBlob(new FileInputStream("E:\\tmp\\handson-jpa2\\src\\test\\resources\\image.png"), size);
            final Blob blob = NonContextualLobCreator.INSTANCE.createBlob(is, size);
            fileContent.setContent(blob);
            em.persist(fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
