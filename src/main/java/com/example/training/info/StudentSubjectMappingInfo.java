package com.example.training.info;

import com.example.training.entity.Student;
import com.example.training.entity.Subject;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;

public class StudentSubjectMappingInfo implements Serializable {
    private String id;
    private Student student;
    private Subject subject;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
