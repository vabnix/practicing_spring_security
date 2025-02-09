package com.vaibhav.security.service;

import com.vaibhav.security.entity.Student;
import com.vaibhav.security.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        if(id!=null){
            Optional<Student> studentOptional = studentRepository.findById(id);
            if(studentOptional.isPresent()){
                return studentOptional.get();
            }
        }
        return null;
    }

    public Student addStudent(Student student) {
        if(student!=null){
            Student savedStudent = studentRepository.save(student);
            return savedStudent;
        }
        return null;
    }
}
