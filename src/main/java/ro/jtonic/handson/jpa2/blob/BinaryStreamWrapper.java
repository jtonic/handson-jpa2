package ro.jtonic.handson.jpa2.blob;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.BinaryStream;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Antonel Pazargic (pazaran)
 * <p>Date: 04/09/2014
 * <p>Time: 08:46
 */
public class BinaryStreamWrapper implements BinaryStream {

    private final Blob blob;

    public BinaryStreamWrapper(Blob blob) {
        this.blob = blob;
    }

    @Override
    public InputStream getInputStream() {
        try {
            return blob.getBinaryStream();
        } catch (SQLException e) {
            throw new HibernateException(e.getMessage(), e);
        }
    }

    @Override
    public byte[] getBytes() {
        // fixme
        return new byte[0];
    }

    @Override
    public long getLength() {
        // fixme
        return 0;
    }

    @Override
    public void release() {
        try {
            blob.free();
        } catch (SQLException e) {
            throw new HibernateException(e.getMessage(), e);
        }
    }
}
