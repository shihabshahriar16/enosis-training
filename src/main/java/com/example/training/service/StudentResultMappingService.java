package com.example.training.service;

import com.example.training.entity.Student;
import com.example.training.entity.StudentResultMapping;
import com.example.training.entity.StudentSubjectMapping;

import javax.transaction.SystemException;
import java.util.List;

public interface StudentResultMappingService {
    List<StudentResultMapping> getAll();

    StudentResultMapping addNewStudentResultMapping(String studentId, String subjectId, int score) throws SystemException;

    StudentResultMapping updateStudentResultMapping(String studentSubjectMappingId, int score) throws SystemException;
}
