package com.vaibhav.security.controller;

import com.vaibhav.security.entity.Student;
import com.vaibhav.security.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get-token")
    public CsrfToken getToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping
    public String seeHome(HttpServletRequest request){
        return "Hello Vaibhav, <br/> your session id :"+ request.getSession().getId();
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }


    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
}
