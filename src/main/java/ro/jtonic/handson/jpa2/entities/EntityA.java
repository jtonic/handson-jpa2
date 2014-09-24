package ro.jtonic.handson.jpa2.entities;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by pazaran on 08/08/2014.
 */
@Entity
@Table(name = "ENTITY_A")
public class EntityA {

    @Id
    @SequenceGenerator(name = "ENTITY_A_ID_GENERATOR", sequenceName = "SEQ_ENTITY_A")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTITY_A_ID_GENERATOR")
    private Long id;

    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private Blob content;

    public EntityA(String name) {
        this.name = name;
    }

    public EntityA() {
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
