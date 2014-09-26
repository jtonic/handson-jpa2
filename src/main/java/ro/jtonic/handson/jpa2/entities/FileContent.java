package ro.jtonic.handson.jpa2.entities;

import com.google.common.base.MoreObjects;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;

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

    public Blob getContent() {
        return content;
    }

    public void setContent(Blob content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }

}
