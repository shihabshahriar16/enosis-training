package com.example.training.dao;

import com.example.training.entity.Student;
import com.example.training.entity.StudentSubjectMapping;
import com.example.training.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSubjectMappingDAO extends JpaRepository<StudentSubjectMapping, String>, JpaSpecificationExecutor<StudentSubjectMapping> {
    List<StudentSubjectMapping> findByStudentAndSubject(Student student, Subject subject);
}
