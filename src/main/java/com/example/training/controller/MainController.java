package com.example.training.controller;

import com.example.training.delegate.StudentDelegate;
import com.example.training.entity.Student;
import com.example.training.info.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.SystemException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path="/demo")
public class MainController {
    @Autowired
    StudentDelegate studentDelegate;

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

    @GetMapping(path="/all")
    public @ResponseBody
    ResponseEntity<List<StudentInfo>> getAll() throws IOException {
        List<StudentInfo> result = studentDelegate.getAll();
        return ResponseEntity.ok(result);
    }


}