package ro.jtonic.handson.jpa2.entities;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by pazaran on 16/07/2014.
 */
@Entity
@Table(name = "TONY_ORG_ENTITY_TYPE")
@NamedNativeQueries(
        {@NamedNativeQuery(name = OrgEntityType.nativeQuery, query = "SELECT TOR_ID, NAME FROM TONY_ORG_ENTITY_TYPE WHERE NAME = :name", resultClass = OrgEntityType.class)}
)
public class OrgEntityType implements Serializable {

    public static final String nativeQuery = "nativeQuery";

    @Id
    @Column(name = "TOR_ID")
    private Long id;

    @Column(name = "CD_SP2")
    private String code;

    private String name;

    public OrgEntityType() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("code", code)
                .add("name", name)
                .toString();
    }
}
