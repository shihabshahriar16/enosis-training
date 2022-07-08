package com.example.training.dao;

import com.example.training.entity.Student;
import com.example.training.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDAO extends JpaRepository<Subject, String>, JpaSpecificationExecutor<Subject> {

    public Subject findById(String id);
}