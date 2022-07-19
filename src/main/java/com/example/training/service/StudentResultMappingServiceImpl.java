package com.example.training.service;

import com.example.training.dao.StudentResultMappingDAO;
import com.example.training.dao.StudentSubjectMappingDAO;
import com.example.training.entity.Student;
import com.example.training.entity.StudentResultMapping;
import com.example.training.entity.StudentSubjectMapping;
import com.example.training.entity.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentResultMappingServiceImpl implements StudentResultMappingService{
    @Inject
    StudentResultMappingDAO studentResultMappingDAO;
    @Inject
    StudentSubjectMappingService studentSubjectMappingService;
    @Override
    public List<StudentResultMapping> getAll() {
        return studentResultMappingDAO.findAll();

    }

    @Override
    @Transactional
    public StudentResultMapping addNewStudentResultMapping(String studentId,String subjectId, int score) throws SystemException {
        StudentSubjectMapping studentSubjectMapping = studentSubjectMappingService.findByStudentAndSubject(studentId,subjectId).get(0);
        StudentResultMapping studentResultMapping = studentResultMappingDAO.findByStudentSubjectMapping(studentSubjectMapping);
        if (studentResultMapping != null) {
            throw new SystemException("Student Result Mapping Already Present");
        }
        studentResultMapping = new StudentResultMapping();
        studentResultMapping.setStudentSubjectMapping(studentSubjectMapping);
        studentResultMapping.setScore(score);
        studentResultMapping = studentResultMappingDAO.saveAndFlush(studentResultMapping);
        return studentResultMapping;
    }

    @Override
    @Transactional
    public StudentResultMapping updateStudentResultMapping(String studentSubjectMappingId, int score) throws SystemException {
        StudentResultMapping studentResultMapping = studentResultMappingDAO.findOne(studentSubjectMappingId);
        if (studentResultMapping == null) {
            throw new SystemException("Student Result Mapping Not Found");
        }
        if (!StringUtils.isEmpty(score)) {
            studentResultMapping.setScore(score);
        }
        studentResultMapping = studentResultMappingDAO.saveAndFlush(studentResultMapping);
        return studentResultMapping;
    }
}
