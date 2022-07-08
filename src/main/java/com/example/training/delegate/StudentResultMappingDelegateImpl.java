package com.example.training.delegate;

import com.example.base.AbstractDelegate;
import com.example.training.info.StudentResultMappingInfo;
import com.example.training.info.SubjectInfo;
import com.example.training.service.StudentResultMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.SystemException;
import java.io.IOException;
import java.util.List;

@Component
public class StudentResultMappingDelegateImpl extends AbstractDelegate implements StudentResultMappingDelegate{

    @Autowired
    StudentResultMappingService studentResultMappingService;

    @Override
    public List<StudentResultMappingInfo> getAll() throws IOException {
        return convertToList(studentResultMappingService.getAll(), StudentResultMappingInfo.class);
    }

    @Override
    public StudentResultMappingInfo addNewStudentResultMapping(String studentId, String subjectId, int score) throws IOException, SystemException {

        return convert(studentResultMappingService.addNewStudentResultMapping(studentId,subjectId,score), StudentResultMappingInfo.class);
    }

    @Override
    public StudentResultMappingInfo updateStudentResultMapping(String studentSubjectMappingId, int score) throws SystemException, IOException {
        return convert(studentResultMappingService.updateStudentResultMapping(studentSubjectMappingId, score), StudentResultMappingInfo.class);
    }
}
