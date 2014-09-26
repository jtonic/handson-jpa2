package ro.jtonic.handson.jpa2.blob;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.BinaryStream;
import org.hibernate.engine.jdbc.WrappedBlob;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.BlobTypeDescriptor;

import java.sql.Blob;
import java.sql.SQLException;

/**
* Antonel Pazargic (pazaran)
* <p>Date: 04/09/2014
* <p>Time: 08:46
*/
final class JavaBlobTypeDescriptor extends BlobTypeDescriptor {

    public static final JavaBlobTypeDescriptor INSTANCE = new JavaBlobTypeDescriptor();

    private JavaBlobTypeDescriptor() {
        System.out.println("Ctr of BlobTypeWrapperDescriptor");
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> X unwrap(Blob value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }

        if (BinaryStream.class.isAssignableFrom(type)) {
            return (X) new BinaryStreamWrapper(value);
        }

        try {
            final Blob blob = WrappedBlob.class.isInstance(value) ? ((WrappedBlob) value).getWrappedBlob() : value;
            final Blob rv = options.getLobCreator().createBlob(blob.getBinaryStream(), -1);
            return (X) rv;
        } catch (SQLException e) {
            throw new HibernateException(e);
        }
    }

}
