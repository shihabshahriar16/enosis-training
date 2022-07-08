package com.example.training.dao;

import com.example.training.entity.StudentResultMapping;
import com.example.training.entity.StudentSubjectMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentResultMappingDAO extends JpaRepository<StudentResultMapping, String>, JpaSpecificationExecutor<StudentResultMapping> {
    List<StudentResultMapping> findByStudentSubjectMapping(StudentSubjectMapping studentSubjectMapping);
}
