package com.college.sms.service;

import com.college.sms.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course saveCourse(Course course);

    List<Course> getAllCourses();

    Optional<Course> getCourseById(Long id);

    Course updateCourse(Course course);

    void deleteCourse(Long id);

    // Dashboard
    long getCourseCount();
}