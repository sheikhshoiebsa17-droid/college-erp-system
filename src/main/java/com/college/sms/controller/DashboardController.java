package com.college.sms.controller;

import com.college.sms.repository.CourseRepository;
import com.college.sms.repository.FacultyRepository;
import com.college.sms.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final CourseRepository courseRepository;

    public DashboardController(StudentRepository studentRepository,
                               FacultyRepository facultyRepository,
                               CourseRepository courseRepository) {

        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/")
    public String dashboard(Model model) {

        model.addAttribute("studentCount", studentRepository.count());

        model.addAttribute("facultyCount", facultyRepository.count());

        model.addAttribute("courseCount", courseRepository.count());

        return "dashboard/dashboard";
    }

}