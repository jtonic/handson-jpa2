package ro.jtonic.handson.jpa2;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.jtonic.handson.jpa2.entities.FileContent;
import ro.jtonic.handson.jpa2.entities.OrgEntity;
import ro.jtonic.handson.jpa2.entities.OrgEntityType;
import ro.jtonic.handson.jpa2.entities.Part;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public long saveFileContent(FileContent fileContent) {
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
        OrgEntityType orgEntityType;
        try {
            orgEntityType = orgEntity.getOrgEntityType();
            if (orgEntityType != null) {
                orgEntityType.getId();
            }
        } catch (EntityNotFoundException e) {
            System.out.println("OrgEntityType NotFound for orgEntityId " + orgEntId);
            orgEntityType = null;
            orgEntity.setOrgEntityType(orgEntityType);
        }
        System.out.println("orgEntityType = " + orgEntityType);
        return orgEntity;
    }

    @Transactional
    public void mergeFileContent(FileContent fileContent) {
        if (fileContent == null) {
            throw new IllegalArgumentException("FileContent cannot be null");
        }
        em.merge(fileContent);
    }

    public void flush() {
        em.flush();
    }

    public void refresh(FileContent fileContent) {
        em.refresh(fileContent);
    }

    @Transactional
    public void saveAndMergeFileContent() throws IOException {
        final Path filePath = Paths.get(System.getProperty("user.dir"), "src/test/resources/image.png");
        long size = Files.size(filePath);
        FileContent fileContent = new FileContent("Antonel Pazargic");
        final String absoluteFileName = filePath.toAbsolutePath().toString();
        final FileInputStream is = new FileInputStream(absoluteFileName);
        fileContent.setContentFromInputStream(is, size);
        em.persist(fileContent);
        em.flush();
        em.refresh(fileContent);
//        fileContent.setContentFromInputStream(is, size);
        em.merge(fileContent);
    }
}
