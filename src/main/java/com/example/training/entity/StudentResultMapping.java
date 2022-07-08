package com.example.training.entity;

import com.example.base.AbstractPersistable;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "STUDENT_RESULT_MAPPING")
@AttributeOverride(name = "id", column = @Column(name = "RESULT_ID"))
public class StudentResultMapping extends AbstractPersistable implements Serializable {

    @Column(name="SCORE")
    private int score;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "studentSubjectMappingId",
            referencedColumnName = "STUDENT_SUBJECT_MAPPING_ID"
    )
    @JsonBackReference
    private StudentSubjectMapping studentSubjectMapping;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public StudentSubjectMapping getStudentSubjectMapping() {
        return studentSubjectMapping;
    }

    public void setStudentSubjectMapping(StudentSubjectMapping studentSubjectMapping) {
        this.studentSubjectMapping = studentSubjectMapping;
    }
}
