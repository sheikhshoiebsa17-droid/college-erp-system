package com.college.sms.controller;

import com.college.sms.entity.Student;
import com.college.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String viewStudents(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        // Search
        if (keyword != null && !keyword.trim().isEmpty()) {

            model.addAttribute("students",
                    studentService.searchStudents(keyword));

            model.addAttribute("keyword", keyword);

            return "student/students";
        }

        // Pagination
        Page<Student> studentPage = studentService.getStudentsPage(page);

        model.addAttribute("students", studentPage.getContent());

        model.addAttribute("currentPage", page);

        model.addAttribute("totalPages", studentPage.getTotalPages());

        model.addAttribute("totalItems", studentPage.getTotalElements());

        return "student/students";
    }

    @GetMapping("/new")
    public String showStudentForm(Model model) {

        model.addAttribute("student", new Student());

        return "student/student-form";
    }
    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "student/student-form";
        }

        studentService.saveStudent(student);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Student added successfully!");

        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {

        Student student = studentService.getStudentById(id).orElseThrow();

        model.addAttribute("student", student);

        return "student/student-form";
    }

    @PostMapping("/update")
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "student/student-form";
        }

        studentService.updateStudent(student);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Student updated successfully!");

        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {

        try {

            studentService.deleteStudent(id);

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Student deleted successfully.");

        } catch (DataIntegrityViolationException e) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Cannot delete this student because Marks, Results, Attendance or Fee records already exist.");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Unable to delete student.");

        }

        return "redirect:/students";
    }

}