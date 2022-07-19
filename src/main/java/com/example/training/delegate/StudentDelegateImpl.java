package com.example.training.delegate;

import com.example.base.AbstractDelegate;
import com.example.training.entity.Student;
import com.example.training.entity.StudentResultMapping;
import com.example.training.entity.StudentSubjectMapping;
import com.example.training.info.StudentCgpaInfo;
import com.example.training.info.StudentInfo;
import com.example.training.info.StudentResultMappingInfo;
import com.example.training.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.SystemException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class StudentDelegateImpl extends AbstractDelegate implements StudentDelegate{
    @Autowired
    StudentService studentService;

    @Override
    public List<Student> getAll() throws IOException {
        List<Student> studentList = studentService.getAll();
//        studentList.get(0).setSubjects(null);
        return studentList;
    }

    @Override
    public StudentInfo getById(String studentId) throws IOException {
        return convert(studentService.findById(studentId), StudentInfo.class);
    }

    @Override
    public StudentInfo addNewStudent(String firstName, String lastName, String address) throws IOException {
        return convert(studentService.addNewStudent(firstName, lastName, address), StudentInfo.class);
    }

    @Override
    public StudentInfo updateStudent(String studentId, String firstName, String lastName, String address) throws SystemException, IOException {
        return convert(studentService.updateStudent(studentId, firstName, lastName, address), StudentInfo.class);
    }

    @Override
    public void deleteStudent(String studentId) {
        studentService.deleteStudent(studentId);
    }

    @Override
    public List<StudentResultMappingInfo> getGPA(String studentId) throws IOException {
        Student student = studentService.findById(studentId);
        List<StudentResultMapping> studentResultMappingList = new ArrayList<StudentResultMapping>();
        for(StudentSubjectMapping subject: student.getSubjects()) {
            studentResultMappingList.add(subject.getStudentResultMapping());
        }
        List<StudentResultMappingInfo> studentResultMappingInfos = convertToList(studentResultMappingList,StudentResultMappingInfo.class);
        while(studentResultMappingInfos.remove(null));
        for(StudentResultMappingInfo studentResultMappingInfo: studentResultMappingInfos){
            studentResultMappingInfo.setGrade();
        }
        return studentResultMappingInfos;
    }

    @Override
    public Double getCGPA(String studentId) throws IOException {
        List<StudentResultMappingInfo> studentResultMappingInfos = getGPA(studentId);
        Double sum = 0.0;
        for(StudentResultMappingInfo studentResultMappingInfo: studentResultMappingInfos){
            sum+=studentResultMappingInfo.getGrade();
        }
        
        return sum/studentResultMappingInfos.size();
    }

    @Override
    public List<StudentCgpaInfo> getCGPAList() throws IOException {
        List<Student> studentList = studentService.getAll();
        List<StudentCgpaInfo> studentCgpaInfoList = new ArrayList<StudentCgpaInfo>();
        for(Student student: studentList){
            String studentId = student.getId();
            Double cgpa = getCGPA(studentId);
            StudentCgpaInfo studentCgpaInfo = new StudentCgpaInfo();
            studentCgpaInfo.setId(studentId);
            studentCgpaInfo.setGrade(cgpa);
            studentCgpaInfoList.add(studentCgpaInfo);
        }
        Collections.sort(studentCgpaInfoList, new Comparator<StudentCgpaInfo>() {
            @Override
            public int compare(StudentCgpaInfo lhs, StudentCgpaInfo rhs) {
                return lhs.getGrade() > rhs.getGrade() ? -1 : lhs.getGrade() == rhs.getGrade() ? 0 : 1;
            }
        });
        return studentCgpaInfoList.subList(0,Math.min(3,studentCgpaInfoList.size()));
    }


}
