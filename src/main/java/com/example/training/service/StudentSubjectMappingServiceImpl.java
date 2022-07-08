package com.example.training.service;

import com.example.training.dao.StudentDAO;
import com.example.training.dao.StudentSubjectMappingDAO;
import com.example.training.dao.SubjectDAO;
import com.example.training.entity.Student;
import com.example.training.entity.StudentSubjectMapping;
import com.example.training.entity.Subject;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentSubjectMappingServiceImpl implements StudentSubjectMappingService {

    @Inject
    StudentSubjectMappingDAO studentSubjectMappingDAO;
    @Inject
    StudentService studentService;
    @Inject
    SubjectService subjectService;

    public List<StudentSubjectMapping> getAll() {
        return studentSubjectMappingDAO.findAll();
    }

    @Override
    @Transactional
    public StudentSubjectMapping addNewStudentSubjectMapping(String studentId, String subjectId) throws SystemException {
        Student student = studentService.findById(studentId);
        Subject subject = subjectService.findById(subjectId);
        StudentSubjectMapping studentSubjectMapping = studentSubjectMappingDAO.findByStudentAndSubject(student,subject).get(0);
        if (studentSubjectMapping != null) {
            throw new SystemException("Student Subject Mapping Already Present");
        }
        studentSubjectMapping = new StudentSubjectMapping();
        studentSubjectMapping.setStudent(student);
        studentSubjectMapping.setSubject(subject);
        studentSubjectMapping = studentSubjectMappingDAO.saveAndFlush(studentSubjectMapping);

        return studentSubjectMapping;
    }

    @Override
    public List<StudentSubjectMapping>  findByStudentAndSubject(String studentId, String subjectId) {
        Student student = studentService.findById(studentId);
        Subject subject = subjectService.findById(subjectId);
        return studentSubjectMappingDAO.findByStudentAndSubject(student,subject);
    }

}
