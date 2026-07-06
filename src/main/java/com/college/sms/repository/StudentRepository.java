package com.college.sms.repository;

import com.college.sms.dto.DepartmentCountDTO;
import com.college.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findTop5ByOrderByIdDesc();

    @Query("""
            SELECT new com.college.sms.dto.DepartmentCountDTO(
                    s.department,
                    COUNT(s)
            )
            FROM Student s
            GROUP BY s.department
            """)
    List<DepartmentCountDTO> getDepartmentStatistics();


    List<Student> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrDepartmentContainingIgnoreCase(
            String firstName,
            String lastName,
            String email,
            String department
    );
}