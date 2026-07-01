package com.college.sms.controller;

import com.college.sms.entity.Faculty;
import com.college.sms.service.FacultyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public String viewFaculty(Model model) {
        model.addAttribute("facultyList", facultyService.getAllFaculty());
        return "faculty/faculty";
    }

    @GetMapping("/new")
    public String showFacultyForm(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "faculty/add-faculty";
    }

    @PostMapping("/save")
    public String saveFaculty(@ModelAttribute Faculty faculty) {
        facultyService.saveFaculty(faculty);
        return "redirect:/faculty";
    }

    @GetMapping("/edit/{id}")
    public String editFaculty(@PathVariable Long id, Model model) {
        Faculty faculty = facultyService.getFacultyById(id).orElseThrow();
        model.addAttribute("faculty", faculty);
        return "faculty/edit-faculty";
    }

    @PostMapping("/update")
    public String updateFaculty(@ModelAttribute Faculty faculty) {
        facultyService.updateFaculty(faculty);
        return "redirect:/faculty";
    }

    @GetMapping("/delete/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return "redirect:/faculty";
    }
}