package ro.jtonic.handson.jpa2.blob;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;

import java.sql.Blob;

/**
 * Antonel Pazargic (pazaran)
 * <p>Date: 04/09/2014
 * <p>Time: 08:46
 */
public class BlobUserType extends AbstractSingleColumnStandardBasicType<Blob> {

    public BlobUserType() {
        super(SqlBlobTypeDescriptor.INSTANCE, JavaBlobTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "BobUserType";
    }

}
