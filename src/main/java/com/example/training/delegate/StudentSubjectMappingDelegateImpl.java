package com.example.training.delegate;

import com.example.base.AbstractDelegate;
import com.example.training.entity.Student;
import com.example.training.entity.Subject;
import com.example.training.info.StudentInfo;
import com.example.training.info.StudentSubjectMappingInfo;
import com.example.training.info.SubjectInfo;
import com.example.training.service.StudentService;
import com.example.training.service.StudentSubjectMappingService;
import com.example.training.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.SystemException;
import java.io.IOException;
import java.util.List;

@Component
public class StudentSubjectMappingDelegateImpl extends AbstractDelegate implements StudentSubjectMappingDelegate{

    @Autowired
    StudentSubjectMappingService studentSubjectMappingService;

    @Override
    public List<StudentSubjectMappingInfo> getAll() throws IOException {
        return convertToList(studentSubjectMappingService.getAll(), StudentSubjectMappingInfo.class);
    }

    @Override
    public StudentSubjectMappingInfo addNewStudentSubjectMapping(String studentId, String subjectId) throws IOException, SystemException {

        return convert(studentSubjectMappingService.addNewStudentSubjectMapping(studentId,subjectId), StudentSubjectMappingInfo.class);
    }
}
