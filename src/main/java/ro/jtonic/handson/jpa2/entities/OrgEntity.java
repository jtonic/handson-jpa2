package ro.jtonic.handson.jpa2.entities;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOR_ID", nullable = true)
    private OrgEntityType orgEntityType;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORG_ID_PARENT", nullable = true)
    private Collection<OrgEntity> childOrgEntities;

    @ManyToOne
    @JoinColumn(name = "ORG_ID_PARENT", nullable = true)
//    @JoinTable(name = "TONY_ORG_ENTITY", joinColumns = { @JoinColumn(name = "ORG_ID_PARENT") }, inverseJoinColumns = { @JoinColumn(name = "ORG_ID") })
    private OrgEntity parent;

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

    public Collection<OrgEntity> getChildOrgEntities() {
        return childOrgEntities;
    }

    public void setChildOrgEntities(Collection<OrgEntity> parent) {
        this.childOrgEntities = parent;
    }

    public OrgEntity getParent() {
        return parent;
    }

    public void setParent(OrgEntity parent) {
        this.parent = parent;
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
