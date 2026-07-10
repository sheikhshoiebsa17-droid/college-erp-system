package com.college.sms.controller;

import com.college.sms.entity.Student;
import com.college.sms.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET All Students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // GET Student By ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {

        Optional<Student> student = studentService.getStudentById(id);

        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE Student
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        Student savedStudent = studentService.saveStudent(student);

        return ResponseEntity.ok(savedStudent);
    }

    // UPDATE Student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        Optional<Student> existingStudent = studentService.getStudentById(id);

        if (existingStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        student.setId(id);

        Student updatedStudent = studentService.updateStudent(student);

        return ResponseEntity.ok(updatedStudent);
    }

    // DELETE Student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        Optional<Student> existingStudent = studentService.getStudentById(id);

        if (existingStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        studentService.deleteStudent(id);

        return ResponseEntity.ok("Student deleted successfully.");
    }
}