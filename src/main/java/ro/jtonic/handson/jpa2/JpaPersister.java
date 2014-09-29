package ro.jtonic.handson.jpa2;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.jtonic.handson.jpa2.entities.FileContent;
import ro.jtonic.handson.jpa2.entities.OrgEntity;
import ro.jtonic.handson.jpa2.entities.Part;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public void saveFileContent(FileContent fileContent) throws IOException {
        long size = Files.size(Paths.get("E:/tmp/handson-jpa2/src/test/resources/image.png"));
        final FileInputStream is = new FileInputStream("E:/tmp/handson-jpa2/src/test/resources/image.png");
        fileContent.setContentFromInputStream(is, size);
        em.persist(fileContent);
    }

    @Transactional
    public long saveFileContent1(FileContent fileContent) {
        if (fileContent == null) {
            throw new IllegalArgumentException("FileContent cannot be null");
        }
        em.persist(fileContent);
        return fileContent.getId();
    }

    @Transactional(readOnly = true)
    public FileContent getById(Long id) throws SQLException, IOException {
        final FileContent saved = em.find(FileContent.class, id);
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

    @Transactional(readOnly = true)
    public OrgEntity getOrgEntityById(Long orgEntId) {
        OrgEntity orgEntity = getEm().find(OrgEntity.class, orgEntId);
        return orgEntity;
    }

}
