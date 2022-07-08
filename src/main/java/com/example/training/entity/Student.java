package com.example.training.entity;

import com.example.base.AbstractPersistable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STUDENT", indexes = @Index(columnList = "STUDENT_ID"))
@AttributeOverride(name = "id", column = @Column(name = "STUDENT_ID"))
public class Student extends AbstractPersistable implements Serializable {

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference(value = "student-ref")
    private List<StudentSubjectMapping> subjects = new ArrayList<StudentSubjectMapping>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<StudentSubjectMapping> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<StudentSubjectMapping> subjects) {
        this.subjects = subjects;
    }
}
