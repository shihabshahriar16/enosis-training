package com.example.training.service;

import com.example.training.dao.StudentSubjectMappingDAO;
import com.example.training.entity.Student;
import com.example.training.entity.StudentSubjectMapping;
import com.example.training.entity.Subject;

import javax.transaction.SystemException;
import java.util.List;

public interface StudentSubjectMappingService {
    List<StudentSubjectMapping> getAll();

    StudentSubjectMapping addNewStudentSubjectMapping(String studentId, String subjectId) throws SystemException;

    List<StudentSubjectMapping>  findByStudentAndSubject(String studentId, String subjectId);
}
