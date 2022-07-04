package com.example.training.dao;

import com.example.training.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO extends JpaRepository<Student, String>, JpaSpecificationExecutor<Student> {
}
