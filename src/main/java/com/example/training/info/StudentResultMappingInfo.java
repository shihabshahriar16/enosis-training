package com.example.training.info;

import com.example.training.entity.StudentSubjectMapping;

import java.io.Serializable;

public class StudentResultMappingInfo implements Serializable {
    private String id;
    private StudentSubjectMapping studentSubjectMapping;
    private int score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StudentSubjectMapping getStudentSubjectMapping() {
        return studentSubjectMapping;
    }

    public void setStudentSubjectMapping(StudentSubjectMapping studentSubjectMapping) {
        this.studentSubjectMapping = studentSubjectMapping;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
