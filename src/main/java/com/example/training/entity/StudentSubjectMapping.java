package com.example.training.entity;

import com.example.base.AbstractPersistable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "STUDENT_SUBJECT_MAPPING")
@AttributeOverride(name = "id", column = @Column(name = "STUDENT_SUBJECT_MAPPING_ID"))
public class StudentSubjectMapping extends AbstractPersistable implements Serializable {

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "studentId",
            referencedColumnName = "STUDENT_ID"
    )
    @JsonBackReference(value = "student-ref")
    private Student student;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "subjectId",
            referencedColumnName = "SUBJECT_ID"
    )
    @JsonBackReference(value = "subject-ref")
    private Subject subject;

    @OneToOne(
            mappedBy = "studentSubjectMapping",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private StudentResultMapping studentResultMapping;

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

    public StudentResultMapping getStudentResultMapping() {
        return studentResultMapping;
    }

    public void setStudentResultMapping(StudentResultMapping studentResultMapping) {
        this.studentResultMapping = studentResultMapping;
    }
}
