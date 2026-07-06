package com.college.sms.service;

import com.college.sms.entity.Student;

import java.util.List;
import java.util.Optional;
import com.college.sms.dto.DepartmentCountDTO;
import org.springframework.data.domain.Page;

public interface StudentService {

    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Optional<Student> getStudentById(Long id);

    Student updateStudent(Student student);

    void deleteStudent(Long id);

    // Dashboard
    long getStudentCount();
    List<Student> getLatestStudents();
    List<DepartmentCountDTO> getDepartmentStatistics();


    List<Student> searchStudents(String keyword);

    Page<Student> getStudentsPage(int pageNo);
}