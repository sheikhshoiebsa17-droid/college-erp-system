package com.college.sms.controller;

import com.college.sms.entity.Attendance;
import com.college.sms.entity.Student;
import com.college.sms.service.AttendanceService;
import com.college.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final StudentService studentService;

    public AttendanceController(AttendanceService attendanceService,
                                StudentService studentService) {
        this.attendanceService = attendanceService;
        this.studentService = studentService;
    }

    // ===================== Attendance List =====================

    @GetMapping
    public String attendanceList(
            @RequestParam(required = false) String keyword,
            Model model) {

        if (keyword != null && !keyword.isBlank()) {

            model.addAttribute(
                    "attendanceList",
                    attendanceService.searchAttendance(keyword));

        } else {

            model.addAttribute(
                    "attendanceList",
                    attendanceService.getAllAttendance());

        }

        model.addAttribute("keyword", keyword);

        return "attendance/attendance";
    }

    // ===================== Attendance Form =====================

    @GetMapping("/new")
    public String showAttendanceForm(Model model) {

        model.addAttribute(
                "attendance",
                new Attendance());

        model.addAttribute(
                "students",
                studentService.getAllStudents());

        return "attendance/form";
    }

    // ===================== Save Attendance =====================

//    @PostMapping("/save")
//    public String saveAttendance(
//            @RequestParam("studentId") Long studentId,
//            @RequestParam("attendanceDate") String attendanceDate,
//            @RequestParam("status") String status) {
//
//        System.out.println("========== SAVE ATTENDANCE ==========");
//        System.out.println("Student ID : " + studentId);
//        System.out.println("Date       : " + attendanceDate);
//        System.out.println("Status     : " + status);
//
//        Student student = studentService
//                .getStudentById(studentId)
//                .orElseThrow(() -> new RuntimeException("Student Not Found"));
//
//        Attendance attendance = new Attendance();
//
//        attendance.setStudent(student);
//        attendance.setAttendanceDate(java.time.LocalDate.parse(attendanceDate));
//        attendance.setStatus(status);
//
//        attendanceService.saveAttendance(attendance);
//
//        System.out.println("Attendance Saved Successfully!");
//
//        return "redirect:/attendance";
//    }
@PostMapping("/save")
public String saveAttendance(
        @RequestParam(required = false) Long id,
        @RequestParam("studentId") Long studentId,
        @RequestParam("attendanceDate") String attendanceDate,
        @RequestParam("status") String status) {

    Attendance attendance;

    if (id != null) {

        attendance = attendanceService
                .getAttendanceById(id)
                .orElse(new Attendance());

    } else {

        attendance = new Attendance();

    }

    Student student = studentService
            .getStudentById(studentId)
            .orElseThrow(() -> new RuntimeException("Student Not Found"));

    attendance.setStudent(student);
    attendance.setAttendanceDate(java.time.LocalDate.parse(attendanceDate));
    attendance.setStatus(status);

    attendanceService.saveAttendance(attendance);

    return "redirect:/attendance";
}
    @GetMapping("/edit/{id}")
    public String editAttendance(@PathVariable Long id,
                                 Model model) {

        Attendance attendance = attendanceService
                .getAttendanceById(id)
                .orElseThrow(() ->
                        new RuntimeException("Attendance Not Found"));

        model.addAttribute("attendance", attendance);

        model.addAttribute(
                "students",
                studentService.getAllStudents());

        return "attendance/form";
    }
    @GetMapping("/delete/{id}")
    public String deleteAttendance(@PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return "redirect:/attendance";

    }
}