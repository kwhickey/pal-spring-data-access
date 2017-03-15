package io.pivotal.workshop.domain;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Document
@Entity
@XmlRootElement(name = "snippet")
public class Snippet {

    public Snippet() {
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String title;
    private String code;

    @Column(insertable = true, updatable = false)
    private Date created;

    private Date modified;

    public Snippet(String title, String code) {
        this.id = java.util.UUID.randomUUID().toString();
        this.title = title;
        this.code = code;
        this.created = new Date();
        this.modified = new Date();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    public void setTitle(String title) {
        this.title = title;
        this.modified = new Date();
    }

    public void setCode(String code) {
        this.code = code;
        this.modified = new Date();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @PrePersist
    void onCreate() {
        this.setCreated(new Date());
        this.setModified(new Date());
    }

    @PreUpdate
    void onUpdate() {
        setModified(new Date());
    }
}
