package com.college.sms.repository;

import com.college.sms.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findTop5ByOrderByIdDesc();

}