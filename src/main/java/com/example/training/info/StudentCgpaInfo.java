package com.example.training.info;

import java.io.Serializable;

public class StudentCgpaInfo implements Serializable {
    private String id;
    private double grade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
