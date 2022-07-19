package com.example.training.service;

import com.example.training.dao.SubjectDAO;
import com.example.training.entity.Student;
import com.example.training.entity.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.transaction.SystemException;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Inject
    SubjectDAO subjectDAO;

    @Override
    public Subject findById(String id){
        return subjectDAO.findById(id);
    }

    public List<Subject> getAll() {
        return subjectDAO.findAll();
    }

    @Override
    public Subject addNewSubject(String subjectTitle) {
        Subject subject = new Subject();
        subject.setSubjectTitle(subjectTitle);
        subject = subjectDAO.saveAndFlush(subject);
        return subject;
    }

    @Override
    public Subject updateSubject(String subjectId, String subjectTitle) throws SystemException {
        Subject subject = subjectDAO.findOne(subjectId);
        if (subject == null) {
            throw new SystemException("Subject Not Found");
        }

        if (!StringUtils.isEmpty(subjectTitle)) {
            subject.setSubjectTitle(subjectTitle);
        }

        return subjectDAO.saveAndFlush(subject);
    }

    @Override
    public void deleteSubject(String subjectId) {
        subjectDAO.delete(subjectId);
        subjectDAO.flush();
    }
}
