package ro.jtonic.handson.jpa2.entities;

import com.google.common.base.MoreObjects;
import org.hibernate.engine.jdbc.NonContextualLobCreator;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by pazaran on 08/08/2014.
 */
@Entity
@Table(name = "TONY_FILE_CONTENT")
public class FileContent {

    @Id
    @SequenceGenerator(name = "FILE_CONTENT_ID_GENERATOR", sequenceName = "SEQ_TONY_FILE_CONTENT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_CONTENT_ID_GENERATOR")
    private Long id;

    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private Blob content;

    public FileContent(String name) {
        this.name = name;
    }

    public FileContent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    Blob getContent() {
        return content;
    }

    void setContent(Blob content) {
        this.content = content;
    }

    @Transient
    public InputStream getContentFromInputStream() throws SQLException {
        if(this.content == null)
            return new ByteArrayInputStream(new byte[0]);
        return this.content.getBinaryStream();
    }

    @Transient
    public void setContentFromInputStream(InputStream is, long size) {
        if(is != null) {
            this.content = NonContextualLobCreator.INSTANCE.createBlob(is, size);
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }

}
