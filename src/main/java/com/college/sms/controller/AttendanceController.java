package com.college.sms.controller;

import com.college.sms.entity.Attendance;
import com.college.sms.service.AttendanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public String viewAttendance(Model model) {
        model.addAttribute("attendanceList", attendanceService.getAllAttendance());
        return "attendance/attendance";
    }

    @GetMapping("/new")
    public String showAttendanceForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        return "attendance/mark-attendance";
    }

    @PostMapping("/save")
    public String saveAttendance(@ModelAttribute Attendance attendance) {
        attendanceService.saveAttendance(attendance);
        return "redirect:/attendance";
    }

    @GetMapping("/edit/{id}")
    public String editAttendance(@PathVariable Long id, Model model) {
        Attendance attendance = attendanceService.getAttendanceById(id).orElseThrow();
        model.addAttribute("attendance", attendance);
        return "attendance/edit-attendance";
    }

    @PostMapping("/update")
    public String updateAttendance(@ModelAttribute Attendance attendance) {
        attendanceService.updateAttendance(attendance);
        return "redirect:/attendance";
    }

    @GetMapping("/delete/{id}")
    public String deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return "redirect:/attendance";
    }
}