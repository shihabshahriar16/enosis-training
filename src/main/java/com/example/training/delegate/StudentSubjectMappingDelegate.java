package com.example.training.delegate;

import com.example.training.entity.Student;
import com.example.training.entity.Subject;
import com.example.training.info.StudentInfo;
import com.example.training.info.StudentSubjectMappingInfo;

import javax.transaction.SystemException;
import java.io.IOException;
import java.util.List;

public interface StudentSubjectMappingDelegate {
    List<StudentSubjectMappingInfo> getAll() throws IOException;

    StudentSubjectMappingInfo addNewStudentSubjectMapping(String studentId, String subjectId) throws IOException, SystemException;

}
