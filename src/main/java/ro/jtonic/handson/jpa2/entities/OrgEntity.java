package ro.jtonic.handson.jpa2.entities;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pazaran on 16/07/2014.
 */
@Entity
@Table(name = "TONY_ORG_ENTITY")
public class OrgEntity implements Serializable {

    @Id
    @Column(name = "ORG_ID")
    private Long id;

    @Column(name = "ORG_CD")
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TOR_ID", nullable = true)
    private OrgEntityType orgEntityType;

    public OrgEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OrgEntityType getOrgEntityType() {
        return orgEntityType;
    }

    public void setOrgEntityType(OrgEntityType orgEntityType) {
        this.orgEntityType = orgEntityType;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("code", code)
                .add("orgEntityType", orgEntityType)
                .toString();
    }
}
