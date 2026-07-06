package com.college.sms.controller;

import com.college.sms.entity.Result;
import com.college.sms.entity.Student;
import com.college.sms.service.ResultService;
import com.college.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/results")
public class ResultController {

    private final ResultService resultService;
    private final StudentService studentService;

    public ResultController(ResultService resultService,
                            StudentService studentService) {

        this.resultService = resultService;
        this.studentService = studentService;
    }

    // ================= View All Results =================

    @GetMapping
    public String listResults(Model model) {

        model.addAttribute(
                "results",
                resultService.getAllResults());

        // ================= Dashboard Analytics =================

        long totalResults =
                resultService.getTotalResults();

        Double averageSGPA =
                resultService.getAverageSGPA();

        Double averageCGPA =
                resultService.getAverageCGPA();

        long passedStudents =
                resultService.getPassedStudents();

        long failedStudents =
                resultService.getFailedStudents();

        double passPercentage = 0;

        if (totalResults > 0) {

            passPercentage =
                    (passedStudents * 100.0) / totalResults;

        }

        model.addAttribute(
                "totalResults",
                totalResults);

        model.addAttribute(
                "averageSGPA",
                averageSGPA == null
                        ? 0.0
                        : Math.round(averageSGPA * 100.0) / 100.0);

        model.addAttribute(
                "averageCGPA",
                averageCGPA == null
                        ? 0.0
                        : Math.round(averageCGPA * 100.0) / 100.0);

        model.addAttribute(
                "passPercentage",
                String.format("%.2f%%", passPercentage));

        model.addAttribute(
                "passedStudents",
                passedStudents);

        model.addAttribute(
                "failedStudents",
                failedStudents);

// ================= Charts =================

        model.addAttribute(
                "semesterSGPA",
                resultService.getSemesterSGPA());

        model.addAttribute(
                "semesterDistribution",
                resultService.getSemesterDistribution());

        return "results/results";
    }
    // ================= Add Result Form =================

    @GetMapping("/new")
    public String addResultForm(Model model) {

        model.addAttribute(
                "result",
                new Result());

        model.addAttribute(
                "students",
                studentService.getAllStudents());

        return "results/result-form";
    }

    // ================= Save Result =================

    @PostMapping("/save")
    public String saveResult(@ModelAttribute Result result) {

        resultService.saveResult(result);

        return "redirect:/results";
    }

    // ================= Edit Result =================

    @GetMapping("/edit/{id}")
    public String editResult(@PathVariable Long id,
                             Model model) {

        Result result = resultService
                .getResultById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid Result Id : " + id));

        model.addAttribute("result", result);

        model.addAttribute(
                "students",
                studentService.getAllStudents());

        return "results/result-form";
    }

    // ================= Update Result =================

    @PostMapping("/update")
    public String updateResult(@ModelAttribute Result result) {

        resultService.updateResult(result);

        return "redirect:/results";
    }

    // ================= Delete Result =================

    @GetMapping("/delete/{id}")
    public String deleteResult(@PathVariable Long id) {

        resultService.deleteResult(id);

        return "redirect:/results";
    }

}