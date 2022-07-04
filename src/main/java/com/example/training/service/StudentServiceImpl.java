package com.example.training.service;

import com.example.training.dao.StudentDAO;
import com.example.training.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.transaction.SystemException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Inject
    StudentDAO studentDAO;

    public List<Student> getAll() {
        return studentDAO.findAll();
    }

    @Override
    public Student addNewStudent(String firstName, String lastName, String address) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAddress(address);
        student = studentDAO.saveAndFlush(student);
        return student;
    }

    @Override
    public Student updateStudent(String studentId, String firstName, String lastName, String address) throws SystemException {
        Student student = studentDAO.findOne(studentId);
        if (student == null) {
            throw new SystemException("Student Not Found");
        }

        if (!StringUtils.isEmpty(firstName)) {
            student.setFirstName(firstName);
        }

        if (!StringUtils.isEmpty(lastName)) {
            student.setLastName(lastName);
        }

        if (!StringUtils.isEmpty(address)) {
            student.setAddress(address);
        }

        return studentDAO.saveAndFlush(student);
    }
}
