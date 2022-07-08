package com.example.training.delegate;

import com.example.training.info.SubjectInfo;

import javax.transaction.SystemException;
import java.io.IOException;
import java.util.List;

public interface SubjectDelegate {
    List<SubjectInfo> getAll() throws IOException;

    SubjectInfo addNewSubject(String subjectTitle) throws IOException;

    SubjectInfo updateSubject(String subjectId, String subjectTitle) throws SystemException, IOException;
}
