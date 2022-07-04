package com.example.training.delegate;

import com.example.base.AbstractDelegate;
import com.example.training.info.StudentInfo;
import com.example.training.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.SystemException;
import java.io.IOException;
import java.util.List;

@Component
public class StudentDelegateImpl extends AbstractDelegate implements StudentDelegate{
    @Autowired
    StudentService studentService;

    @Override
    public List<StudentInfo> getAll() throws IOException {
        return convertToList(studentService.getAll(), StudentInfo.class);
    }

    @Override
    public StudentInfo addNewStudent(String firstName, String lastName, String address) throws IOException {
        return convert(studentService.addNewStudent(firstName, lastName, address), StudentInfo.class);
    }

    @Override
    public StudentInfo updateStudent(String studentId, String firstName, String lastName, String address) throws SystemException, IOException {
        return convert(studentService.updateStudent(studentId, firstName, lastName, address), StudentInfo.class);
    }
}
