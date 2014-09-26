package ro.jtonic.handson.jpa2.blob;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class BlobIntegrator implements Integrator {

    public BlobIntegrator() {
        System.out.println("Blob integration initialization.");
    }

    @Override
    public void integrate(Configuration configuration, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        BlobUserType blobUserType = new BlobUserType();
        configuration.registerTypeOverride(blobUserType);
    }

    @Override
    public void integrate(MetadataImplementor metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        BlobUserType blobUserType = new BlobUserType();
        metadata.getTypeResolver().registerTypeOverride(blobUserType);
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        //fixme find a solution for this
        // nothing so far
    }
}
