package com.example.training.entity;

import com.example.base.AbstractPersistable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="SUBJECT", indexes = @Index(columnList = "SUBJECT_ID"))
@AttributeOverride(name = "id", column = @Column(name = "SUBJECT_ID"))
public class Subject extends AbstractPersistable implements Serializable {

    @Column(name="SUBJECT_TITLE")
    private String subjectTitle;

    @OneToMany(
            mappedBy = "subject",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference(value = "subject-ref")
    private List<StudentSubjectMapping> students = new ArrayList<StudentSubjectMapping>();

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public List<StudentSubjectMapping> getStudents() {
        return students;
    }

    public void setStudents(List<StudentSubjectMapping> students) {
        this.students = students;
    }


}
