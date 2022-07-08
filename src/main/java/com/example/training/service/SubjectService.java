package com.example.training.service;

import com.example.training.entity.Student;
import com.example.training.entity.Subject;

import javax.transaction.SystemException;
import java.util.List;

public interface SubjectService {

    Subject findById(String id);
    List<Subject> getAll();

    Subject addNewSubject(String subjectTitle);

    Subject updateSubject(String subjectId, String subjectTitle) throws SystemException;
}
