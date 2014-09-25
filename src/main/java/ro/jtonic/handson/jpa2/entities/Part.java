package ro.jtonic.handson.jpa2.entities;

import javax.persistence.*;

/**
 * Antonel Pazargic (pazaran)
 * <p>Date: 04/09/2014
 * <p>Time: 08:46
 */
@Entity
@Table(name = "TONY_PART")
public class Part {

    @Id
    @SequenceGenerator(name = "PART_ID_GENERATOR", sequenceName = "SEQ_TONY_PART")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PART_ID_GENERATOR")
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @JoinColumn(name = "FILE_CONTENT_ID")
    private FileContent fileContent;

    public Part() {
    }

    public Part(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileContent getFileContent() {
        return fileContent;
    }

    public void setFileContent(FileContent fileContent) {
        this.fileContent = fileContent;
    }
}
