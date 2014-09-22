package ro.jtonic.handson.jpa2;

import com.google.common.io.ByteStreams;
import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.jtonic.handson.jpa2.entities.EntityA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by pazaran on 08/08/2014.
 */
@Component
public class JpaPersister {

    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    @Transactional
    public void saveEntityA(EntityA entityA) {
        final Session hibernateSession = em.unwrap(Session.class);
        final LobHelper lobHelper = hibernateSession.getLobHelper();
        try {
            try(InputStream is = new FileInputStream("E:\\tmp\\handson-jpa2\\src\\test\\resources\\image.png")) {
                entityA.setContent(lobHelper.createBlob(ByteStreams.toByteArray(is)));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        em.persist(entityA);
    }

    public EntityManager getEm() {
        return em;
    }
}
