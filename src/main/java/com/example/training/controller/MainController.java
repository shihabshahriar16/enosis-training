package com.example.training.controller;

import com.example.training.delegate.StudentDelegate;
import com.example.training.delegate.StudentResultMappingDelegate;
import com.example.training.delegate.StudentSubjectMappingDelegate;
import com.example.training.delegate.SubjectDelegate;
import com.example.training.entity.Student;
import com.example.training.entity.StudentResultMapping;
import com.example.training.entity.StudentSubjectMapping;
import com.example.training.info.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path="/demo")
public class MainController {
    @Autowired
    StudentDelegate studentDelegate;
    @Autowired
    SubjectDelegate subjectDelegate;
    @Autowired
    StudentSubjectMappingDelegate studentSubjectMappingDelegate;
    @Autowired
    StudentResultMappingDelegate studentResultMappingDelegate;

    @PostMapping(path="/add-student")
    public @ResponseBody
    ResponseEntity<StudentInfo> addNewStudent (@RequestParam String firstName,
                       @RequestParam String lastName,
                       @RequestParam String address) throws IOException {
        StudentInfo result = studentDelegate.addNewStudent(firstName, lastName, address);
        return ResponseEntity.ok(result);
    }

    @PutMapping(path="/update-student")
    public @ResponseBody
    ResponseEntity<StudentInfo> updateStudent (@RequestParam String studentId,
                                  @RequestParam String firstName,
                                  @RequestParam String lastName,
                                  @RequestParam String address) throws IOException {

        StudentInfo result = null;
        try {
            result = studentDelegate.updateStudent(studentId, firstName, lastName, address);
        } catch (SystemException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path="/delete-student")
    public @ResponseBody
    ResponseEntity<Boolean> deleteStudent (@RequestParam String studentId) throws IOException {
        studentDelegate.deleteStudent(studentId);
        return ResponseEntity.ok(true);
    }

    @GetMapping(path="/all")
    public @ResponseBody
    ResponseEntity<List<Student>> getAll() throws IOException {
        List<Student> result = studentDelegate.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping(path="/get-all-info")
    public @ResponseBody
    ResponseEntity<StudentInfo> getAllInfo(@RequestParam String studentId) throws IOException {
        StudentInfo result = studentDelegate.getById(studentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path="/get-info-subject")
    public @ResponseBody
    ResponseEntity<StudentInfo> getInfoSubject(@RequestParam String studentId) throws IOException {
        StudentInfo result = studentDelegate.getById(studentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path="/all-subject")
    public @ResponseBody
    ResponseEntity<List<SubjectInfo>> getAAll() throws IOException {
        List<SubjectInfo> result = subjectDelegate.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping(path="/add-subject")
    public @ResponseBody
    ResponseEntity<SubjectInfo> addNewSubject (@RequestParam String subjectTitle) throws IOException {
        SubjectInfo result = subjectDelegate.addNewSubject(subjectTitle);
        return ResponseEntity.ok(result);
    }

    @PutMapping(path="/update-subject")
    public @ResponseBody
    ResponseEntity<SubjectInfo> updateSubject (@RequestParam String subjectId,
                                               @RequestParam String subjectTitle) throws IOException {

        SubjectInfo result = null;
        try {
            result = subjectDelegate.updateSubject(subjectId, subjectTitle);
        } catch (SystemException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path="/delete-subject")
    public @ResponseBody
    ResponseEntity<Boolean> deleteSubject (@RequestParam String subjectId) throws IOException {
        subjectDelegate.deleteSubject(subjectId);
        return ResponseEntity.ok(true);
    }

    @PostMapping(path="/assign-subject")
    public @ResponseBody
    ResponseEntity<StudentSubjectMappingInfo> addNewStudentSubject (@RequestParam String studentId,
                                                             @RequestParam String subjectId) throws IOException, SystemException {
        StudentSubjectMappingInfo result = studentSubjectMappingDelegate.addNewStudentSubjectMapping(studentId,subjectId);
        return ResponseEntity.ok(result);
    }

    @PostMapping(path="/add-result")
    public @ResponseBody
    ResponseEntity<StudentResultMappingInfo> addNewStudentResultMapping (@RequestParam String studentId,
                                                                         @RequestParam String subjectId,
                                                                         @RequestParam int score) throws IOException, SystemException {
        StudentResultMappingInfo result = studentResultMappingDelegate.addNewStudentResultMapping(studentId,subjectId,score);
        return ResponseEntity.ok(result);
    }
    @PutMapping(path="/update-result")
    public @ResponseBody
    ResponseEntity<StudentResultMappingInfo> updateStudentResultMapping (@RequestParam String studentResultMappingId,
                                               @RequestParam int score) throws IOException {

        StudentResultMappingInfo result = null;
        try {
            result = studentResultMappingDelegate.updateStudentResultMapping(studentResultMappingId, score);
        } catch (SystemException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping(path="/calculate-gpa")
    public @ResponseBody
    ResponseEntity<List<StudentResultMappingInfo>> calculateGpa(@RequestParam String studentId) throws IOException {
        List<StudentResultMappingInfo> result = studentDelegate.getGPA(studentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path="/calculate-cgpa")
    public @ResponseBody
    ResponseEntity<Double> calculateCgpa(@RequestParam String studentId) throws IOException {
        Double result = studentDelegate.getCGPA(studentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path="/result-table")
    public @ResponseBody
    ResponseEntity<List<StudentCgpaInfo>> getCGPAList() throws IOException {
        List<StudentCgpaInfo> result = studentDelegate.getCGPAList();
        return ResponseEntity.ok(result);
    }

}