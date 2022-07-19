package com.example.training.info;

import com.example.training.entity.StudentSubjectMapping;

import java.io.Serializable;

public class StudentResultMappingInfo implements Serializable {
    private String id;
    private StudentSubjectMapping studentSubjectMapping;
    private int score;
    private double grade;

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

    public double getGrade() {
        return grade;
    }

    public void setGrade() {
        if(this.score>=80) this.grade=4.0;
        else if(this.score>=75) this.grade=3.75;
        else if(this.score>=70) this.grade=3.5;
        else if(this.score>=65) this.grade=3.25;
        else if(this.score>=60) this.grade=3;
        else if(this.score>=55) this.grade=2.75;
        else if(this.score>=50) this.grade=2.5;
        else if(this.score>=45) this.grade=2.25;
        else if(this.score>=40) this.grade=2;
        else this.grade=0;
    }
}
