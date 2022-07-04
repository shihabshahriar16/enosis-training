package com.example.base;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractPersistable implements Serializable, Persistable<String> {
    private static final long serialVersionUID = 2535090450811888936L;
    @Id
    @GeneratedValue(
            generator = "system-uuid"
    )
    @GenericGenerator(
            name = "system-uuid",
            strategy = "uuid2"
    )
    @Column(
            name = "ID"
    )
    private String id;

    public AbstractPersistable() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNew() {
        return null == this.getId();
    }
}