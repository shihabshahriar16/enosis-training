package com.example.training.info;

import com.example.training.entity.StudentSubjectMapping;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.List;

public class SubjectInfo implements Serializable {
    private String id;
    private String subjectTitle;
    private List<StudentSubjectMapping> students;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
