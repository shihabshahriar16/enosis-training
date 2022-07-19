package com.example.training.delegate;

import com.example.base.AbstractDelegate;
import com.example.training.info.SubjectInfo;
import com.example.training.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.SystemException;
import java.io.IOException;
import java.util.List;

@Component
public class SubjectDelegateImpl extends AbstractDelegate implements SubjectDelegate{
    @Autowired
    SubjectService subjectService;

    @Override
    public List<SubjectInfo> getAll() throws IOException {
        return convertToList(subjectService.getAll(), SubjectInfo.class);
    }

    @Override
    public SubjectInfo addNewSubject(String subjectTitle) throws IOException {
        return convert(subjectService.addNewSubject(subjectTitle), SubjectInfo.class);
    }

    @Override
    public SubjectInfo updateSubject(String subjectId, String subjectTitle) throws SystemException, IOException {
        return convert(subjectService.updateSubject(subjectId, subjectTitle), SubjectInfo.class);
    }

    @Override
    public void deleteSubject(String subjectId) {
        subjectService.deleteSubject(subjectId);
    }
}
