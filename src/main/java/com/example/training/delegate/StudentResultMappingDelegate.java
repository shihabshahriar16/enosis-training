package com.example.training.delegate;

import com.example.training.info.StudentResultMappingInfo;
import com.example.training.info.StudentSubjectMappingInfo;
import com.example.training.info.SubjectInfo;

import javax.transaction.SystemException;
import java.io.IOException;
import java.util.List;

public interface StudentResultMappingDelegate {
    List<StudentResultMappingInfo> getAll() throws IOException;

    StudentResultMappingInfo addNewStudentResultMapping(String studentId, String subjectId, int score) throws IOException, SystemException;

    StudentResultMappingInfo updateStudentResultMapping(String studentSubjectMappingId, int score) throws SystemException, IOException;
}
