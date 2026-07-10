package com.college.sms.service;

import com.college.sms.dto.DepartmentCountDTO;
import com.college.sms.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Optional<Student> getStudentById(Long id);

    Student updateStudent(Student student);

    void deleteStudent(Long id);

    long getStudentCount();

    List<Student> getLatestStudents();

    List<DepartmentCountDTO> getDepartmentStatistics();

    List<Student> searchStudents(String keyword);

    Page<Student> getStudentsPage(int pageNo);
}