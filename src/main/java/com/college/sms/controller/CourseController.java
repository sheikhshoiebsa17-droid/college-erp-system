package com.college.sms.controller;

import com.college.sms.entity.Course;
import com.college.sms.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String viewCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "course/courses";
    }

    @GetMapping("/new")
    public String showCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "course/add-course";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {

        Course course = courseService.getCourseById(id).orElseThrow();

        model.addAttribute("course", course);

        return "course/edit-course";
    }

    @PostMapping("/update")
    public String updateCourse(@ModelAttribute Course course) {

        courseService.updateCourse(course);

        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);

        return "redirect:/courses";
    }
}