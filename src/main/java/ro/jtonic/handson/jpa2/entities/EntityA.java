package ro.jtonic.handson.jpa2.entities;

import javax.persistence.*;

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

}
