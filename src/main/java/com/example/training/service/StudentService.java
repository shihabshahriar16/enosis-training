package com.example.training.service;

import com.example.training.entity.Student;

import javax.transaction.SystemException;
import java.util.List;

public interface StudentService {
    List<Student> getAll();

    Student addNewStudent(String firstName, String lastName, String address);

    Student updateStudent(String studentId, String firstName, String lastName, String address) throws SystemException;
}