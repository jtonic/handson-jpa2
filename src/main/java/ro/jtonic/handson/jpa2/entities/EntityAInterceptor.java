package ro.jtonic.handson.jpa2.entities;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;

/**
 * Antonel Pazargic (pazaran)
 * <p>Date: 04/09/2014
 * <p>Time: 08:46
 */
public class EntityAInterceptor extends EmptyInterceptor {

/*
    final Session hibernateSession = em.unwrap(Session.class);
    final LobHelper lobHelper = hibernateSession.getLobHelper();
    lobHelper.createBlob(ByteStreams.toByteArray(is))
*/
    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("onDelete");
        super.onDelete(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("onLoad");
        return super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("onSave");
        return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public Object getEntity(String entityName, Serializable id) {
        System.out.println("getEntity");
        return super.getEntity(entityName, id);
    }
}
