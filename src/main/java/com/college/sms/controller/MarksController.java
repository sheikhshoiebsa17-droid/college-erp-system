package com.college.sms.controller;

import com.college.sms.entity.Marks;
import com.college.sms.entity.Student;
import com.college.sms.service.MarksService;
import com.college.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.college.sms.export.MarksExcelExporter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.college.sms.export.MarksPdfExporter;


@Controller
@RequestMapping("/marks")
public class MarksController {

    private final MarksService marksService;
    private final StudentService studentService;

    public MarksController(MarksService marksService,
                           StudentService studentService) {

        this.marksService = marksService;
        this.studentService = studentService;

    }

    // ================= Marks List =================

    @GetMapping
    public String marksList(

            @RequestParam(value = "keyword", required = false)
            String keyword,

            Model model) {

        if (keyword != null && !keyword.trim().isEmpty()) {

            model.addAttribute(
                    "marksList",
                    marksService.searchMarks(keyword));

        } else {

            model.addAttribute(
                    "marksList",
                    marksService.getAllMarks());

        }

        model.addAttribute("keyword", keyword);

        // ================= Dashboard Statistics =================

        model.addAttribute(
                "totalRecords",
                marksService.getTotalMarksRecords());

        model.addAttribute(
                "passedStudents",
                marksService.getPassedStudents());

        model.addAttribute(
                "failedStudents",
                marksService.getFailedStudents());

        model.addAttribute(
                "averageMarks",
                marksService.getAverageMarks());

        model.addAttribute(
                "highestMarks",
                marksService.getHighestMarks());

        model.addAttribute(
                "topPerformer",
                marksService.getTopPerformer());
        model.addAttribute(
                "subjectPerformance",
                marksService.getSubjectPerformance());

        model.addAttribute(
                "gradeDistribution",
                marksService.getGradeDistribution());
        return "marks/marks";
    }

    // ================= Add Marks =================

    @GetMapping("/new")
    public String showMarksForm(Model model) {

        model.addAttribute("marks", new Marks());

        model.addAttribute(
                "students",
                studentService.getAllStudents());

        return "marks/form";

    }

    // ================= Save / Update Marks =================

    @PostMapping("/save")
    public String saveMarks(

            @RequestParam("studentId") Long studentId,

            @ModelAttribute Marks marks) {

        Student student = studentService
                .getStudentById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student Not Found"));

        marks.setStudent(student);

        // Calculate Total

        int total = marks.getInternalMarks() +
                marks.getExternalMarks();

        marks.setTotalMarks(total);

        // Grade

        if (total >= 90) {

            marks.setGrade("A+");

        } else if (total >= 80) {

            marks.setGrade("A");

        } else if (total >= 70) {

            marks.setGrade("B");

        } else if (total >= 60) {

            marks.setGrade("C");

        } else if (total >= 50) {

            marks.setGrade("D");

        } else {

            marks.setGrade("F");

        }

        // Result

        if (total >= 50) {

            marks.setResult("Pass");

        } else {

            marks.setResult("Fail");

        }

        marksService.saveMarks(marks);

        return "redirect:/marks";

    }

    // ================= Edit Marks =================

    @GetMapping("/edit/{id}")
    public String editMarks(
            @PathVariable Long id,
            Model model) {

        Marks marks = marksService
                .getMarksById(id)
                .orElseThrow(() ->
                        new RuntimeException("Marks not found"));

        model.addAttribute("marks", marks);

        model.addAttribute(
                "students",
                studentService.getAllStudents());

        return "marks/form";

    }

    // ================= Delete Marks =================

    @GetMapping("/delete/{id}")
    public String deleteMarks(
            @PathVariable Long id) {

        marksService.deleteMarks(id);

        return "redirect:/marks";

    }
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response)
            throws IOException {

        response.setContentType(
                "application/octet-stream");

        String headerKey =
                "Content-Disposition";

        String headerValue =
                "attachment; filename=marks_report.xlsx";

        response.setHeader(headerKey, headerValue);

        List<Marks> listMarks =
                marksService.getAllMarks();

        MarksExcelExporter exporter =
                new MarksExcelExporter(listMarks);

        exporter.export(response);

    }
    @GetMapping("/export/pdf")
    public void exportToPdf(HttpServletResponse response)
            throws IOException {

        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";

        String headerValue =
                "attachment; filename=marks_report.pdf";

        response.setHeader(headerKey, headerValue);

        List<Marks> listMarks = marksService.getAllMarks();

        MarksPdfExporter exporter =
                new MarksPdfExporter(listMarks);

        exporter.export(response);

    }
}