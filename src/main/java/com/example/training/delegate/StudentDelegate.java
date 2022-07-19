package com.example.training.delegate;

import com.example.training.entity.Student;
import com.example.training.info.StudentCgpaInfo;
import com.example.training.info.StudentInfo;
import com.example.training.info.StudentResultMappingInfo;

import javax.transaction.SystemException;
import java.io.IOException;
import java.util.List;

public interface StudentDelegate {
    List<Student> getAll() throws IOException;
    StudentInfo getById(String studentId) throws IOException;
    StudentInfo addNewStudent(String firstName, String lastName, String address) throws IOException;

    StudentInfo updateStudent(String studentId, String firstName, String lastName, String address) throws SystemException, IOException;

    void deleteStudent(String studentId);

    List<StudentResultMappingInfo> getGPA(String studentId) throws IOException;

    Double getCGPA(String studentId) throws IOException;

    List<StudentCgpaInfo> getCGPAList() throws IOException;
}
