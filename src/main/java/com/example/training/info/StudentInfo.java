package com.example.training.info;

import com.example.training.entity.StudentSubjectMapping;

import java.io.Serializable;
import java.util.List;

public class StudentInfo implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private List<StudentSubjectMapping> subjects;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
