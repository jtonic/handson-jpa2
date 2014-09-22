package ro.jtonic.handson.jpa2;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.jtonic.handson.jpa2.entities.EntityA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by pazaran on 08/08/2014.
 */
@Component
public class JpaPersister {

    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    @Transactional
    public void saveEntityA(EntityA entityA) {
        em.persist(entityA);
    }

}
