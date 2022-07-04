package com.example.training.delegate;

import com.example.training.entity.Student;
import com.example.training.info.StudentInfo;

import javax.transaction.SystemException;
import java.io.IOException;
import java.util.List;

public interface StudentDelegate {
    List<StudentInfo> getAll() throws IOException;

    StudentInfo addNewStudent(String firstName, String lastName, String address) throws IOException;

    StudentInfo updateStudent(String studentId, String firstName, String lastName, String address) throws SystemException, IOException;
}
